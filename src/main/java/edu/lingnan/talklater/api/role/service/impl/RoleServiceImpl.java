package edu.lingnan.talklater.api.role.service.impl;

import edu.lingnan.talklater.api.role.domiain.Request.RoleQueryEntity;
import edu.lingnan.talklater.api.role.domiain.RoleXx;
import edu.lingnan.talklater.api.role.repository.RoleXxRepository;
import edu.lingnan.talklater.api.role.service.RoleService;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;

/**
 * Description:
 * date: 2021/3/11 17:44
 *
 * @author likunzhu
 * @since
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleXxRepository roleXxRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<RoleXx> queryRolePage(RoleQueryEntity queryEntity) {
        //定义过滤模板的实体类
        RoleXx roleXx =new RoleXx();

        //过滤条件
        if(queryEntity.getRoleName()!=""){
            roleXx.setName(queryEntity.getRoleName());
        }

        //生成example
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<RoleXx> example = Example.of(roleXx, matcher);

        //分页，并且通过created_date字段进行降序排序
        PageRequest of = PageRequest.of(queryEntity.getPageNum()-1, queryEntity.getPageSize(), Sort.Direction.DESC, "createdDate");

        Page<RoleXx> roleXxPage = roleXxRepository.findAll(example,of);

        return roleXxPage;
    }

    @Override
    public RoleXx modifyByZdmc(String roleId, String zdmc, String value) {
        StringBuffer sql= new StringBuffer();
        sql.append(" update u_role_xx ");
        switch(zdmc){
            case "is_valid" :
                sql.append(" set is_valid = ? ");
                break;
        }
        sql.append(" where id = ?");
        int n= jdbcTemplate.update(sql.toString(),new Object[]{value,roleId},new int[]{Types.VARCHAR,Types.VARCHAR});

        if(n<1) return null;

        RoleXx roleXx = roleXxRepository.findById(roleId).get();

        return roleXx;
    }



}
