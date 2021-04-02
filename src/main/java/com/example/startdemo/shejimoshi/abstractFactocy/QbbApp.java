package com.example.startdemo.shejimoshi.abstractFactocy;

public class QbbApp extends App {
    @Override
    public CreateOrderHandel getCreateOrderHandel() {
        return new CreateOrderHandelQbb();
    }

    @Override
    public ConfirmOrderHandle getConfirmOrderHandle() {
        return new ConfirmOrderHandleQbb();
    }
}
