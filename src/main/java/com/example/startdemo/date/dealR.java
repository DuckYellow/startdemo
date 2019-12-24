package com.example.startdemo.date;

import com.btime.util.StringUtil;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xuweihang@qbb.com
 * @date 2019-07-18 11:23
 */
public class dealR {

    static String filePath = "/Users/xuweihang/Downloads/蜻蜓内容导入模版.xlsx";

    private static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(filePath);
        System.out.println(gson.toJson(list));
        list.remove(0);
        for (List<String> strings : list) {
            if (!StringUtils.isEmpty(strings.get(3))) {
                System.out.println(Float.valueOf(strings.get(3)));
            }

            if (!StringUtils.isEmpty(strings.get(4))) {
                System.out.println(Float.valueOf(strings.get(4)));
            }

            if (!StringUtils.isEmpty(strings.get(5))) {
                System.out.println(Float.valueOf(strings.get(5)));
            }

            if (!StringUtils.isEmpty(strings.get(6))) {
                System.out.println(Float.valueOf(strings.get(6)));
            }
        }

        System.out.println("---");
    }

}