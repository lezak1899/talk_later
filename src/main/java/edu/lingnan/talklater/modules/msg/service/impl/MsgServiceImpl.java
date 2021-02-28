package edu.lingnan.talklater.modules.msg.service.impl;

import edu.lingnan.talklater.modules.msg.domain.MsgXx;
import edu.lingnan.talklater.modules.msg.repository.MsgRepository;
import edu.lingnan.talklater.modules.msg.service.MsgService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public MsgXx addMsg(String senderUsername, String recipientUsernmae, String msg,String setReadedFlag) {
        MsgXx msgXx = new MsgXx();
        msgXx.setSenderUsername(senderUsername);
        msgXx.setRecipientUsername(recipientUsernmae);
        msgXx.setMsg(msg);
        msgXx.setValid("1");
        msgXx.setCreatedDate(System.currentTimeMillis());
        msgXx.setWithdrawFlag("2");
        msgXx.setReadedFlag(setReadedFlag);
        return msgRepository.saveAndFlush(msgXx);
    }

    @Override
    public List<MsgXx> queryMsg(String recipientUsername) {

        if(StringUtil.isNullOrEmpty(recipientUsername)) return null;

        MsgXx msgXx = new MsgXx();
        msgXx.setValid("1");
        msgXx.setReadedFlag("2");//未读
        msgXx.setWithdrawFlag("2");//未撤回
        msgXx.setRecipientUsername(recipientUsername);
        Example example = Example.of(msgXx);

        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        List <MsgXx> list = msgRepository.findAll(example,sort);

        return list;
    }

    @Override
    public void batchSign(List<String> ids) {
        if(ids.isEmpty()||ids==null) return;
        msgRepository.batchSign(ids);
    }


}
