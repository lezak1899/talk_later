package edu.lingnan.talklater.modules.info.service.impl;

import edu.lingnan.talklater.modules.info.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Description:
 * date: 2020/12/27 17:59
 * author likunzhu
 * version
 * since JDK 1.8
 */

@Service
@Transactional
public class InfoServiceImpl implements InfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String,Object> test(){

        StringBuffer sql=new StringBuffer("select * from u_user_xx");

        Map<String,Object> result = jdbcTemplate.queryForMap(sql.toString());

        return result;

    }
}
