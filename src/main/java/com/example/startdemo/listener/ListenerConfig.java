package com.example.startdemo.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {

    @Bean
    public MyListener myListener(){
        return new MyListener();
    }

}
