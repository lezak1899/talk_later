package edu.lingnan.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * Description:
 * date: 2020/12/5 11:28
 * author likunzhu
 * version
 * since JDK 1.8
 */
public class HelloWebsocketInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline =  socketChannel.pipeline();

        //对http的支持
        //websocket  基于 http协议，添加http编码器
        pipeline.addLast(new HttpServerCodec());
        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage进行聚合，聚合成FullHttpResponse或者 FullHttpRequest
        pipeline.addLast(new HttpObjectAggregator(1024*64));

        //对httpSocket的支持
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义的hander
         pipeline.addLast(new ChatHandler ());


    }
}
