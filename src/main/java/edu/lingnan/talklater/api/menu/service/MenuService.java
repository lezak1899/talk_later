package edu.lingnan.talklater.api.menu.service;

import edu.lingnan.talklater.api.menu.domain.MenuXx;
import edu.lingnan.talklater.api.menu.domain.request.MenuQueryEntity;
import edu.lingnan.talklater.api.menu.domain.response.MenuOption;
import edu.lingnan.talklater.api.menu.domain.response.MenuTree;
import edu.lingnan.talklater.api.menu.domain.response.RoleMenu;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Description:
 * date: 2021/3/8 11:11
 *
 * @author likunzhu
 * @since
 */
public interface MenuService {

    /**
     * 获取用户的所有菜单列表
     */
    public List<MenuTree> getMenuList(String userId);


    /**
     * 分页查询所有菜单列表
     * @param queryEntity
     * @return
     */
    public Page<MenuXx> queryMenuPage(MenuQueryEntity queryEntity);

    /**
     * 修改单个字段
     */
    public MenuXx modifyByZdmc(String menuId,String zdmc,String value);

    /**
     * 按角色id查询菜单列表
     */
    public List<RoleMenu> queryMenuListByRoleId(String roleId);

    /**
     * 查询菜单选项
     */
    public List <MenuOption>  queryMenuOptions(String fid);

    /**
     * 分配权限
     */
    public int distributeMenu(String roleId,String menuId1,String menuId2);

    /**
     * 删除角色菜单关联
     */
    public int deleteCorrelation(String roleMenuId);

}

