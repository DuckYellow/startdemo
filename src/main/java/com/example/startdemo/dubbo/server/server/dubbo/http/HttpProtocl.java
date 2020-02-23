package com.example.startdemo.dubbo.server.server.dubbo.http;

import com.example.startdemo.dubbo.api.Invocation;
import com.example.startdemo.dubbo.api.URL;
import com.example.startdemo.dubbo.server.server.Protocl;
import com.example.startdemo.dubbo.server.server.dubbo.http.client.HttpClient;

public class HttpProtocl implements Protocl {

    @Override
    public Object invokeProtocl(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.post(url.getHost(), url.getPort(), invocation);
    }

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHost(), url.getPort());
    }
}
