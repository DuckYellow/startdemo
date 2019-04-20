package com.example.startdemo.nettydemo.server;

import com.example.startdemo.nettydemo.services.BytesToStringDecoder;
import com.example.startdemo.nettydemo.services.StringToBytesEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.annotation.PreDestroy;

public class MsgServer {
    public static void main(String[] args) {
        nettySart();
    }

    //用于接收网络I/O请求线程池,异步IO
    private static EventLoopGroup bossEventLoop = new NioEventLoopGroup();
    //处理channel的线程池
    private static EventLoopGroup workerEventLoop = new NioEventLoopGroup();


    public static void nettySart() {

        //
        ServerBootstrap nettyBoot = new ServerBootstrap();
        //出站 最后一步,数据分包
        final LengthFieldPrepender prepender = new LengthFieldPrepender(4, true);
        //出站,第一步,将自定义对象:String转化为bytes
        final StringToBytesEncoder encoder = new StringToBytesEncoder();
        //入站第一步:网络包组包,不过这里加入last的时候要new出来,不然回报异常.
        LengthFieldBasedFrameDecoder frameDecoder = new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4, 4);
        //入站,第二步,bytes 转化为自定义的对象:String
        final BytesToStringDecoder decoder = new BytesToStringDecoder();
        //入战,第三步,消息处理,亦可以转发.
        final MsgServerHandler msgServerHandler = new MsgServerHandler("服务端");

        //配置netty服务
        nettyBoot.group(bossEventLoop, workerEventLoop)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        //入站,1,2,3出站5,4
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO)) //日志打印
                                .addLast("4", prepender)//4 分包,bytes -> http数据包
                                .addLast("5", encoder)//5 //msg -> bytes
                                .addLast("1", new LengthFieldBasedFrameDecoder(1000000, 0, 4, -4, 4))//1,http网络数据包,组包
                                .addLast("2", decoder)//2 //bytes -> msg ,自定义解码
                                .addLast("3", msgServerHandler);//3 //msg -> msg,数据转换,转发数据
                    }
                });
        try {
            //启动netty并监听指定端口,这里是main执行用同步阻塞.也可以用异步.
            ChannelFuture channelFuture = nettyBoot.bind(1765).await();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }

    }


    @PreDestroy
    public void close() {
        bossEventLoop.shutdownGracefully();
        workerEventLoop.shutdownGracefully();
    }
}
