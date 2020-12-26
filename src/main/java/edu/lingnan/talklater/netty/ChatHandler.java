package edu.lingnan.talklater.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;


/**
 * Description:
 * date: 2020/12/8 21:08
 * author likunzhu
 * version
 * since JDK 1.8
 */
//TextWebSocketFrame：专门为websocket处理文本的对象，frame是消息载体
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端
     private static ChannelGroup channels=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String content=textWebSocketFrame.text();
        System.out.println("接受的数据为："+ content);

        channels.writeAndFlush(new TextWebSocketFrame("服务器"+ LocalDateTime.now()+"接受到："+content));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开，channel对应的短id："+ ctx.channel().id().asShortText());
        System.out.println("客户端断开，channel对应的长id："+ ctx.channel().id().asLongText());
    }
}
