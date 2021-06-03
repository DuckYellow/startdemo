package com.example.startdemo.listener;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestListener {
    @Test
    public void test() {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ListenerConfig.class);

        annotationConfigApplicationContext.publishEvent(new ApplicationEvent("新建的事件-----") {});

        annotationConfigApplicationContext.close();
    }
}
