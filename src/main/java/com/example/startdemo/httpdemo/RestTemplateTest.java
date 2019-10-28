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
        //String url = "http://47.254.135.205/finance/querySupplierBill";
        String url = "http://47.254.135.205/finance/getSupplierBillDetail";
        //用户账号
        String username = "kikuu_coralglobal";
        //用户密码
        String password = "KiKUU_29831";
        //系统当前时间(yyyy-MM-dd HH:mm:ss)
        String currentTime = "2019-01-04 20:00:00";
        //MD5生成规则 （系统当前时间+用户密码）
        String abstractMD5 = Hashing.md5().newHasher().putString(currentTime + password, Charsets.UTF_8).hash().toString();
        // String abstractMD6 = Hashing.sha1().newHasher().putString(currentTime,password).hash().toString();
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("username", username);
        headers.set("currentTime", currentTime);
        headers.set("abstractMD5", abstractMD5);
        Map<String, String> params = Maps.newHashMap();
        params.put("BatchNo", "201812161231047580000");
        params.put("billConfirmBySupplierStartTime", "2019-01-01 11:44:30");
        params.put("billConfirmBySupplierEndTime", "2019-01-04 11:44:30");
        HttpEntity<String> formEntity = new HttpEntity<>(new Gson().toJson(params), headers);
        ResponseEntity<String> response = client.postForEntity(url, formEntity, String.class);
        System.out.println(response.getBody());
    }
}
