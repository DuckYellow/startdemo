package com.example.startdemo.dubbo.server.server.dubbo;

import com.example.startdemo.dubbo.api.Invocation;
import com.example.startdemo.dubbo.api.URL;
import com.example.startdemo.dubbo.server.server.Protocl;
import com.example.startdemo.dubbo.server.server.dubbo.client.NettyClient;

public class NettyProtocl implements Protocl {
    @Override
    public Object invokeProtocl(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHost(), url.getPort(), invocation);
    }

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHost(), url.getPort());
    }
}
