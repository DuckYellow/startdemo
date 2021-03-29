package com.example.startdemo.function;

import java.util.function.BiFunction;

public class MyBiFunction implements BiFunction<String, String, String> {
    @Override
    public String apply(String s, String s2) {
        return s + ";" + s2;
    }
}
