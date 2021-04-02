package com.example.startdemo.shejimoshi.abstractFactocy;

public class DoudianApp extends App {


    @Override
    public CreateOrderHandle getCreateOrderHandel() {
        return new CreateOrderHandleDoudian();
    }

    @Override
    public ConfirmOrderHandle getConfirmOrderHandle() {
        return new ConfirmOrderHandleDoudain();
    }
}
