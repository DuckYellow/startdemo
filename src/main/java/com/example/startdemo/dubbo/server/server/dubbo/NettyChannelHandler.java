package com.example.startdemo.dubbo.server.server.dubbo;

import com.example.startdemo.dubbo.api.Invocation;
import com.example.startdemo.dubbo.register.RegisterType;
import com.example.startdemo.dubbo.register.factory.LocalRegisterFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;


public class NettyChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        String interfaceName = invocation.getInterfaceName();
        Class interfaceImplClass = LocalRegisterFactory.getLocalRegister(RegisterType.LOCAL).get(interfaceName);
        Method method = interfaceImplClass.getMethod(invocation.getMethodName(),invocation.getParamtypes());
        String result = (String) method.invoke(interfaceImplClass.newInstance(),invocation.getObjects());
        ctx.write(result);
        ctx.flush();
    }
}
