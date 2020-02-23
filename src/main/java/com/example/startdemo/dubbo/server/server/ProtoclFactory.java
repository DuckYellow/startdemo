package com.example.startdemo.dubbo.server.server;


import com.example.startdemo.dubbo.server.server.dubbo.NettyProtocl;
import com.example.startdemo.dubbo.server.server.dubbo.http.HttpProtocl;

public class ProtoclFactory {
    private static NettyProtocl nettyProtocl = new NettyProtocl();

    private static HttpProtocl httpProtocl = new HttpProtocl();

    public static Protocl getProtocl(ProtoclType protoclType) {
        switch (protoclType) {
            case HTTP:
                return httpProtocl;
            case NETTY:
                return nettyProtocl;
            default:
                return null;
        }
    }
}
