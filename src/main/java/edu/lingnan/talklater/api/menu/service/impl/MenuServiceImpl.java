package edu.lingnan.talklater.api.menu.service.impl;

import edu.lingnan.talklater.api.menu.domain.MenuXx;
import edu.lingnan.talklater.api.menu.domain.mapper.MenuXxMapper;
import edu.lingnan.talklater.api.menu.domain.request.MenuQueryEntity;
import edu.lingnan.talklater.api.menu.domain.response.MenuOption;
import edu.lingnan.talklater.api.menu.domain.response.MenuTree;
import edu.lingnan.talklater.api.menu.domain.response.RoleMenu;
import edu.lingnan.talklater.api.menu.repository.MenuXxRepository;
import edu.lingnan.talklater.api.menu.service.MenuService;
import edu.lingnan.talklater.api.role.domiain.RoleXx;
import edu.lingnan.talklater.response.ReturnCode;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private MenuXxRepository menuXxRepository;

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
        sql.append(" where f_id = '1' order by umx.order");
        List<MenuXx> sqlResult = jdbcTemplate.query(sql.toString(),new Object[]{userId},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(MenuXx.class));
        result = MenuXxMapper.fromMenuXxListToMenuTreeList(sqlResult);

        /**
         * 遍历二级菜单
         */
        for (MenuTree item: result
             ) {
            String menuId = item.getId();
            StringBuffer querySql = new StringBuffer();
            querySql.append(" select umx.* from ");
            querySql.append(" (select id ,username from u_user_xx  where id = ? and is_valid = '1') uux");
            querySql.append(" left join (select user_id ,role_id from  u_user_role  where is_valid = '1') uur on (uux.id = uur.user_id ) ");
            querySql.append(" left join (select id ,name from u_role_xx where is_valid ='1') urx on (uur.role_id=urx.id)");
            querySql.append(" left join (select role_id ,menu_id from u_role_menu where is_valid = '1') urm on (urm.role_id = urx.id)");
            querySql.append(" left join (select * from u_menu_xx umx where is_valid='1') umx on (umx.id = urm.menu_id)");
            querySql.append(" where f_id = ? order by umx.order");
//            querySql.append(" select * from u_menu_xx umx where f_id = ? and is_valid='1'");
            List<MenuXx> queryResult = jdbcTemplate.query(querySql.toString(),new Object[]{userId,menuId},new int[]{Types.VARCHAR,Types.VARCHAR},new BeanPropertyRowMapper(MenuXx.class));

            item.setChilds(queryResult);
        }
        
        return result;
    }

    @Override
    public Page<MenuXx> queryMenuPage(MenuQueryEntity queryEntity) {
        //定义过滤模板的实体类
        MenuXx menuXx =new MenuXx();

        //过滤条件
        if(queryEntity.getMenuName()!=""){
            menuXx.setName(queryEntity.getMenuName());
        }

        //生成example
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<MenuXx> example = Example.of(menuXx, matcher);

        //分页，并且通过created_date字段进行降序排序
        PageRequest of = PageRequest.of(queryEntity.getPageNum()-1, queryEntity.getPageSize(), Sort.Direction.DESC, "createdDate");

        Page<MenuXx> menuXxPage = menuXxRepository.findAll(example,of);

        return menuXxPage;
    }

    @Override
    public MenuXx modifyByZdmc(String menuId, String zdmc, String value) {
        StringBuffer sql= new StringBuffer();
        sql.append(" update u_menu_xx ");
        switch(zdmc){
            case "is_valid" :
                sql.append(" set is_valid = ? ");
                break;
        }
        sql.append(" where id = ?");
        int n= jdbcTemplate.update(sql.toString(),new Object[]{value,menuId},new int[]{Types.VARCHAR,Types.VARCHAR});

        if(n<1) return null;

        MenuXx menuXx = menuXxRepository.findById(menuId).get();

        return menuXx;
    }

    /**
     * 按角色id查询所有菜单
     * @param roleId
     * @return
     */
    @Override
    public List<RoleMenu> queryMenuListByRoleId(String roleId) {

        StringBuffer sql = new StringBuffer();
        sql.append(" select urx.*,urm.role_menu_id,umx.* from ");
        sql.append(" (select id as role_id from u_role_xx where is_valid='1') urx ");
        sql.append(" left join (select id as role_menu_id, role_id ,menu_id from u_role_menu where is_valid='1') urm on (urm.role_id=urx.role_id)");
        sql.append(" left join (select id as menu_id ,f_id as fid,name,url,bz,created_date from  u_menu_xx where is_valid='1') umx on (urm.menu_id=umx.menu_id)");
        sql.append(" where urm.role_id=?");
        sql.append(" and umx.menu_id is not null");
        sql.append(" order by umx.created_date desc");

        List<RoleMenu> roleMenuList = jdbcTemplate.query(sql.toString(),new Object[]{roleId},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(RoleMenu.class));

        return roleMenuList;
    }

    @Override
    public List<MenuOption> queryMenuOptions(String fid) {

        if(fid==null||fid=="") return null;

        StringBuffer sql = new StringBuffer();
        sql.append(" select id as value ,name as label from u_menu_xx umx");
        sql.append(" where f_id = ?");
        sql.append(" and is_valid=1");



        List<MenuOption> menuOptions = jdbcTemplate.query(sql.toString(),new Object[]{fid},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(MenuOption.class));

        return menuOptions;
    }

    /**
     * 1、如果menuId2为空为，则是要分配一级菜单
     * 2、判断是否已经拥有此菜单
     * 3、如果menuId1，menu2都不为空则是要分配二级菜单
     * 4、判断是否已经拥有此菜单
     */
    @Override
    public int distributeMenu(String roleId, String menuId1, String menuId2) {
        //插入数据的sql
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into u_role_menu (id,role_id,menu_id,is_valid,created_date) values (uuid(),?,?,'1',?)");

        //校验数据的sql
        StringBuffer verifySql = new StringBuffer();
        verifySql.append(" select * from u_role_menu urm where role_id = ? and menu_id = ?");
        //1、如果menuId2为空为，则是要分配一级菜单
        if(StringUtil.isNullOrEmpty(menuId2)){
           //2、判断是否已经拥有此菜单
            List<Map<String,Object>> res= jdbcTemplate.queryForList(verifySql.toString(), new Object[]{roleId,menuId1}, new int[]{Types.VARCHAR,Types.VARCHAR});
            if(res.size()==0){
                jdbcTemplate.update(sql.toString(),
                        new Object[]{roleId,menuId1,System.currentTimeMillis()},
                        new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
                return ReturnCode.SUCCESS.getCode();
            }

        }else{//3、如果menuId1，menu2都不为空则是要分配二级菜单
            List<Map<String,Object>> res= jdbcTemplate.queryForList(verifySql.toString(), new Object[]{roleId,menuId2}, new int[]{Types.VARCHAR,Types.VARCHAR});
            //4、判断是否已经拥有此菜单
            if(res.size()==0) {
                jdbcTemplate.update(sql.toString(),
                        new Object[]{roleId, menuId2, System.currentTimeMillis()},
                        new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
                return ReturnCode.SUCCESS.getCode();
            }
        }
        return ReturnCode.MENU_EXSIT.getCode();
    }

    /**
     * 1、如果是删除一级菜单，则需要连带删除2级菜单
     * 2、如果是二级菜单，直接删除
     */
    @Override
    public int deleteCorrelation(String roleMenuId) {
        if(StringUtil.isNullOrEmpty(roleMenuId)) return 0;
        StringBuffer sql =new StringBuffer();
        sql.append(" delete from u_role_menu where id = ?");
        int n = jdbcTemplate.update(sql.toString(),new Object[]{roleMenuId},new int[]{Types.VARCHAR});
        return n;
    }


}
