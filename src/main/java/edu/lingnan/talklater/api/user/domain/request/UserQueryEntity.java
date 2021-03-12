package edu.lingnan.talklater.api.user.domain.request;

import edu.lingnan.talklater.request.QueryEntity;

/**
 * Description: 分页信息实体类
 * date: 2021/3/9 22:47
 *
 * @author likunzhu
 * @since
 */
public class UserQueryEntity extends QueryEntity {

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickname;



    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
