package edu.lingnan.talklater.modules.requestxx.service;

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
}
