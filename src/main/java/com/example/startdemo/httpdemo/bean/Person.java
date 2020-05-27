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


        Person person = new Person();
        person.setId(1);

        Person person2 = new Person();
        person2.setId(2);

        List<Person> personList=new ArrayList<>();
        personList.add(person);
        personList.add(person2);

        personList.sort(Comparator.comparing(Person::getId));
        System.out.println("a");
    }
}
