package com.example.startdemo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class test {
    public static void main(String[] args) {

        //@Service读取
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.execute();
        }
    }
}
