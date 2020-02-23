package com.example.startdemo.dubbo.client.proxy;

import com.example.startdemo.dubbo.api.Invocation;
import com.example.startdemo.dubbo.api.URL;
import com.example.startdemo.dubbo.register.RegisterType;
import com.example.startdemo.dubbo.register.RemoteRegister;
import com.example.startdemo.dubbo.register.factory.RemoteRegisterFactory;
import com.example.startdemo.dubbo.server.server.Protocl;
import com.example.startdemo.dubbo.server.server.ProtoclFactory;
import com.example.startdemo.dubbo.server.server.ProtoclType;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static <T> T getProxy(final ProtoclType protoclType, final RegisterType registerType, final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Protocl protocl = ProtoclFactory.getProtocl(protoclType);
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                RemoteRegister remoteRegister = RemoteRegisterFactory.getRemoteRegister(registerType);
                URL radomURL = remoteRegister.getRadomURL(interfaceClass.getName());
                System.out.println("调用地址host:" + radomURL.getHost() + ",port:" + radomURL.getPort());
                return protocl.invokeProtocl(radomURL, invocation);
            }
        });
    }
}
