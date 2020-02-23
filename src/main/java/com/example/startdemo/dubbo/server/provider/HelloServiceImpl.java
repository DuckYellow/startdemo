package com.example.startdemo.dubbo.server.provider;


import com.example.startdemo.dubbo.api.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("提供者被调用 入参：" + name);
        return "success：" + name;
    }
}
