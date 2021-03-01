package edu.lingnan.talklater.modules.friendsref.service;

import edu.lingnan.talklater.modules.friendsref.domain.vo.FriendsRefVo;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;

import java.util.List;

/**
 * Description:
 * date: 2021/2/4 16:01
 *
 * @author likunzhu
 * @since
 */
public interface FriendsRefService {

    /**
     * 按照用户名判断，目标用户是否已是好友
     */
    Boolean isExist(FriendsRef friendsRef);

    /**
     * 互相成为朋友
     */
    Boolean becomeFriends(String username,String friendUsername);

    /**
     * 查询好友列表
     */
    List<FriendsRefVo> queryFriendList(String username);

    /**
     * 删除好友
     */
    int delFriend(String myUsername,String friendUsername);
}
