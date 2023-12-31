package edu.lingnan.talklater.modules.user.service;


import edu.lingnan.talklater.api.user.domain.request.UserQueryEntity;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.domain.dto.request.UserFaceImgRequestDto;
import edu.lingnan.talklater.modules.user.domain.dto.response.UserXxResponseDTO;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2020/12/27 20:14
 * author likunzhu
 * version
 * since JDK 1.8
 */
public interface UserXxService {



    /**
     * @description:用户名是否存在
     * @author: likunzhu
     * @date:
     */
    public Boolean isExist(UserXx userXx);

    /**
     * @description:用户名是否存在
     * @author: likunzhu
     * @date:
     */
    public Boolean isExistByUsername(String username);

    /**
     * @description:
     * @author: likunzhu
     * @date:
     * @return
     */
    public UserXx queryOne(UserXx userXx);

    /**
     * @description:
     * @author: likunzhu
     * @date:
     * @return
     */
    public UserXx login(UserXx userXx);


    /**
     * @description:注册
     * @author: likunzhu
     * @date:
     * @return
     */
    public int register(UserXx userXx);


    /**
     * @description:通过zdmc修改特定字段的值
     * @author: likunzhu
     * @date:
     * @return
     */
    public Boolean  modifyUserXxByZdmc (UserXx userXx,String zdmc);


    /**
     * 分页查询
     */
    public Page<UserXx> queryUserPage(UserQueryEntity queryEntity);


    /**
     * 管理端修改属性的service方法
     * @param userId
     * @param zdmc
     * @param value
     * @return
     */
    public UserXx modifyByZdmc(String userId, String zdmc,String value);

    /**
     * 修改用户信息
     */
    public Boolean modifyUsesr(UserXx userXx);



    /**
     * 查询用户信息接口
     */
    public UserXxResponseDTO checkUserInfo(String userId);

    /**
     * 多表关联分页查询测试
     * @param pageable
     * @return
     */
    public Page<Map<String,Object>>  queryUserPageTest(Pageable pageable);


    /**
     * 头像上传
     * @return
     */
    public UserXx faceImgUpload(UserFaceImgRequestDto userFaceImgRequestDto);

    /**
     * 校验当前登录用户对应的角色是否被禁用
     * @return
     */
    public Boolean checkRoleValid(String userId);

}
