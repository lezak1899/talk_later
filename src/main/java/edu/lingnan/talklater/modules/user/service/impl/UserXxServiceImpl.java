package edu.lingnan.talklater.modules.user.service.impl;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.repository.UserXxRepository;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Optional;

/**
 * Description:
 * date: 2020/12/27 20:14
 * author likunzhu
 * version
 * since JDK 1.8
 */
@Service
public class UserXxServiceImpl implements UserXxService {

    @Autowired
    private UserXxRepository userXxRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public Boolean isExist(UserXx userXx) {
        if(userXx==null) return false;
        Example example = Example.of(userXx);
        Optional<UserXx> userXxOptional = userXxRepository.findOne(example);
        return userXxOptional.isPresent();
    }

    @Override
    public UserXx queryOne(UserXx userXx) {
        Example example = Example.of(userXx);
        Optional<UserXx> userXxOptional = userXxRepository.findOne(example);
        if(userXxOptional.isPresent()){
            return userXxOptional.get();
        }
        return null;
    }

    @Override
    public Boolean register(UserXx userXx) {

        //设置系统字段
        userXx.setUsertype("1");//1为普通用户，2为管理员
        userXx.setValid(true);
        userXx.setCreatedDate(System.currentTimeMillis());

        UserXx returnUsesr= userXxRepository.save(userXx);
        if(returnUsesr==null) return false;
        return true;
    }

    @Override
    public Boolean modifyUserXx(UserXx userXx, String zdmc) {
        if(StringUtil.isNullOrEmpty(zdmc)||StringUtil.isNullOrEmpty(userXx.getId()))  return false;
        String userId = userXx.getId();
        String newValue;
        StringBuffer sql= new StringBuffer();
        sql.append(" update u_user_xx ");
        switch(zdmc){
            case "nickname" :
                sql.append(" set nickname = ? ");
                newValue=userXx.getNickname();
                if(StringUtil.isNullOrEmpty(newValue)) return false;
                break;
            case "password" :
                sql.append(" set password = ? ");
                newValue=userXx.getPassword();
                if(StringUtil.isNullOrEmpty(newValue)) return false;
                break;
            default :
                return false;
        }
        sql.append(" where id = ?");
        jdbcTemplate.update(sql.toString(),new Object[]{newValue},new int[]{Types.VARCHAR,Types.VARCHAR});

        return null;
    }
}
