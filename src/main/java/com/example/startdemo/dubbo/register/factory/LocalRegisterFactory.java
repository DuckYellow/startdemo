package com.example.startdemo.dubbo.register.factory;


import com.example.startdemo.dubbo.register.LocalRegister;
import com.example.startdemo.dubbo.register.RegisterType;
import com.example.startdemo.dubbo.register.local.LocalMapRegister;

public class LocalRegisterFactory {

    private static LocalMapRegister localMapRegister = new LocalMapRegister();

    public static LocalRegister getLocalRegister(RegisterType registerType) {
        switch (registerType) {
            case LOCAL:
                return localMapRegister;
            default:
                return null;
        }
    }
}
