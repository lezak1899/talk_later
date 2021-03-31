package edu.lingnan.talklater.modules.user.repository;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


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

    @Query(" select u from UserXx u where u.username = ?1 and u.password = ?2")
    List<UserXx> queryByUsernameAndPassword(String username,String password);


    /**
     * 测试关联分页
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = " select u.id from u_user_xx u left join u_user_role ur on u.id = ur.user_id ",
            countQuery = "select count(u.id) from u_user_xx u left join u_user_role ur on u.id = ur.user_id ")
    Page<Map<String, Object>> queryUserPageTest(Pageable pageable);



}
