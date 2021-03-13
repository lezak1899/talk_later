package edu.lingnan.talklater.api.role.domiain.Request;


import edu.lingnan.talklater.request.QueryEntity;

/** 角色查询的实体类
 * Description:
 * date: 2021/3/12 10:31
 *
 * @author likunzhu
 * @since
 */
public class RoleQueryEntity extends QueryEntity {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
