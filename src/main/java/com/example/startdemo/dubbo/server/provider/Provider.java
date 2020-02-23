package com.example.startdemo.dubbo.server.provider;

import com.example.startdemo.dubbo.api.HelloService;
import com.example.startdemo.dubbo.api.URL;
import com.example.startdemo.dubbo.register.LocalRegister;
import com.example.startdemo.dubbo.register.RegisterType;
import com.example.startdemo.dubbo.register.RemoteRegister;
import com.example.startdemo.dubbo.register.factory.LocalRegisterFactory;
import com.example.startdemo.dubbo.register.factory.RemoteRegisterFactory;
import com.example.startdemo.dubbo.server.server.Protocl;
import com.example.startdemo.dubbo.server.server.ProtoclFactory;
import com.example.startdemo.dubbo.server.server.ProtoclType;


public class Provider {
    public static void main(String[] args) {
            URL url = new URL("localhost",8021);
            //远程服务注册地址
            RemoteRegister register = RemoteRegisterFactory.getRemoteRegister(RegisterType.LOCAL);
            register.register(HelloService.class.getName(),url);

            //本地注册服务的实现类
            LocalRegister localRegister = LocalRegisterFactory.getLocalRegister(RegisterType.LOCAL);
            localRegister.register(HelloService.class.getName(),HelloServiceImpl.class);

            Protocl protocl = ProtoclFactory.getProtocl(ProtoclType.NETTY);
            protocl.start(url);
    }
}
