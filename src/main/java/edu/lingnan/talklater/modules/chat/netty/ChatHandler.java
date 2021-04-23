package edu.lingnan.talklater.modules.chat.netty;

import edu.lingnan.talklater.modules.chat.Enum.MsgActionEnum;
import edu.lingnan.talklater.modules.chat.domain.DataContent;
import edu.lingnan.talklater.modules.chat.domain.UserChannelRel;

import edu.lingnan.talklater.modules.msg.domain.MsgXx;
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
import org.springframework.stereotype.Component;

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
     public static ChannelGroup users=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        //当前发送消息的Channel
        Channel currentChannel = channelHandlerContext.channel();

        //获得msgService
        MsgService msgService = (MsgService) springUtil.getBean("msgServiceImpl");

        //接收到的消息体
        String content=textWebSocketFrame.text();
        DataContent dataContent = JsonUtils.jsonToPojo(content,DataContent.class);

        System.out.println("接受的数据为："+ content);

        int actionType = dataContent.getAction();

        /**
         * 依据不同的actionType，执行相应的逻辑
         * 1、action为初次连接，建立连接前需要做“重复登录”校验
         */
        if(actionType== MsgActionEnum.CONNECT.type){
            String senderUsername = dataContent.getChatMsg().getSenderUsername();
            String recipientUsername = dataContent.getChatMsg().getRecipientUsername();
            String msgText = dataContent.getChatMsg().getMsg();

            //校验当前账号是否已经登录在其他设备，
            Channel recipientChannel= UserChannelRel.get(dataContent.getChatMsg().getSenderUsername());
            if (recipientChannel!=null){
                Channel findChannel = users.find(recipientChannel.id());
                if (findChannel!=null){
                    dataContent.setAction(MsgActionEnum.REPEAT_LOGIN.type);
                    findChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dataContent)));
                    findChannel.close();
                }
            }


            //将username和channel关联起来
            UserChannelRel.put(senderUsername,currentChannel);
        }
        /**
         * 2、action为聊天，需要将发送者的消息存储到数据中，并且找到接收者的channel将消息推送出去
         */
        else if(actionType== MsgActionEnum.CHAT.type){
            String senderUsername = dataContent.getChatMsg().getSenderUsername();
            String recipientUsername = dataContent.getChatMsg().getRecipientUsername();
            String msgText = dataContent.getChatMsg().getMsg();

            //将数据写入数据库
            MsgXx msgXxResult = msgService.addMsg(senderUsername,recipientUsername,msgText,"2");

            //进行消息的推送,通过接受者的Username找到对应的的Channal
            Channel recipientChannel = UserChannelRel.get(recipientUsername);

            if (recipientChannel!=null){
                Channel findChannel = users.find(recipientChannel.id());
                if (findChannel!=null){
                    dataContent.getChatMsg().setId(msgXxResult.getId());
                    findChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dataContent)));//如果对方在线就直接推送消息
                }
            }
        }
        /**
         *3、action为签收信息
         */
        else if(actionType== MsgActionEnum.SIGNED.type){

            String msgIdsStr = dataContent.getExtand();
            String msgIds[] = msgIdsStr.split(",");
            List<String> msgIdList = new ArrayList<>();
            for (String mid : msgIds) {
                if (!StringUtil.isNullOrEmpty(mid)) {
                    msgIdList.add(mid);
                }
            }
            System.out.println(msgIdList.toString());

            if (msgIdList != null && !msgIdList.isEmpty() && msgIdList.size() > 0) {
                msgService.batchSign(msgIdList);//批量签收,更新数据库字段
            }
        }
        /**
         *4、action为保持心跳
         */
        else if(actionType== MsgActionEnum.KEEPALIVE.type){
            System.out.println("收到来自channel为[" + currentChannel + "]的心跳包...");
        }
        /**
         *5、action为拉取好友
         */
        else if(actionType== MsgActionEnum.PULL_FRIEND.type){

        }
        /**
         *6、重复登录校验
         */
        else if(actionType==MsgActionEnum.REPEAT_LOGIN.type){
            System.out.println("111");

            //获得其他设备登录的账号信息，如果在线，将dataContent返回回去
            Channel recipientChannel= UserChannelRel.get(dataContent.getChatMsg().getSenderUsername());
                if (recipientChannel!=null){
                    Channel findChannel = users.find(recipientChannel.id());
                    if (findChannel!=null){
                        findChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dataContent)));//如果对方在线就直接推送消息
                    }
                }

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
