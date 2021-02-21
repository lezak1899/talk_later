package edu.lingnan.talklater.modules.friendsref.service.impl;

import edu.lingnan.talklater.modules.friendsref.domain.vo.FriendsRefVo;
import edu.lingnan.talklater.modules.friendsref.repository.FriendsRefRepository;
import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import edu.lingnan.talklater.modules.user.domain.UserXx;
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

}
