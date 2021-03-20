package edu.lingnan.talklater.modules.task.repository;

import edu.lingnan.talklater.modules.task.domain.TaskXx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * StaskXx仓库
 * @author likunzhu
 */

@Repository
public interface TaskXxRepository extends JpaRepository<TaskXx, String>, JpaSpecificationExecutor<TaskXx> {

}