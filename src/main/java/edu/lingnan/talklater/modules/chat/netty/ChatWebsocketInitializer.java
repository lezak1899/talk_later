package edu.lingnan.talklater.modules.chat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * Description:
 * date: 2020/12/5 11:28
 * author likunzhu
 * version
 * since JDK 1.8
 */
public class ChatWebsocketInitializer extends ChannelInitializer<SocketChannel> {


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


        /**
         * 心跳机制的handle，针对客户端，如果在1分钟时没有向服务端发送读写心跳(ALL)，则主动断开
         */
        // 如果是读空闲或者写空闲，不处理
//        pipeline.addLast(new IdleStateHandler(8, 10, 12));
//        // 自定义的空闲状态检测
//        pipeline.addLast(new HeartBeatHandler());



        /**
         * 自定义handle，用于处理即时通讯的业务
         */
        //对httpSocket的支持,并指定访问路径
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义的hander
        pipeline.addLast(new ChatHandler());


    }
}
