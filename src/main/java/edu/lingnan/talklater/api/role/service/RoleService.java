package edu.lingnan.talklater.api.role.service;

import edu.lingnan.talklater.api.role.domiain.Request.RoleQueryEntity;
import edu.lingnan.talklater.api.role.domiain.RoleXx;
import org.springframework.data.domain.Page;

/**
 * Description:
 * date: 2021/3/11 17:44
 *
 * @author likunzhu
 * @since
 */
public interface RoleService {


    /**
     * 角色分页查询
     */
    public Page<RoleXx> queryRolePage(RoleQueryEntity queryEntity);

    /**
     * 修改单个属性
     */
    public RoleXx modifyByZdmc(String roleId,String zdmc,String value);
}
