package edu.lingnan.talklater.modules.requestxx.service.impl;

import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import edu.lingnan.talklater.modules.requestxx.domain.RequestXx;
import edu.lingnan.talklater.modules.requestxx.domain.vo.RequestXxVo;
import edu.lingnan.talklater.modules.requestxx.repository.RequestXxRepository;
import edu.lingnan.talklater.modules.requestxx.service.RequestXxService;
import edu.lingnan.talklater.response.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.netty.util.internal.StringUtil;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Description:
 * date: 2021/2/4 14:30
 *
 * @author likunzhu
 * @since
 */
@Transactional
@Service
public class RequestXxServiceImpl implements RequestXxService {

    private final String PASS_REQUEST="2"; //通过好友申请

    private final String DEBY_REQUEST="3";//拒绝好友申请

    @Autowired
    private RequestXxRepository requestXxRepository;

    @Autowired
    private FriendsRefService friendsRefService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 对发起的请求校验，1、请求对象不能为空，2、请求对象不能是自己，3、请求对象不能是自己的好友，
     * 4、对同一个用户发送的好友请求，如果上一条请求还未被处理，不允许重复提交
     */
    @Override
    public int verifyRequest(String senderName,String recipientName) {

        //1、请求对象不能为空
        if (StringUtil.isNullOrEmpty(recipientName)) return ReturnCode.FRIEND_USERNAME_NULL.getCode();

        //2、请求对象不能是自己，
        if(senderName.equals(recipientName)) return ReturnCode.IS_YOURSELF.getCode();

        //3、请求对象不能是自己的好友
        FriendsRef friendsRef = new FriendsRef();
        friendsRef.setUsername(senderName);
        friendsRef.setFriendUsername(recipientName);
        if(friendsRefService.isExist(friendsRef)) return ReturnCode.FRIEND_ALREADY.getCode();

        //4、对同一个用户发送的好友请求，如果上一条请求还未被处理，不允许重复提交
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) as num from u_request_xx urx ");
        sql.append(" where is_valid = '1' and request_state = '1' ");
        sql.append(" and sender_username = ? and recipient_username = ?");

        Map sqlResult = jdbcTemplate.queryForMap(sql.toString(),new Object[]{senderName,recipientName},new int[]{Types.VARCHAR,Types.VARCHAR});
        if(sqlResult!=null){
            int num = Integer.parseInt(String.valueOf(sqlResult.get("num")));
            if (num>0) return ReturnCode.ERROR_RESUBMIT.getCode();
        }
        return ReturnCode.SUCCESS.getCode();
    }

    /**
     * 新增好友请求
     */
    @Override
    public void addRequest(String senderName,String recipientName){

        RequestXx requestXx = new RequestXx();

        requestXx.setSenderUsername(senderName);
        requestXx.setRecipientUsername(recipientName);
        requestXx.setValid("1");
        requestXx.setRequest_state("1");
        requestXx.setCreatedDate(System.currentTimeMillis());
        requestXxRepository.save(requestXx);

    }

    @Override
    public List<RequestXxVo> queryAllRequest(String username) {

        StringBuffer sql = new StringBuffer();
        sql.append(" select * from v_request_xx  where recipient_username = ?");
        sql.append(" and is_valid ='1'");
        sql.append(" and request_state = '1'");
        sql.append(" order by created_date desc");

        List<RequestXxVo> list= jdbcTemplate.query(sql.toString(),new Object[]{username},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(RequestXxVo.class));

        return list;
    }

    @Override
    public boolean handleRequest(String requestId, String actionType) {

        if(StringUtil.isNullOrEmpty(requestId)||StringUtil.isNullOrEmpty(actionType)) return false;

        Optional<RequestXx> requestXxOptional =requestXxRepository.findById(requestId);

        if(!requestXxOptional.isPresent()) return false;

        StringBuffer sql = new StringBuffer();
        sql.append(" update u_request_xx set request_state = ? ,modified_date = ?  where is_valid = '1' and id = ?");
        jdbcTemplate.update(sql.toString(),new Object[]{actionType,System.currentTimeMillis(),requestId},new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});

        //新增好友表信息
        if(PASS_REQUEST.equals(actionType)){
            friendsRefService.becomeFriends(requestXxOptional.get().getSenderUsername(),requestXxOptional.get().getRecipientUsername());
            friendsRefService.becomeFriends(requestXxOptional.get().getRecipientUsername(),requestXxOptional.get().getSenderUsername());
        }

        return true;
    }
}
