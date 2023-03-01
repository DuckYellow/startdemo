package com.example.startdemo.date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.startdemo.date.entity.InstagramPhotoCfg;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 徐炜航
 * @version 1.0
 * @date 2022-11-08 21:39
 * @mail xuweihang@joyy.sg
 */
public class ReadJson {
    public static void main(String[] args) throws Exception {
        readFile("/Users/xuweihang/Downloads/ins.json");
    }


    private static void readFile(String filePath) throws Exception {
        int storeCount = 0;

        int markCount = 0;
        List<Long> store1Ids = new ArrayList<>();
        int topIds = 0;
        List<Long> store2Ids = new ArrayList<>();
        int hashTag = 0;
        List<Long> store3Ids = new ArrayList<>();
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
            storeCount++;
            JSONObject jsonObject = JSON.parseObject(object.toString());
            String jsonObject1 = jsonObject.getString("config_value");
            Long storeId = jsonObject.getLong("store_id");

            InstagramPhotoCfg cfg = JSON.parseObject(jsonObject1, InstagramPhotoCfg.class);
            if (MapUtils.isNotEmpty(cfg.getMarks())) {
                markCount += cfg.getMarks().size();
                store1Ids.add(storeId);
            }
            if (!StringUtils.isEmpty(cfg.getConfig().getShowHashTags())
                    || !StringUtils.isEmpty(cfg.getConfig().getNonShowHashTags())) {
                hashTag++;
                store2Ids.add(storeId);
            }
            if (CollectionUtils.isNotEmpty(cfg.getConfig().getTopMediaIds())) {
                topIds++;
                store3Ids.add(storeId);
            }
        }
        Collections.sort(store1Ids);
        Collections.sort(store2Ids);
        Collections.sort(store3Ids);


        System.out.println("店铺总数：" + storeCount);
        System.out.println("mark总数：" + markCount);
        System.out.println("mark 店铺总数：" + store1Ids.size());
        System.out.println("mark店铺id：" + store1Ids.toString());
        System.out.println("hash有数据 店铺总数：" + hashTag);
        System.out.println("hash有数据店铺id：" + store2Ids.toString());
        System.out.println("使用topId 店铺总数：" + topIds);
        System.out.println("使用topId店铺id：" + store3Ids.toString());
    }
}
