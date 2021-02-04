package edu.lingnan.talklater.modules.requestxx.repository;

import edu.lingnan.talklater.modules.requestxx.domain.RequestXx;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 * date: 2021/2/4 14:31
 *
 * @author likunzhu
 * @since
 */
@Repository
public interface RequestXxRepository extends JpaRepository<RequestXx, String>, JpaSpecificationExecutor<RequestXx>{

    @Query(" select r from RequestXx r")
    List<RequestXx> findAllRequestXx();

}


