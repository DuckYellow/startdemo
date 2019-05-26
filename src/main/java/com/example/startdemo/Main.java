package com.example.startdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuweihang@qbb.com
 * @date 2019-05-18 21:49
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SpringTest bean = context.getBean("SpringTest", SpringTest.class);
    }
}
