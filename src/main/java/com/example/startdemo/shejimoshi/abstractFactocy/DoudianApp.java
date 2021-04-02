package com.example.startdemo.shejimoshi.abstractFactocy;

public class DoudianApp extends App {


    @Override
    public CreateOrderHandel getCreateOrderHandel() {
        return new CreateOrderHandelDoudian();
    }

    @Override
    public ConfirmOrderHandle getConfirmOrderHandle() {
        return new ConfirmOrderHandleDoudain();
    }
}
