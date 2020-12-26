package edu.lingnan.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //通过socketChannel获取pipeline
        ChannelPipeline pipeline = socketChannel.pipeline();

        //HttpServerCode是由netty提供的处理类，可以理解为拦截器,用于解码
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());

        //自定义handler
        pipeline.addLast("customHandler",new CustomHandler());

    }
}
