package edu.lingnan.talklater.modules.stat.repository;


import edu.lingnan.talklater.modules.stat.domain.StatXx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 * date: 2021/3/16 13:58
 *
 * @author likunzhu
 * @since
 */
@Repository
public interface StatRepository extends JpaRepository<StatXx, String>, JpaSpecificationExecutor<StatXx>{
}




