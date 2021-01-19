package edu.lingnan.talklater.modules.user.service.impl;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.repository.UserXxRepository;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
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
}
