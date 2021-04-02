package com.example.startdemo.shejimoshi.abstractFactocy;

public class QbbApp extends App {
    @Override
    public CreateOrderHandle getCreateOrderHandel() {
        return new CreateOrderHandleQbb();
    }

    @Override
    public ConfirmOrderHandle getConfirmOrderHandle() {
        return new ConfirmOrderHandleQbb();
    }
}
