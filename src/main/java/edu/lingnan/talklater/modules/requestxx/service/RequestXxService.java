package edu.lingnan.talklater.modules.requestxx.service;

import edu.lingnan.talklater.modules.requestxx.domain.RequestXx;
import edu.lingnan.talklater.modules.requestxx.domain.vo.RequestXxVo;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;

import java.util.List;

/**
 * Description:
 * date: 2021/2/4 14:29
 *
 * @author likunzhu
 * @since
 */
public interface RequestXxService {

    /**
     * 对发起的请求校验，1、请求对象不能为空，2、请求对象不能是自己，3、请求对象不能是自己的好友
     * @return
     */
    int verifyRequest(String senderName,String recipientName);

    /**
     * 对发起的请求校验，1、请求对象不能为空，2、请求对象不能是自己，3、请求对象不能是自己的好友
     * @return
     */
    void addRequest(String senderName,String recipientName);


    /**
     * 返回当前用户接受到的所有未处理的好友请求
     * @return
     */
    List<RequestXxVo> queryAllRequest(String username);

    /**
     * 处理请求
     * @return
     */
    boolean handleRequest(String requestId, String actionType);
}
