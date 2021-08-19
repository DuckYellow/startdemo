package com.example.startdemo.reflect;

import lombok.Data;

@Data
public class Car {
    static {
        System.out.println("static");
    }

    private String brand;

    private String color;

    private int maxSpeed;
}
