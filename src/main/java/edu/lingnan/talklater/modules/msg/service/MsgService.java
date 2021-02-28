package edu.lingnan.talklater.modules.msg.service;

import edu.lingnan.talklater.modules.msg.domain.MsgXx;

import java.util.List;

/**
 * Description:
 * date: 2021/2/20 14:01
 *
 * @author likunzhu
 * @since
 */
public interface MsgService {

    /**
     * 新增消息
     * @param senderUsername
     * @param recipientUsernmae
     * @param msg
     * @param setReadedFlag
     */
    public MsgXx addMsg(String senderUsername,String recipientUsernmae,String msg,String setReadedFlag);

    /**
     * 通过接受者查收未接受消息
     * @param recipientUsername
     * @return
     */
    public List<MsgXx> queryMsg(String recipientUsername);


    public void batchSign(List<String> ids);
}
