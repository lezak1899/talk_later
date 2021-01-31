package edu.lingnan.talklater.modules.user.service;

import edu.lingnan.talklater.modules.user.domain.UserXx;

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
     * @description:
     * @author: likunzhu
     * @date:
     * @return
     */
    public UserXx queryOne(UserXx userXx);


    /**
     * @description:注册
     * @author: likunzhu
     * @date:
     * @return
     */
    public Boolean register(UserXx userXx);


    public Boolean  modifyUserXx (UserXx userXx,String zdmc);

}
