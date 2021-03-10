package edu.lingnan.talklater.api.menu.service;

import edu.lingnan.talklater.api.menu.domain.response.MenuTree;

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
     * 获取所有菜单列表
     */
    public List<MenuTree> getMenuList(String userId);

}

