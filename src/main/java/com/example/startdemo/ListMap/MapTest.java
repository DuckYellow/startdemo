package com.example.startdemo.ListMap;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapTest {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap();
        Integer key1 = 100;
        Integer key2 = 200;
        map.put(key1, 1);
        map.put(key2, 2);

        int n = map.size();
        int a = (n - 1) & key2.hashCode();
        System.out.println(a);
        System.out.println(map.get(key1));
    }

    static class Student {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    public static void forEach() {
        // Java7以及之前迭代Map
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.putIfAbsent(1, "one");
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

    public void allMap() {
        HashMap<String, Integer> hashMap = new HashMap();
        hashMap.put("b", 0);
        hashMap.put("a", 0);
        hashMap.put("c", 0);
        System.out.println(gson.toJson(hashMap));

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("b", 0);
        concurrentHashMap.put("a", 0);
        concurrentHashMap.put("c", 0);
        concurrentHashMap.get("a");
        System.out.println(gson.toJson(concurrentHashMap));

        ConcurrentSkipListMap<String, Integer> skipListMap = new ConcurrentSkipListMap();
        skipListMap.put("b", 0);
        skipListMap.put("a", 0);
        skipListMap.put("c", 0);
        System.out.println(gson.toJson(skipListMap));

        TreeMap<String, Integer> treeMap = new TreeMap();
        treeMap.put("key_1", 1);
        treeMap.put("key_2", 2);
        treeMap.put("key_3", 3);
        System.out.println(gson.toJson(treeMap));

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("b", 0);
        linkedHashMap.put("a", 0);
        linkedHashMap.put("c", 0);
        System.out.println(gson.toJson(linkedHashMap));
    }
}
