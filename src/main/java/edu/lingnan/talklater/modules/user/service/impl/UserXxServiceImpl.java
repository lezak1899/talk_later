package edu.lingnan.talklater.modules.user.service.impl;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.repository.UserXxRepository;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import edu.lingnan.talklater.utils.FileUtil;
import edu.lingnan.talklater.utils.QRCodeUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Types;
import java.util.Optional;

/**
 * Description:
 * date: 2020/12/27 20:14
 * author likunzhu
 * version
 * since JDK 1.8
 */
@Transactional
@Service
public class UserXxServiceImpl implements UserXxService {



    @Autowired
    private UserXxRepository userXxRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QRCodeUtil qrCodeUtil;

    @Autowired
    private FileUtil fileUtil;



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
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        if(userXx == null) return false;


        //设置系统字段
        userXx.setUsertype("1");//1为普通用户，2为管理员
        userXx.setValid(true);
        userXx.setCreatedDate(System.currentTimeMillis());

        //生成二维码图片流
        qrCodeUtil.createQRCodeToOutputStream(byteArrayOutputStream,userXx.getUsername());

        UserXx returnUsesr= userXxRepository.save(userXx);//将信息存入数据库中

        //将二维码图片上传到服务器，将访问路径保存到数据库中
        String path= fileUtil.uploadOutputStream(returnUsesr.getId()+"_qrcode.png",byteArrayOutputStream);//上传到服务器
        returnUsesr.setQrcode(path);
        userXxRepository.save(returnUsesr);


        if(returnUsesr==null) return false;
        return true;
    }



    @Override
    public Boolean modifyUserXxByZdmc(UserXx userXx, String zdmc) {
        if(userXx==null||StringUtil.isNullOrEmpty(zdmc)||StringUtil.isNullOrEmpty(userXx.getId()))  return false;
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
        int n= jdbcTemplate.update(sql.toString(),new Object[]{newValue,userId},new int[]{Types.VARCHAR,Types.VARCHAR});

        if(n<1) return false;
        return true;
    }


}
