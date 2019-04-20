package com.example.startdemo.proxyInstance;

public class Main {
    public static void main(String[] args) {
        Bussiness bussiness = new BussinessImpl();
        Bussiness bussinessProxy = (Bussiness) new CglibProxyFactory(bussiness).getProxyInstance();

        bussiness.execute();
        bussinessProxy.execute();
    }
}
