package edu.lingnan.talklater.modules.chat.netty;

import edu.lingnan.talklater.modules.chat.Enum.MsgActionEnum;
import edu.lingnan.talklater.modules.chat.domain.DataContent;
import edu.lingnan.talklater.modules.chat.domain.UserChannelRel;

import edu.lingnan.talklater.modules.msg.service.MsgService;
import edu.lingnan.talklater.utils.JsonUtils;

import edu.lingnan.talklater.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * date: 2020/12/8 21:08
 * author likunzhu
 * version
 * since JDK 1.8
 */
//TextWebSocketFrame：专门为websocket处理文本的对象，frame是消息载体
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private SpringUtil springUtil;

    //用于记录和管理所有客户端,所有在线的用户的channel都会存储到这里
     private static ChannelGroup users=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String content=textWebSocketFrame.text();

        DataContent dataContent = JsonUtils.jsonToPojo(content,DataContent.class);

        //当前发送消息的Channel
        Channel currentChannel = channelHandlerContext.channel();

        String senderUsername = dataContent.getChatMsg().getSenderUsername();
        String recipientUsername = dataContent.getChatMsg().getRecipientUsername();
        String msgText = dataContent.getChatMsg().getMsg();

        System.out.println("接受的数据为："+ content);

        int actionType = dataContent.getAction();

        //依据不同的actionType，执行相应的逻辑
        if(actionType== MsgActionEnum.CONNECT.type){
            //action为初次连接
            //将username和channel关联起来
            UserChannelRel.put(senderUsername,currentChannel);

            // 测试
            for (Channel c : users) {
                System.out.println(c.id().asLongText());
            }
            UserChannelRel.output();

        }else if(actionType== MsgActionEnum.CHAT.type){
            //action为聊天



            //将聊天信息保存到据库，spring会将类名的首字母小写
            MsgService msgService = (MsgService) springUtil.getBean("msgServiceImpl");
            msgService.addMsg(senderUsername,recipientUsername,msgText);

            //进行消息的推送,通过接受者的Username找到对应的的Channal
            Channel recipientChannel = UserChannelRel.get(recipientUsername);

            if (recipientChannel==null){
                //用户已经离线,离线推送信息
            }else{
                Channel findChannel = users.find(recipientChannel.id());//有疑问

                if (findChannel!=null){
                    findChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(msgText)));//有疑问
                }else {
                    //用户已经离线,离线推送信息
                }

            }


        }else if(actionType== MsgActionEnum.SIGNED.type){
            //action为签收信息

            String msgIdsStr = dataContent.getExtand();
            String msgIds[] = msgIdsStr.split(",");

            List<String> msgIdList = new ArrayList<>();
            for (String mid : msgIds) {
                if (StringUtil.isNullOrEmpty(mid)) {
                    msgIdList.add(mid);
                }
            }

            System.out.println(msgIdList.toString());

            if (msgIdList != null && !msgIdList.isEmpty() && msgIdList.size() > 0) {
                // 批量签收,更新数据库字段
//                userService.updateMsgSigned(msgIdList);
            }

        }else if(actionType== MsgActionEnum.KEEPALIVE.type){
            //action为保持心跳
            System.out.println("收到来自channel为[" + currentChannel + "]的心跳包...");

        }else if(actionType== MsgActionEnum.PULL_FRIEND.type){
            //action为拉取好友

        }



    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
       users.remove(ctx.channel());
    }

    //发生异常，打印异常信息，关闭channel，并移除
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
