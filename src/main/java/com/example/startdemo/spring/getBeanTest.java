package com.example.startdemo.spring;

import com.example.startdemo.spi.SpiImpl1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class getBeanTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        ac.getBean(SpiImpl1.class);
        ac.hashCode();
        String a="a";
        a.length();
    }
}
