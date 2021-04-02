package com.example.startdemo.shejimoshi.abstractFactocy;

public abstract class App {

    /**
     * 创建订单
     */
    public abstract CreateOrderHandle getCreateOrderHandel();

    /**
     * 订单确认
     */
    public abstract ConfirmOrderHandle getConfirmOrderHandle();
}
