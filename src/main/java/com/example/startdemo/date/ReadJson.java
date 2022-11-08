package com.example.startdemo.date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.startdemo.date.entity.InstagramPhotoCfg;
import org.apache.commons.collections4.MapUtils;

import java.io.*;

/**
 * @author 徐炜航
 * @version 1.0
 * @date 2022-11-08 21:39
 * @mail xuweihang@joyy.sg
 */
public class ReadJson {
    public static void main(String[] args) throws Exception {
        readFile("/Users/xuweihang/Downloads/test.json");

    }


    private static void readFile(String filePath) throws Exception {
        int storeCount = 0;
        int markCount = 0;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String jsonStr = sb.toString();
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        for (Object object : jsonArray) {

            JSONObject jsonObject = JSON.parseObject(object.toString());
            String jsonObject1 = jsonObject.getString("config_value");

            InstagramPhotoCfg cfg = JSON.parseObject(jsonObject1, InstagramPhotoCfg.class);
            if (MapUtils.isNotEmpty(cfg.getMarks())) {
                storeCount++;
                markCount += cfg.getMarks().size();
            }
        }
        System.out.println(storeCount);
        System.out.println(markCount);
    }
}
