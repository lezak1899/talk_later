package edu.lingnan.talklater.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2020/12/20 15:47
 * author likunzhu
 * version
 * since JDK 1.8
 */
@Component
public class WebSocketService {

    private EventLoopGroup boosGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;


    private static String nettyPort;
    @Value("${netty.port}")
    public  void setNettyPort(String nettyPort) {
        WebSocketService.nettyPort = nettyPort;
    }

    // 设置为单例
    private static class  SingletionWebSocketService{
        static final WebSocketService instance=new WebSocketService();
    }
    //获取实例方法
    public static WebSocketService getInstance(){
        return SingletionWebSocketService.instance;
    }

     //初始化方法
     public  WebSocketService(){
         //主线程组，用于接受客户端连接，但是不做任何处理
         boosGroup =  new NioEventLoopGroup();

         //从线程组，用于接受指派，处理任务
         workGroup =  new NioEventLoopGroup();

         serverBootstrap =new ServerBootstrap();

         serverBootstrap.group(boosGroup,workGroup)// 设置主从线程
                 .channel(NioServerSocketChannel.class)//设置nio的双向管道
                 .childHandler(new HelloWebsocketInitializer());//子处理器，由于处理
    }

    //启动并绑定端口，由于是交给了springboot管理，故不再需要手动关闭资源
    public void start() throws InterruptedException {
        channelFuture =serverBootstrap.bind(Integer.parseInt(nettyPort)).sync();
        System.err.println("netty websocket 启动,端口"+ nettyPort);
    }

}
