/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.api.menu.repository;

import edu.lingnan.talklater.api.menu.domain.MenuXx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



/**
 * MenuXx仓库
 * @author likunzhu
 */
public interface MenuXxRepository extends JpaRepository<MenuXx, String>, JpaSpecificationExecutor<MenuXx> {

}