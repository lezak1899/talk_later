package edu.lingnan.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

/**
 * Description:
 * date: 2020/10/28 15:25
 *
 * @author likunzhu
 * @since
 */


@Slf4j
public class ApiResponse  implements Serializable {


    /**响应编码：0成功；-1系统异常；*/
    private int code;

    /**响应结果描述*/
    private String message;

    /**业务数据*/
    private Map<String,Object> data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /*
     * 无业务数据的成功响应
     * @return
     */
    public static ApiResponse success() {
        return success(null);
    }

    /**
     * 带业务数据的成功响应
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse success(Map<String,Object> data) {
        ApiResponse response = new ApiResponse();
        response.setCode(ReturnCode.SUCCESS.getCode());
        response.setMessage(ReturnCode.SUCCESS.getMsg());
        response.setData(data);
        log.error("请求返回报文为：{}", response.toString());
        return response;
    }

    /**
     * 响应失败
     * @return
     */
    public static ApiResponse fail() {
        return fail(ReturnCode.FAIL.getCode(), ReturnCode.FAIL.getMsg());
    }


    /**
     * 响应失败
     * @param responseCode
     * @return
     */
    public static ApiResponse fail(ReturnCode responseCode) {
        return fail(responseCode.getCode(), responseCode.getMsg());
    }

    /**
     * 响应失败
     * @param failCode
     * @param msg
     * @return
     */
    public static ApiResponse fail(int failCode, String msg) {
        ApiResponse response = new ApiResponse();
        response.setCode(failCode);
        response.setMessage(msg);
        log.error("响应失败，返回报文为{}", response.toString());
        //设置响应头
        HttpServletResponse currentResponse = RequestHolder.getCurrentResponse();
        currentResponse.setStatus(getResponseStatus(failCode));
        return response;
    }

    /**
     * 响应失败，自定义code和msg
     * @param failCode
     * @param msg
     *  @param obj
     * @return
     */
    public static ApiResponse fail(int failCode, String msg, Map<String,Object> obj) {
        ApiResponse response = new ApiResponse();
        response.setCode(failCode);
        response.setMessage(msg);
        response.setData(obj);
        log.error("响应失败，返回报文为{}", response.toString());
        //设置响应头的status值
        HttpServletResponse currentResponse = RequestHolder.getCurrentResponse();
        currentResponse.setStatus(getResponseStatus(failCode));
        return response;
    }



    private static Integer getResponseStatus(int failCode){
        if(failCode ==ReturnCode.FAIL.getCode() || failCode ==ReturnCode.SYSTEM_ERROR.getCode()){
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }else if(failCode == ReturnCode.FILE_NOTFOUND.getCode()){
            return HttpStatus.BAD_REQUEST.value();
        }else if(failCode == ReturnCode.SIGN_ERROR.getCode()){
            return HttpStatus.UNAUTHORIZED.value();
        }else {
            return HttpStatus.OK.value();
        }
    }



}
