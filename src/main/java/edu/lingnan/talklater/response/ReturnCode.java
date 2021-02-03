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
    PARAM_format_ERROR(4, "参数格式不正确");




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
            case 11:
                return "登录账号不存在！";
            case 12:
                return "密码错误！";
            default:
                return null;
        }
    }


}
