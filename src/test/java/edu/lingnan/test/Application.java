package edu.lingnan.test;

import edu.lingnan.netty.HelloServerInitializer;
import edu.lingnan.websocket.HelloWebsocketInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;

public class Application {




    @Test
    public void HelloNetty() {

        //主线程组，用于接受客户端连接，但是不做任何处理
        EventLoopGroup boosGroup =  new NioEventLoopGroup();

        //从线程组，用于接受指派，处理任务
        EventLoopGroup workGroup =  new NioEventLoopGroup();

        ServerBootstrap serverBootstrap =new ServerBootstrap();

        serverBootstrap.group(boosGroup,workGroup)// 设置主从线程
                       .channel(NioServerSocketChannel.class)//设置nio的双向管道
                       .childHandler(new HelloServerInitializer());//子处理器，由于处理

        ChannelFuture channelFuture  =null;
        try {
            //启动server，并且设置端口号为8088为启动端口号，同时启动方式为同步
            channelFuture =serverBootstrap.bind(8089).sync();
            //监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }

    }

    @Test
    public void HelloWebsocket(){
        //主线程组，用于接受客户端连接，但是不做任何处理
        EventLoopGroup boosGroup =  new NioEventLoopGroup();

        //从线程组，用于接受指派，处理任务
        EventLoopGroup workGroup =  new NioEventLoopGroup();

        ServerBootstrap serverBootstrap =new ServerBootstrap();

        serverBootstrap.group(boosGroup,workGroup)// 设置主从线程
                .channel(NioServerSocketChannel.class)//设置nio的双向管道
                .childHandler(new HelloWebsocketInitializer());//子处理器，由于处理

        ChannelFuture channelFuture  =null;
        try {
            //启动server，并且设置端口号为8088为启动端口号，同时启动方式为同步
            channelFuture =serverBootstrap.bind(8088).sync();
            //监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }

    }

}
