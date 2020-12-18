package com.example.startdemo.date;

import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDeal {

    public static void main(String[] args) {
        File apolloFile1 = new File("/Users/xuweihang/Desktop/审批流元数据.csv");
        List<List<String>> yuanDate = readFile(apolloFile1);
    }

    private static List<List<String>> readFile(File file) {
        //key
        List<List<String>> res = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) { //一次读一行内容
                List<String> keyList = new ArrayList<>();
                s = s.replaceAll("\"", "");
                s = s.replaceAll("\uFEFF", "");
                String[] strings = s.split(",");
                res.add(CollectionUtils.arrayToList(strings));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }
}
