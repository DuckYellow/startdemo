package com.example.startdemo.dubbo.server.server.dubbo.client;

import io.netty.channel.ChannelHandlerContext;

public interface NettyClientListener {
    void channelRead(ChannelHandlerContext ctx, Object message);
}
