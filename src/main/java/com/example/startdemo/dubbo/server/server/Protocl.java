package com.example.startdemo.dubbo.server.server;


import com.example.startdemo.dubbo.api.Invocation;
import com.example.startdemo.dubbo.api.URL;

public interface Protocl {
    /**
     * 远程调用
     *
     * @param url
     * @param invocation
     */
    Object invokeProtocl(URL url, Invocation invocation);

    /**
     * 服务开启
     *
     * @param url
     */
    void start(URL url);
}
