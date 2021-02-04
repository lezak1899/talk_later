package edu.lingnan.talklater.modules.friendsref.service.impl;

import edu.lingnan.talklater.modules.friendsref.repository.FriendsRefRepository;
import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Boolean isExist(FriendsRef friendsRef) {
        if (friendsRef==null) return false;
        Example example = Example.of(friendsRef);
        List<FriendsRef> friendsRefs = friendsRefRepository.findAll(example);
        return !friendsRefs.isEmpty();
    }

}
