package com.example.startdemo.date;

import com.btime.util.XlsReader;

import java.io.*;
import java.util.*;

/**
 * @author xuweihang@qbb.com
 * @date 2019-06-27 19:35
 */
public class IOTest {
    public static void main(String[] args) {
        File apolloFile1 = new File("/Users/xuweihang/Downloads/long_text_2019-08-01-16-52-42.txt");
    }


    /**
     * 读取文本
     *
     * @param file
     * @return
     */
    private static List<String> readFile(File file) {
        //key
        List<String> keyList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) { //一次读一行内容
                System.out.println(s);
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
