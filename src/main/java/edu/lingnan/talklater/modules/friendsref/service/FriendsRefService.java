package edu.lingnan.talklater.modules.friendsref.service;

import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;

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
}
