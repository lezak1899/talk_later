package edu.lingnan.talklater.modules.user.service.impl;

import edu.lingnan.talklater.api.user.domain.request.UserQueryEntity;
import edu.lingnan.talklater.modules.user.domain.RoleDict;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.domain.dto.request.UserFaceImgRequestDto;
import edu.lingnan.talklater.modules.user.domain.dto.response.UserXxResponseDTO;
import edu.lingnan.talklater.modules.user.domain.mapper.UserXxMapper;
import edu.lingnan.talklater.modules.user.repository.UserXxRepository;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import edu.lingnan.talklater.request.QueryEntity;
import edu.lingnan.talklater.response.ReturnCode;
import edu.lingnan.talklater.utils.Base64Util;
import edu.lingnan.talklater.utils.FileUtil;
import edu.lingnan.talklater.utils.QRCodeUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Description:
 * date: 2020/12/27 20:14
 * author likunzhu
 * version
 * since JDK 1.8
 */
@Service
@Transactional
public class UserXxServiceImpl implements UserXxService {


    @Autowired
    private UserXxRepository userXxRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QRCodeUtil qrCodeUtil;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private Base64Util base64Util;


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
        if(users.isEmpty()) return null;
        UserXx currentUser = users.get(0);

        currentUser.setLastLoginEquipment(userXx.getLastLoginEquipment());
        currentUser.setLastLoginDate(System.currentTimeMillis());

        StringBuffer sql = new StringBuffer(" update u_user_xx set last_login_date = ? , last_login_equipment = ? where id = ?");
        jdbcTemplate.update(sql.toString(),new Object[]{System.currentTimeMillis(),userXx.getLastLoginEquipment(), currentUser.getId()},new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});

        return currentUser;
    }


    /**
     * 注册校验
     * 1、账号需要由字母或者数字组成，并且不能是纯数字，或者纯字母，并且长度限定8~10位
     * 2、密码需要由字母或者数字组成，并且长度限定为6位
     * 3、遍历数据库，判断是否和已存在的账户冲突
     * @param userXx
     * @return
     */
    @Override
    public int register(UserXx userXx) {

        if(userXx == null) return ReturnCode.PARAM_NULL.getCode();

        //1、账号需要由字母或者数字组成，并且不能是纯数字，或者纯字母，并且长度限定为8位
        String userAcountVerify  = "^[0-9A-Za-z]{8,10}$";
        if(!userXx.getUsername().matches(userAcountVerify))
            return ReturnCode.REGISTER_ACOUNT_ERROR.getCode();

        //2、密码需要由字母或者数字组成，并且不能是纯数字，或者纯字母，并且长度限定为6位
        String passwAcountVerify  = "^[0-9A-Za-z]{6,6}$";
        if(!userXx.getPassword().matches(passwAcountVerify))
            return ReturnCode.REGISTER_PASSWORD_ERROR.getCode();

        //3、遍历数据库，判断是否和已存在的账户冲突
        UserXx verifyUserXx=new UserXx();
        verifyUserXx.setUsername(userXx.getUsername());
        if(isExist(verifyUserXx)) return ReturnCode.USERNAME_HAS_BEEN_USE.getCode();



        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //设置系统字段
        userXx.setUsertype(userXx.getUsertype());//1为普通用户，2为运维人员，3为系统管理员
        userXx.setValid("1");
        userXx.setCreatedDate(System.currentTimeMillis());
        UserXx returnUsesr= userXxRepository.save(userXx);//将信息存入数据库中



        //绑定用户和角色
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into u_user_role (id,role_id,user_id,is_valid,created_date) values(uuid(),?,?,'1',?);");

        jdbcTemplate.update(sql.toString(),
                new Object[]{RoleDict.RoleDict(userXx.getUsertype()).getId(),returnUsesr.getId(),System.currentTimeMillis()},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});

        //生成二维码文件流register
        qrCodeUtil.createQRCodeToOutputStream(byteArrayOutputStream,returnUsesr.getUsername());
        //将二维码文件流上传到服务器，将访问路径保存到数据库中
        String path= fileUtil.uploadOutputStream(returnUsesr.getId()+"_qrcode.png",byteArrayOutputStream);//上传到服务器

        String userId=returnUsesr.getId();

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
            case "signature" :
                sql.append(" set fun_signature = ? ");
                newValue=userXx.getFunSignature();
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

    @Override
    public Page<UserXx> queryUserPage(UserQueryEntity queryEntity) {

        //定义过滤模板的实体类
        UserXx userXx =new UserXx();
        userXx.setUsertype(queryEntity.getUserType());

        //过滤条件
        if(queryEntity.getUserName()!=""){
            userXx.setUsername(queryEntity.getUserName());
        }
        if(queryEntity.getNickname()!=""){
            userXx.setNickname(queryEntity.getNickname());
        }


        //生成example
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<UserXx> example = Example.of(userXx, matcher);
        //分页，并且通过created_date字段进行降序排序
        PageRequest of = PageRequest.of(queryEntity.getPageNum()-1, queryEntity.getPageSize(), Sort.Direction.DESC, "createdDate");
        Page<UserXx> userXxPage = userXxRepository.findAll(example,of);

        return userXxPage;
    }


    @Override
    public UserXx modifyByZdmc(String userId, String zdmc,String value) {

        StringBuffer sql= new StringBuffer();
        sql.append(" update u_user_xx ");
        switch(zdmc){
            case "is_valid" :
                sql.append(" set is_valid = ? ");
                break;
        }
        sql.append(" where id = ?");
        int n= jdbcTemplate.update(sql.toString(),new Object[]{value,userId},new int[]{Types.VARCHAR,Types.VARCHAR});

        if(n<1) return null;

        UserXx userXx = userXxRepository.findById(userId).get();

        return userXx;
    }

    @Override
    public Boolean modifyUsesr(UserXx userXx) {

        StringBuffer sql = new StringBuffer();

        sql.append(" update u_user_xx set nickname = ?,");
        sql.append(" password = ? ,");
        sql.append(" sex = ?,");
        sql.append(" userType = ?");
        sql.append(" where id = ?");


        int n= jdbcTemplate.update(sql.toString(),
                new Object[]{userXx.getNickname(),userXx.getPassword(),userXx.getSex(),userXx.getUsertype(),userXx.getId()},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});

        if(n<1) return false;

        return true;
    }


    /**
     * 查询用户信息接口
     */
    @Override
    public UserXxResponseDTO checkUserInfo(String userId){
        UserXx userXx= userXxRepository.findById(userId).get();
        UserXxResponseDTO userXxResponseDTO = UserXxMapper.userXxToUserXxResponseDTO(userXx);
        return userXxResponseDTO;
    };


    /**
     * 多表关联分页查询测试
     * @param pageable
     * @return
     */
    public Page<Map<String,Object>> queryUserPageTest(Pageable pageable){
        return userXxRepository.queryUserPageTest(pageable);
    };




    /**
     * 头像上传
     */
    @Override
    public UserXx faceImgUpload(UserFaceImgRequestDto userFaceImgRequestDto){
        if(userFaceImgRequestDto==null) return null;
        ByteArrayInputStream inputStreamFace450=null;
        ByteArrayInputStream inputStreamFace80=null;

        //
        UserXx userXx = userXxRepository.findById(userFaceImgRequestDto.getUserId()).get();
        String faceImgUrlStr = userXx.getFaceImg();
        String suffix="";
        int n= faceImgUrlStr.indexOf("_1.png");
        if(n>=0){
            suffix="_2.png";
        }else{
            suffix="_1.png";
        }

        //图片名称
        String fileNameFace450 ="faceImg/450px/"+userFaceImgRequestDto.getUserId()+"_face_450"+suffix;
        String fileNameFace80 ="faceImg/80px/"+userFaceImgRequestDto.getUserId()+"_face_80"+suffix;

        //base64转字节流
        try {
            inputStreamFace450 = base64Util.base64ToIo(userFaceImgRequestDto.getFaceImgWhole());
            inputStreamFace80 = base64Util.base64ToIo(userFaceImgRequestDto.getFaceImg());

        } catch (IOException e) {
            e.printStackTrace();
        }

        fileUtil.uploadInputStream(fileNameFace450,inputStreamFace450);
        fileUtil.uploadInputStream(fileNameFace80,inputStreamFace80);

        StringBuffer sql = new StringBuffer();
        sql.append(" update u_user_xx set face_img_whole = ? , face_img = ? where id= ?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{fileNameFace450,fileNameFace80,userFaceImgRequestDto.getUserId()},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});


        StringBuffer querySql= new StringBuffer();
        querySql.append(" select * from u_user_xx where id = ? and is_valid= '1'");
        List<UserXx> res = jdbcTemplate.query(querySql.toString(),
                new Object[]{userFaceImgRequestDto.getUserId()},
                new int[]{Types.VARCHAR},new BeanPropertyRowMapper(UserXx.class));

        if(res ==null) return null;
        return res.get(0);
    };




}
