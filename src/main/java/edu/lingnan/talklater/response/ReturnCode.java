package edu.lingnan.talklater.response;

/**
 * Description:
 * date: 2020/10/28 15:31
 *
 * @author likunzhu
 * @since
 */
public enum ReturnCode {

    /**
     * 登录账号不存在！
     */
    USER_NOTFOUND(11, "登录账号不存在！"),

    /**
     * 密码错误！
     */
    USER_PASSWOED_ERROR(12, "密码错误！"),

    /**
     * 请求成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 请求失败
     */
    FAIL(1, "请求失败"),
    /**
     *该账号已经被注册
     */
    USERNAME_HAS_BEEN_USE(2, "该账号已经被注册"),

    /**
     * 参数为不能空
     */
    PARAM_NULL(3, "参数为不能空"),

    /**
     * 参数格式不正确
     */
    PARAM_FORMAT_ERROR(4, "参数格式不正确"),

    /**
     * 不能添加自己为好友
     */
    IS_YOURSELF(5, "不能添加自己为好友"),

    /**
     * Ta已经是您的好友了
     */
    FRIEND_ALREADY(6, "Ta已经是您的好友了"),

    /**
     * 好友账号不能为空
     */
    FRIEND_USERNAME_NULL(7, "好友账号不能为空"),

    /**
     * 查询不到该用户
     */
    FRIEDN_NOTFOUND(8, "查询不到该用户"),

    /**
     * 已发送好友请求，请勿重复提交！
     */
    ERROR_RESUBMIT(9, "已发送好友请求，请勿重复提交！");







    private final Integer code;
    private final String msg;


    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public static String getMsg(int code){
        switch (code) {
            case 1:
                return "请求失败";
            case 2:
                return "该账号已经被注册";
            case 3:
                return "参数为不能空";
            case 4:
                return "参数格式不正确";
            case 5:
                return "不能添加自己为好友";
            case 6:
                return "对方已经是您的好友";
            case 7:
                return "好友账号不能为空";
            case 8:
                return "查询不到该用户";
            case 9:
                return "请勿重复提交！";
            case 11:
                return "登录账号不存在！";
            case 12:
                return "密码错误！";
            default:
                return null;
        }
    }


}
