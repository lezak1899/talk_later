package edu.lingnan.response;

/**
 * Description:
 * date: 2020/10/28 15:31
 *
 * @author likunzhu
 * @since
 */
public enum ReturnCode {

    /**
     * 请求成功
     */
    USER_NOTFOUND(11, "登录账号不存在！"),

    /**
     * 请求成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 请求失败
     */
    FAIL(1, "请求失败"),
    /**
     * 找不到与asid相匹配的文件
     */
    FILE_NOTFOUND(2, "找不到与asid相匹配的文件"),

    /**
     * 爱数账号验证失败
     */
    SIGN_ERROR(3, "爱数账号验证失败"),

    /**
     * 附件模块系统异常
     */
    SYSTEM_ERROR(4, "附件模块系统异常");



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


}
