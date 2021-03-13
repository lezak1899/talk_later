package edu.lingnan.talklater.api.menu.domain.response;

import javax.persistence.Column;

/**
 * Description:角色菜单关联实体类
 * date: 2021/3/12 17:18
 *
 * @author likunzhu
 * @since
 */
public class RoleMenu {

    /**
     * 角色Id
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 角色菜单关联id
     */
    @Column(name = "role_menu_id")
    private String roleMenuId;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private String menuId;

    /**
     * 父级菜单id
     */
    @Column(name = "fid")
    private String fid;

    /**
     * 菜单名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 菜单url
     */
    @Column(name = "url")
    private String url;

    /**
     * 菜单备注
     */
    @Column(name = "bz")
    private String bz;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(String roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
