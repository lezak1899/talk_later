package edu.lingnan.talklater.modules.user.service.impl;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.repository.UserXxRepository;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import edu.lingnan.talklater.response.ReturnCode;
import edu.lingnan.talklater.utils.FileUtil;
import edu.lingnan.talklater.utils.QRCodeUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.ByteArrayOutputStream;

import java.sql.Types;
import java.util.List;
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
        List<UserXx> userXxOptional = userXxRepository.findAll(example);
        return !userXxOptional.isEmpty();
    }

    @Override
    public Boolean isExistByUsername(String username) {
        if(StringUtil.isNullOrEmpty(username)) return false;

        StringBuffer sql = new StringBuffer(" select username from u_user_xx where username = ?");
        List<String> res = jdbcTemplate.query(sql.toString(),new Object[]{username},new int[]{Types.VARCHAR},new BeanPropertyRowMapper(String.class));

        return !res.isEmpty();
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
    public UserXx login(UserXx userXx) {
        if(userXx==null) return null;

        if(StringUtil.isNullOrEmpty(userXx.getLastLoginEquipment())) userXx.setLastLoginEquipment("unknown");

        List<UserXx> users= userXxRepository.queryByUsernameAndPassword(userXx.getUsername(),userXx.getPassword());
        UserXx currentUser = users.get(0);

        currentUser.setLastLoginEquipment(userXx.getLastLoginEquipment());
        currentUser.setLastLoginDate(System.currentTimeMillis());

        StringBuffer sql = new StringBuffer(" update u_user_xx set last_login_date = ? , last_login_equipment = ? where id = ?");
        jdbcTemplate.update(sql.toString(),new Object[]{System.currentTimeMillis(),userXx.getLastLoginEquipment(), currentUser.getId()},new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});

        return currentUser;
    }




    @Override
    public int register(UserXx userXx) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        if(userXx == null) return ReturnCode.PARAM_NULL.getCode();

        UserXx verifyUserXx=new UserXx();
        verifyUserXx.setUsername(userXx.getUsername());
        if(isExist(verifyUserXx)) return ReturnCode.USERNAME_HAS_BEEN_USE.getCode();

        //设置系统字段
        userXx.setUsertype("1");//1为普通用户，2为管理员
        userXx.setValid(true);
        userXx.setCreatedDate(System.currentTimeMillis());
        UserXx returnUsesr= userXxRepository.save(userXx);//将信息存入数据库中

        //生成二维码文件流
        qrCodeUtil.createQRCodeToOutputStream(byteArrayOutputStream,returnUsesr.getUsername());
        //将二维码文件流上传到服务器，将访问路径保存到数据库中
        String path= fileUtil.uploadOutputStream(returnUsesr.getId()+"_qrcode.png",byteArrayOutputStream);//上传到服务器
        System.out.println(returnUsesr.getId()+":1111111111111111111111");
        String userId=returnUsesr.getId();

//        StringBuffer sql=new StringBuffer(" update u_user_xx set qrcode = ? where id = ?");
//        jdbcTemplate.update(sql.toString(),new Object[]{path,returnUsesr.getId()},new int[]{Types.VARCHAR,Types.VARCHAR});

        returnUsesr.setQrcode(path);
        userXxRepository.save(returnUsesr);

        return ReturnCode.SUCCESS.getCode();
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
