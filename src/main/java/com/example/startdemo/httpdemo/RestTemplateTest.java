package com.example.startdemo.httpdemo;


import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author blithe
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
public class RestTemplateTest {

    public static void main(String[] args) {
        test1();
    }

    public static String getRandomString(int length) {
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void test1() {
        //访问地址
        String url = "http://192.168.5.5:8013/api/xiaoban-content-process/Extract_Info?msg=想听国学三字经";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, String> params = Maps.newHashMap();
        params.put("msg", "想听国学三字经");
        HttpEntity<String> formEntity = new HttpEntity<>(new Gson().toJson(params), headers);
        ResponseEntity<String> response = client.getForEntity(url, String.class);
        System.out.println(response.getBody());
    }
}
