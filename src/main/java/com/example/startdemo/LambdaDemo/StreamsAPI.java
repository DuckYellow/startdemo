package com.example.startdemo.LambdaDemo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsAPI {
    public static void main(String[] args) {
        collect();
    }

    public static void forEach() {
        // 使用Stream.forEach()迭代
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.forEach(str -> System.out.println(str));
    }

    public static void filter() {
        // 保留长度等于3的字符串
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.filter(str -> str.length() == 3).forEach(str -> System.out.println(str));
    }

    public static void distinct() {
        Stream<String> stream = Stream.of("I", "love", "you", "too", "too");
        stream.distinct().forEach(str -> System.out.println(str));
    }

    public static void sorted() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.sorted((str1, str2) -> str1.length() - str2.length())
                .forEach(str -> System.out.println(str));
    }

    public static void map() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.map(str -> str.toUpperCase())
                .forEach(str -> System.out.println(str));
    }

    public static void flatMap() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 6), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(i -> System.out.println(i));
    }

    public static void reduce() {
        // 找出最长的单词
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        System.out.println(longest.get());

        // 求单词长度之和
        Stream<String> stream2 = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream2.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        System.out.println(lengthSum);
    }

    public static void collect() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        List<String> list = stream.collect(Collectors.toList()); // (1)
        ArrayList<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));// (3)
        HashSet<String> hashSet = stream.collect(Collectors.toCollection(HashSet::new));// (4)
        for (String s : list) {
            System.out.println(s);
        }

        list.parallelStream();
        list.stream();

        // 使用Collectors.joining()拼接字符串
        Stream<String> stream2 = Stream.of("I", "love", "you");
        String joined = stream2.collect(Collectors.joining(",", "{", "}"));// "{I,love,you}"
        System.out.println(joined);
    }

}
