package com.example.startdemo.function;

import java.util.function.Function;

public class MyFunction implements Function<String, Integer> {

    @Override
    public Integer apply(String s) {
        return s.length();
    }
}
