package edu.lingnan.talklater.modules.msg.repository;

import edu.lingnan.talklater.modules.msg.domain.MsgXx;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 * date: 2021/2/20 14:01
 *
 * @author likunzhu
 * @since
 */
@Repository
public interface MsgRepository extends JpaRepository<MsgXx, String>, JpaSpecificationExecutor<MsgXx> {

    @Query(" select m from MsgXx m")
    List<MsgXx> findAllMsgXx();

}

