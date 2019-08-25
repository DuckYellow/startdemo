package com.example.startdemo.date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweihang@qbb.com
 * @date 2019-07-18 11:23
 */
public class dealR {
    public static void main(String[] args) {
        File ptRFile = new File("/Users/xuweihang/Downloads/ptKeyR.txt");
        File webserRFile = new File("/Users/xuweihang/Downloads/webserKeyR.txt");
        List<String> ptRList = readR(ptRFile);
        List<String> webserRList = readR(webserRFile);

        List<String> r = new ArrayList<>();
        for (String s : ptRList) {
            for (String s1 : webserRList) {
                if (s.equals(s1)) {
                    r.add(s);
                }
            }
        }
        System.out.println(r.size());
        r.forEach(s -> System.out.println(s));

        File ptMFile = new File("/Users/xuweihang/Downloads/ptKeyM.txt");
        File webserMFile = new File("/Users/xuweihang/Downloads/webserKeyM.txt");
        List<String> ptMList = readR(ptMFile);
        List<String> webserMList = readR(webserMFile);
        List<String> w = new ArrayList<>();
        for (String s : ptMList) {
            for (String s1 : webserMList) {
                if (s.equals(s1)) {
                    w.add(s);
                }
            }
        }
        System.out.println(w.size());
        w.forEach(s -> System.out.println(s));
    }

//    private static List<String> readM(File file) {
//        List<String> res = new ArrayList<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String s = "";
//            while ((s = br.readLine()) != null) { //一次读一行内容
//                s = s.replaceAll("\\s*|\t|\r|\n", "");
//                if (s.length() <= 1) {
//                    continue;
//                }
//                if ("//".equals(s.substring(0, 2))) {
//                    continue;
//                }
//                String[] keyList = s.split("=");
//                if (keyList.length < 2) {
//                    continue;
//                }
//                String keyList1 = keyList[1];
//                String key = keyList1.split(";")[0];
//                res.add(key);
//
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return res;
//    }

    private static List<String> readR(File file) {
        List<String> res = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while ((s = br.readLine()) != null) { //一次读一行内容
                s = s.replaceAll("\\s*|\t|\r|\n", "");
                if (s.length() <= 1) {
                    continue;
                }
                if ("//".equals(s.substring(0, 2))) {
                    continue;
                }
                String[] keyList = s.split("=");
                if (keyList.length < 2) {
                    continue;
                }
                String keyList1 = keyList[1];
                String key = keyList1.split(";")[0];
                res.add(key);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
