package com.example.startdemo.date;

import com.btime.util.XlsReader;
import com.google.gson.Gson;

import java.io.*;
import java.util.*;

/**
 * @author xuweihang@qbb.com
 * @date 2019-06-27 19:35
 */
public class IOTest {
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        File apolloFile1 = new File("/Users/xuweihang/Downloads/read.csv");
        List<Integer> s = readFile(apolloFile1);
        System.out.println(gson.toJson(s));
    }


    /**
     * 读取文本
     *
     * @param file
     * @return
     */
    private static List<Integer> readFile(File file) {
        //key
        List<Integer> keyList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) { //一次读一行内容
                s = s.replaceAll("\"", "");
                s = s.replaceAll("\uFEFF", "");
                s = s.replaceAll(" ", "");

                keyList.add(Integer.valueOf(s));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyList;

    }

    /**
     * 读取excel
     *
     * @param filePath
     * @return
     */
    private static List<List<String>> readExcel(String filePath) {
        //key
        XlsReader reader = new XlsReader();
        List<List> res = reader.load(filePath, List.class);

        return null;
    }
}
