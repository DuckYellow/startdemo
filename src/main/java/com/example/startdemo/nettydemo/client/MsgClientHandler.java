package com.example.startdemo.nettydemo.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

@ChannelHandler.Sharable
public class MsgClientHandler extends ChannelInboundHandlerAdapter {

    String name ;

    public MsgClientHandler(String name) {
        this.name = name;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String str = (String) msg;
        System.out.println("客户端收到:" + str);
        /**
         *  do any thing ....
         */
        //释放msg
        ReferenceCountUtil.release(msg);
    }

}