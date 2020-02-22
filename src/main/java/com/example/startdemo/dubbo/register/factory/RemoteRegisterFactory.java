package com.example.startdemo.dubbo.register.factory;


import com.example.startdemo.dubbo.register.RegisterType;
import com.example.startdemo.dubbo.register.RemoteRegister;
import com.example.startdemo.dubbo.register.local.RemoterMapRegister;
import com.example.startdemo.dubbo.register.zookeeper.ZookeepRemoteRegister;
import com.example.startdemo.dubbo.register.zookeeper.ZookeeperClient;

public class RemoteRegisterFactory {
    private static RemoterMapRegister remoterMapRegister = new RemoterMapRegister();
    private static ZookeepRemoteRegister zookeepRemoteRegister = new ZookeepRemoteRegister(new ZookeeperClient());

    public static RemoteRegister getRemoteRegister(RegisterType registerType) {
        switch (registerType) {
            case LOCAL:
                return remoterMapRegister;
            case ZOOKEEPER:
                return zookeepRemoteRegister;
            default:
                return null;
        }
    }
}
