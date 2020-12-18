package com.example.startdemo.date;

import com.btime.util.XlsReader;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadDemo {
    private static Gson gson = new Gson();

    private static void readCSV() {
        File apolloFile1 = new File("/Users/xuweihang/Downloads/read.csv");
        List<Integer> s = readFile(apolloFile1);
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


}
