package edu.lingnan.talklater.modules.msg.service.impl;

import edu.lingnan.talklater.modules.msg.domain.MsgXx;
import edu.lingnan.talklater.modules.msg.repository.MsgRepository;
import edu.lingnan.talklater.modules.msg.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * date: 2021/2/20 14:06
 *
 * @author likunzhu
 * @since
 */
@Transactional
@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgRepository msgRepository;


    @Override
    public void addMsg(String senderUsername, String recipientUsernmae, String msg) {
        MsgXx msgXx = new MsgXx();
        msgXx.setSenderUsername(senderUsername);
        msgXx.setRecipientUsername(recipientUsernmae);
        msgXx.setMsg(msg);
        msgXx.setValid("1");
        msgXx.setCreatedDate(System.currentTimeMillis());
        msgXx.setWithdrawFlag("2");
        msgXx.setReadedFlag("2");
        msgRepository.saveAndFlush(msgXx);

    }
}
