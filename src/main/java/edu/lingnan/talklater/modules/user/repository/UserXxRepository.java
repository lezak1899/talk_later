package edu.lingnan.talklater.modules.user.repository;

import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


/**
 * Description:
 * date: 2020/12/27 20:47
 * author likunzhu
 * version
 * since JDK 1.8
 */

@Repository
public interface UserXxRepository extends JpaRepository<UserXx, String> {

    @Query(" select u from UserXx u")
    List<UserXx> findAllUser();

}
