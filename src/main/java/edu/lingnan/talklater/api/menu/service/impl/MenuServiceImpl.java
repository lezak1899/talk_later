package edu.lingnan.talklater.api.menu.service.impl;

import edu.lingnan.talklater.api.menu.domain.MenuXx;
import edu.lingnan.talklater.api.menu.domain.mapper.MenuXxMapper;
import edu.lingnan.talklater.api.menu.domain.response.MenuTree;
import edu.lingnan.talklater.api.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/3/8 15:47
 *
 * @author likunzhu
 * @since
 */
@Transactional
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有菜单列表
     */
    public List<MenuTree> getMenuList(String userId){

        //最终返回的数据
        List<MenuTree> result;

        /**
         * 遍历一级菜单
         */
        StringBuffer sql = new StringBuffer();
        sql.append(" select umx.* from ");
        sql.append(" (select id ,username from u_user_xx  where id = ? and is_valid = '1') uux");
        sql.append(" left join (select user_id ,role_id from  u_user_role  where is_valid = '1') uur on (uux.id = uur.user_id ) ");
        sql.append(" left join (select id ,name from u_role_xx where is_valid ='1') urx on (uur.role_id=urx.id)");
        sql.append(" left join (select role_id ,menu_id from u_role_menu where is_valid = '1') urm on (urm.role_id = urx.id)");
        sql.append(" left join (select * from u_menu_xx umx where is_valid='1') umx on (umx.id = urm.menu_id)");
        sql.append(" where f_id = '1' order by 'order'");
        List<MenuXx> sqlResult = jdbcTemplate.query(sql.toString(),new Object[]{userId},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(MenuXx.class));
        result = MenuXxMapper.fromMenuXxListToMenuTreeList(sqlResult);

        /**
         * 遍历二级菜单
         */
        for (MenuTree item: result
             ) {
            String menuId = item.getId();
            StringBuffer querySql = new StringBuffer();
            querySql.append(" select * from u_menu_xx umx where f_id = ? and is_valid='1'");
            List<MenuXx> queryResult = jdbcTemplate.query(querySql.toString(),new Object[]{menuId},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(MenuXx.class));

            item.setChilds(queryResult);


        }


        return result;
    }
}
