package edu.lingnan.talklater.api.user.domain.request;

/**
 * Description: 分页信息实体类
 * date: 2021/3/9 22:47
 *
 * @author likunzhu
 * @since
 */
public class QueryEntity {

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

    /**
     * 当前查询页下表
     */
    private int pageNum;

    /**
     * 每页包含多少条数据
     */
    private int pageSize;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
