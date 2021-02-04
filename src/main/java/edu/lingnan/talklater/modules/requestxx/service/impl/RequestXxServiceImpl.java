package edu.lingnan.talklater.modules.requestxx.service.impl;

import edu.lingnan.talklater.modules.friendsref.repository.FriendsRefRepository;
import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import edu.lingnan.talklater.modules.requestxx.repository.RequestXxRepository;
import edu.lingnan.talklater.modules.requestxx.service.RequestXxService;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.response.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.netty.util.internal.StringUtil;

import java.util.List;

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

    @Autowired
    private RequestXxRepository requestXxRepository;

    @Autowired
    private FriendsRefService friendsRefService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 对发起的请求校验，1、请求对象不能为空，2、请求对象不能是自己，3、请求对象不能是自己的好友
     */
    @Override
    public int verifyRequest(String senderName,String recipientName) {

        if (StringUtil.isNullOrEmpty(recipientName)) return ReturnCode.FRIEND_USERNAME_NULL.getCode();

        if(senderName.equals(recipientName)) return ReturnCode.IS_YOURSELF.getCode();

        FriendsRef friendsRef = new FriendsRef();
        friendsRef.setUsername(senderName);
        friendsRef.setFriendUsername(recipientName);

        if(friendsRefService.isExist(friendsRef)) return ReturnCode.FRIEND_ALREADY.getCode();

        return ReturnCode.SUCCESS.getCode();
    }

//    @Override
//    public Boolean isExist(UserXx userXx) {
//        if(userXx==null) return false;
//        Example example = Example.of(userXx);
//        List<UserXx> userXxOptional = userXxRepository.findAll(example);
//        return !userXxOptional.isEmpty();
//    }
}
