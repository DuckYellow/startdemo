package com.example.startdemo.shejimoshi.abstractFactocy;

public class AppProducer {

    public static App getApp(String choice) {
        if (choice.equalsIgnoreCase("Qbb")) {
            return new QbbApp();
        } else if (choice.equalsIgnoreCase("Doudian")) {
            return new DoudianApp();
        }
        return null;
    }
}
