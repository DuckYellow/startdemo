package com.example.startdemo.dubbo.client.comsummer;


import com.example.startdemo.dubbo.api.HelloService;
import com.example.startdemo.dubbo.client.proxy.ProxyFactory;
import com.example.startdemo.dubbo.register.RegisterType;
import com.example.startdemo.dubbo.server.server.ProtoclType;

public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(ProtoclType.NETTY, RegisterType.LOCAL, HelloService.class);
        String result = helloService.sayHello("这个比");
        System.out.println("消费者输出：输出结果" + result);
    }
}
