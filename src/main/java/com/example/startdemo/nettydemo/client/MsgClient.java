package com.example.startdemo.nettydemo.client;

import com.example.startdemo.nettydemo.services.BytesToStringDecoder;
import com.example.startdemo.nettydemo.services.StringToBytesEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.annotation.PreDestroy;

public class MsgClient {
    public static void main(String[] args) {
        startConnect();
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("OK");
    }

    //用于连接和处理网络请求I/O的线程池
    private static NioEventLoopGroup eventLoopGroup;


    public static void startConnect() {
        //关闭检查
        if (eventLoopGroup != null) {
            try {
                eventLoopGroup.shutdownGracefully().sync();
                eventLoopGroup = null;
            } catch (InterruptedException e) {
            }

        }
        //新建
        eventLoopGroup = new NioEventLoopGroup();
        //出站 最后一步,数据分包
        final LengthFieldPrepender prepender = new LengthFieldPrepender(4, true);
        //出站,第一步,将自定义对象:String转化为bytes
        final StringToBytesEncoder encoder = new StringToBytesEncoder();
        //入站,第二步,bytes 转化为自定义的对象:String
        final BytesToStringDecoder decoder = new BytesToStringDecoder();
        //入战,第三步,消息处理,亦可以转发.
        final MsgClientHandler msgClientHandler = new MsgClientHandler("客户端");

        //服务配置
        Bootstrap clientBoot = new Bootstrap();
        clientBoot.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15 * 1000) // 超时时间
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //请求日志
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO)) //日志打印
                                .addLast("4", prepender)//out 4
                                .addLast("5", encoder)//out 5
                                .addLast("1", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4, 4))//in 1
                                .addLast("2", decoder)//in 2
                                .addLast("3", msgClientHandler)//in 3
                        ;
                    }
                });

        try {
            //链接
            ChannelFuture channelFuture = clientBoot.connect("127.0.0.1", 1765).sync();
            Channel channel = channelFuture.channel();

            channel.writeAndFlush("啊还是大所大撒所大");

            // 服务器返回结果在 MsgHandler 里面处理
        } catch (Throwable ex) {
        }
    }

    @PreDestroy
    public void close() {
        eventLoopGroup.shutdownGracefully();
    }
}
