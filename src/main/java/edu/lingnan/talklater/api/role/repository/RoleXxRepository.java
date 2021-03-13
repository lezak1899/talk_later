/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.api.role.repository;

import edu.lingnan.talklater.api.role.domiain.RoleXx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * RoleXx仓库
 * @author likunzhu
 */
@Repository
public interface RoleXxRepository extends JpaRepository<RoleXx, String>, JpaSpecificationExecutor<RoleXx> {

}