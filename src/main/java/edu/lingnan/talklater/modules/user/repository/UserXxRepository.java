package edu.lingnan.talklater.modules.user.repository;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Description:
 * date: 2020/12/27 20:47
 * author likunzhu
 * version
 * since JDK 1.8
 */

@Repository
public interface UserXxRepository extends JpaRepository<UserXx, String>,JpaSpecificationExecutor<UserXx> {

    @Query(" select u from UserXx u")
    List<UserXx> findAllUser();


}
