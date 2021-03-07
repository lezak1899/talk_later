package edu.lingnan.talklater.modules.friendsref.service.impl;

import edu.lingnan.talklater.modules.chat.Enum.MsgActionEnum;
import edu.lingnan.talklater.modules.chat.domain.DataContent;
import edu.lingnan.talklater.modules.chat.domain.UserChannelRel;
import edu.lingnan.talklater.modules.chat.netty.ChatHandler;
import edu.lingnan.talklater.modules.friendsref.domain.vo.FriendsRefVo;
import edu.lingnan.talklater.modules.friendsref.repository.FriendsRefRepository;
import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
import edu.lingnan.talklater.modules.msg.domain.MsgXx;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;

/**
 * Description:
 * date: 2021/2/4 16:02
 *
 * @author likunzhu
 * @since
 */
@Transactional
@Service
public class FriendsRefServiceImpl implements FriendsRefService {

    @Autowired
    private FriendsRefRepository friendsRefRepository;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean isExist(FriendsRef friendsRef) {
        if (friendsRef==null) return false;
        Example example = Example.of(friendsRef);
        List<FriendsRef> friendsRefs = friendsRefRepository.findAll(example);
        return !friendsRefs.isEmpty();
    }

    @Override
    public Boolean becomeFriends(String username, String friendUsername) {

        if (StringUtil.isNullOrEmpty(username)||StringUtil.isNullOrEmpty(friendUsername)) return false;
        FriendsRef friendsRef = new FriendsRef();
        friendsRef.setUsername(username);
        friendsRef.setFriendUsername(friendUsername);
        friendsRef.setValid("1");
        friendsRef.setCreatedDate(System.currentTimeMillis());
        friendsRefRepository.saveAndFlush(friendsRef);
        return true;
    }

    @Override
    public List<FriendsRefVo> queryFriendList(String username) {

        if(StringUtil.isNullOrEmpty(username)) return null;

        StringBuffer sql = new StringBuffer();

        sql.append(" select * from v_friend_ref where is_valid = '1' and username = ?");

        List<FriendsRefVo> friendsRefVoList= jdbcTemplate.query(sql.toString(),new Object[]{username},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(FriendsRefVo.class));

        return friendsRefVoList;
    }

    @Override
    public int delFriend(String myUsername, String friendUsername) {

        StringBuffer sql = new StringBuffer();
        sql.append(" update u_user_friend_ref set is_valid = '0' ");
        sql.append(" where is_valid = '1' and ((username = ? && friend_username = ?) ||");
        sql.append(" (friend_username = ? && username = ?));");

        int result =jdbcTemplate.update(sql.toString(),
                new Object[]{myUsername,friendUsername,myUsername,friendUsername},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});

        if(result==2){

            DataContent dataContent = new DataContent();
            dataContent.setAction(MsgActionEnum.PULL_FRIEND.type);
            MsgXx msgXx = new MsgXx();
            msgXx.setSenderUsername(myUsername);
            msgXx.setRecipientUsername(friendUsername);
            dataContent.setChatMsg(msgXx);

            //进行消息的推送,通过接受者的Username找到对应的的Channal

            Channel recipientChannel = UserChannelRel.get(friendUsername);
            if (recipientChannel!=null){
                recipientChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dataContent)));//如果对方在线就直接推送消息
            }


        }

        return result;
    }

}
