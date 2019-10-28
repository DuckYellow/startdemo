package com.example.startdemo;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xuweihang@qbb.com
 * @date 2019-05-18 21:23
 */
public class SpringTest {


    public static void main(String[] args) {
        String utcTime = "2019-10-12T00:00:00Z";
        Date date = parseUTCDateString(utcTime);
        long a = calculateHours(new Date(), date);
        System.out.println("a");
    }


    public static Date parseUTCDateString(String expire) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date expireDate = null;
        try {
            expireDate = df.parse(expire);
            return expireDate;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Date();
        }

    }

    public static long calculateHours(Date preTime, Date curTime) {
        long hoursDiff = (curTime.getTime() - preTime.getTime()) / (1000 * 60 * 60);
        return (long) hoursDiff;
    }

    private static void test() {
        String utcTime = "2018-02-04T00:00:00Z";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        sf.setTimeZone(utcZone);
        Date date = null;
        String dateTime = "";
        try {
            date = sf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static int isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int result = 0;
        while (i < chars.length) {
            char valueC = chars[i];
            String value = String.valueOf(valueC);
            if ("I".equals(value) || "X".equals(value) || "C".equals(value)) {
                if ("I".equals(value)) {
                    if ((i + 1) < chars.length && "V".equals(String.valueOf(chars[i + 1]))) {
                        result = result + 4;
                        i = i + 2;
                    } else if ((i + 1) < chars.length && "X".equals(String.valueOf(chars[i + 1]))) {
                        result = result + 9;
                        i = i + 2;
                    } else {
                        result = result + 1;
                        i++;
                    }
                }
                if ("X".equals(value)) {
                    if ((i + 1) < chars.length && "L".equals(String.valueOf(chars[i + 1]))) {
                        result = result + 40;
                        i = i + 2;
                    } else if ((i + 1) < chars.length && "C".equals(String.valueOf(chars[i + 1]))) {
                        result = result + 90;
                        i = i + 2;
                    } else {
                        result = result + 10;
                        i++;
                    }
                }
                if ("C".equals(value)) {
                    if ((i + 1) < chars.length && "D".equals(String.valueOf(chars[i + 1]))) {
                        result = result + 400;
                        i = i + 2;
                    } else if ((i + 1) < chars.length && "M".equals(String.valueOf(chars[i + 1]))) {
                        result = result + 900;
                        i = i + 2;
                    } else {
                        result = result + 100;
                        i++;
                    }
                }
            } else {
                if ("V".equals(value)) result = result + 5;
                else if ("L".equals(value)) result = result + 50;
                else if ("D".equals(value)) result = result + 500;
                else if ("M".equals(value)) result = result + 1000;
                i++;
            }
        }
        return result;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String ggzfc = strs[0];
        for (int a = 0; a < strs.length; a++) {
            while (strs[a].indexOf(ggzfc) != 0) {
                ggzfc = ggzfc.substring(0, ggzfc.length() - 1);
                if (ggzfc.length() == 0) {
                    return "";
                }
            }
        }
        return ggzfc;
    }

}
