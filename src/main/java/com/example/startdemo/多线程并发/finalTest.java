package com.example.startdemo.多线程并发;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class finalTest {
    public static final Integer a = 1;

    public static final List<String> list;

    static {
        list = new ArrayList<>();
        list.add("1");
        list.add("2");
    }

    public static void test1() {
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }

        for (String s : list) {
            System.out.print(s);
        }
    }

    public static void main(String[] args) {
        String text = "Text";
        change(text);
        String textCopy = "jext";
        System.out.println(textCopy);
        System.out.println(text.equals(textCopy));
    }

    static void change(String txt) {
        try {
            Field valueField = txt.getClass().getDeclaredField("value");
            valueField.setAccessible(true);
            char[] value = (char[]) valueField.get(txt);
            value[0] = 'j';
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
