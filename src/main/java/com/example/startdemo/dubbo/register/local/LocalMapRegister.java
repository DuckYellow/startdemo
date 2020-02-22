package com.example.startdemo.dubbo.register.local;


import com.example.startdemo.dubbo.register.LocalRegister;

import java.util.HashMap;
import java.util.Map;

public class LocalMapRegister implements LocalRegister {
    private Map<String, Class> registerMap = new HashMap<String, Class>(1024);

    @Override
    public void register(String interfaceName, Class interfaceImplClass) {
        registerMap.put(interfaceName, interfaceImplClass);
    }

    @Override
    public Class get(String interfaceName) {
        return registerMap.get(interfaceName);
    }
}
