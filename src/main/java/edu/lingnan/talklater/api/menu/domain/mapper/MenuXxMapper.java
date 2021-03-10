package edu.lingnan.talklater.api.menu.domain.mapper;

import edu.lingnan.talklater.api.menu.domain.MenuXx;
import edu.lingnan.talklater.api.menu.domain.response.MenuTree;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Description:用于转换类型
 * date: 2021/3/8 16:01
 *
 * @author likunzhu
 * @since
 */
public final class MenuXxMapper {

    /**
     * MenuXx实体类转换成MenuTree实体类
     * @param menuXx
     * @return
     */
    public static MenuTree fromMenuXxToMenuTree(MenuXx menuXx){

        MenuTree menuTree = new MenuTree();

        menuTree.setId(menuXx.getId());
        menuTree.setFid(menuXx.getFid());
        menuTree.setName(menuXx.getName());
        menuTree.setUrl(menuXx.getUrl());
        menuTree.setOrder(menuXx.getOrder());
        menuTree.setIcon(menuXx.getIcon());
        return menuTree;
    }

    public static List<MenuTree> fromMenuXxListToMenuTreeList(List<MenuXx> menuXxList){
        List<MenuTree> menuTreeList = new ArrayList<>();

        for (MenuXx item: menuXxList
             ) {
            MenuTree menuTree = fromMenuXxToMenuTree(item);
            menuTreeList.add(menuTree);
        }

        return menuTreeList;
    }



}
