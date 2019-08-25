package com.example.startdemo.date;

import java.io.*;
import java.util.*;

/**
 * @author xuweihang@qbb.com
 * @date 2019-06-27 19:35
 */
public class IOTest {
    public static void main(String[] args) {
        File apolloFile1 = new File("/Users/xuweihang/Downloads/long_text_2019-08-01-16-52-42.txt");
        List<String> keyList = readFile(apolloFile1);
        System.out.println(keyList.size());

        File apolloFile2 = new File("/Users/xuweihang/Downloads/long_text_2019-08-01-16-52-56.txt");
        List<String> keyList2 = readFile(apolloFile2);
        Set<String> a=new HashSet<>(keyList2);
        System.out.println(keyList2.size());

        for (String s : keyList) {
            if (!a.contains(s)){
                System.out.println(s);
            }
        }


//        File apolloFile2 = new File("/Users/xuweihang/Downloads/long_text_2019-07-15-18-17-48.txt");
//        List<String> keyList2 = readFile(apolloFile2);
//        System.out.println(keyList2.size());
//        for (String s : keyList2) {
//            if (!set.contains(s)) {
//                System.out.println(s);
//            }
//        }
    }

    public static void matchProject(String[] args) {
        File apolloFile = new File("/Users/xuweihang/Downloads/webserPt2");
        List<String> keyList = readFile(apolloFile);
        System.out.println("keyList=" + keyList.size());
        String url = "/Users/xuweihang/IdeaProjects/webser-pt";
        // String url = "/Users/xuweihang//IdeaProjects/btime.webser.edu/btime.webser.edu/src/main/java/com/btime/webser/edu/member";
        File file = new File(url);
        getFile(file, keyList);
        System.out.println("res=" + keyList.size());

        for (String s : keyList) {
            System.out.println(s);
        }
    }

    private static void getFile(File file, List<String> keyList) {
        Set<String> res = new HashSet<>();
        File[] tempList = file.listFiles();
        for (File file1 : tempList) {
            if (file1.isFile() && file1.getName().endsWith(".java")) {
                getKey(file1, keyList);
            }
            if (file1.isDirectory()) {
                getFile(file1, keyList);
            }
        }
    }

    private static Set<String> getKey(File file, List<String> keyList) {
        if (file == null || !file.isFile()) {
            return new HashSet<>();
        }
        Set<String> res = new HashSet<>();
        List<String> list = readJava(file);
        for (String s : list) {
            for (int i = 0; i < keyList.size(); i++) {
                if (s.contains(keyList.get(i))) {
                    keyList.remove(i);
                    i--;
                }
            }
        }
        return res;
    }

    private static List<String> readJava(File file) {
        List<String> res = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while ((s = br.readLine()) != null) { //一次读一行内容
                s = s.replace(" ", "");
                if (s.length() <= 1) {
                    continue;
                }
                if ("//".equals(s.substring(0, 2))) {
                    continue;
                } else {
                    res.add(s);
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private static List<String> readFile(File file) {
        //key
        List<String> keyList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) { //一次读一行内容
                if (s.length() <= 0) {
                    continue;
                }
                s = s.replace(" ", "");
                if ("#".equals(s.substring(0, 1))) {
                    continue;
                }
                if (s.contains("=")) {
                    keyList.add(s.split("=")[0]);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyList;

    }
}
