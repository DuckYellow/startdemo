package com.example.startdemo.httpdemo.bean;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Person {
    private int id;
    private String name1;
    private String name2;
    private String name3;

    public static void main(String[] args) {
        List<String> aList = new ArrayList<>();
        aList.add("1");
        aList.add("2");
        aList.add("3");

        List<String> bList = new ArrayList<>();
        bList.add("a");
        bList.add("b");

        for (String s : aList) {
            System.out.print(s);
            for (String s1 : bList) {
                System.out.print(s1);
                if (s1.equals("a")) {
                    break;
                }
            }
            System.out.println("");
        }

    }
}
