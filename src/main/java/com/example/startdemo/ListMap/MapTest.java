package com.example.startdemo.ListMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        Map a = new HashMap();
        a.put(1,1);

        Map b = new ConcurrentHashMap();
        b.put(1,1);
        b.get(1);
        Set<String> seta=new HashSet();
        seta.add("a");

        getOrDefault();
    }

    public static void forEach() {
        // Java7以及之前迭代Map
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.putIfAbsent(1,"one");
        map.put(2, "two");
        map.put(3, "three");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        // 使用forEach()结合匿名内部类迭代Map
        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "one");
        map2.put(2, "two");
        map2.put(3, "three");
        map2.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    public static void getOrDefault() {
        // 查询Map中指定的值，不存在时使用默认值
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        // Java7以及之前做法
        if (map.containsKey(4)) { // 1
            System.out.println(map.get(4));
        } else {
            System.out.println("NoValue");
        }
    // Java8使用Map.getOrDefault()
        System.out.println(map.getOrDefault(4, "NoValue")); // 2
    }
}