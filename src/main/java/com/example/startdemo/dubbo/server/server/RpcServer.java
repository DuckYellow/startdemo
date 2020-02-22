package com.example.startdemo.dubbo.server.server;

/**
 * 服务端server
 */
public interface RpcServer {
    /**
     * 开启服务 监听hostName：port
     * @param hostName
     * @param port
     */
    public void start(String hostName, int port);

}
