package edu.lingnan.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;



/**
 * Description:
 * date: 2020/12/3 21:06
 * author likunzhu
 * version
 * since JDK 1.8
 */
//SimpleChannelInboundHandler 相对于请求来说，相当于“入站，入境”
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        //获得channel
        Channel channel = channelHandlerContext.channel();

        //如果是httpRequest请求
        if(httpObject instanceof HttpRequest){
            //显示客户端的远程地址
            System.out.println("ip地址："+channel.remoteAddress());

            //定义传输的内容，以及编码
            ByteBuf byteBuffer = Unpooled.copiedBuffer("Hello netty!", CharsetUtil.UTF_8);

            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuffer);

            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");


            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuffer.readableBytes());
//            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,300);

            //将内容刷新到页面
            channelHandlerContext.writeAndFlush(fullHttpResponse);

        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel注册");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel注销");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel活跃");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel不活跃");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel读取完成");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("channel用户事件触发");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel可写更改");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("捕获到异常");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel。。。增加handler");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel。。。移除handler");
        super.handlerRemoved(ctx);
    }
}
