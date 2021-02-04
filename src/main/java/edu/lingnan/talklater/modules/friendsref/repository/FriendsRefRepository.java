package edu.lingnan.talklater.modules.friendsref.repository;

import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import edu.lingnan.talklater.modules.requestxx.domain.RequestXx;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 * date: 2021/2/4 16:03
 *
 * @author likunzhu
 * @since
 */
@Repository
public interface FriendsRefRepository extends JpaRepository<FriendsRef, String>, JpaSpecificationExecutor<FriendsRef>{
    @Query(" select f from FriendsRef f")
    List<FriendsRef> findAllRequestXx();
}


