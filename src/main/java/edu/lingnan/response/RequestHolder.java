package edu.lingnan.response;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 用于获得当前请求体，当前相应体
 * @author: likunzhu
 * @date:
 */
public class RequestHolder {
    /**
     * 获取当前请求
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    /**
     * 获取当前线程响应
     * @return
     */
    public static HttpServletResponse getCurrentResponse(){
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
    }


}