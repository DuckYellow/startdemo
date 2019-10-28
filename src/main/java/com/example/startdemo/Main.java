package com.example.startdemo;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuweihang@qbb.com
 * @date 2019-05-18 21:49
 */
public class Main {
    private static String desktop = "/Users/xuweihang/Desktop/";
    private static Map<Integer, String> urlMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String text = desktop + "text";
        String src = desktop + "src";
        String disc = desktop + "disc";
        //        getText(text);
        //        savePic(src);
        dealPic(src, disc);

    }

    public static void savePic(String path) throws Exception {
        for (Map.Entry<Integer, String> integerStringEntry : urlMap.entrySet()) {
            Integer themeId = integerStringEntry.getKey();
            String urlString = integerStringEntry.getValue();
            String savePath = path + "/" + themeId + ".jpg";
            URL url = new URL(urlString);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5000);
            InputStream is = con.getInputStream();
            byte[] bs = new byte[1024];
            FileOutputStream os = new FileOutputStream(savePath);

            int len;
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
        }

    }

    public static void getText(String text) throws Exception {
        for (int i = 1; i < 16; i++) {
            String path = text + "/" + i + ".txt";
            File ptRFile = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(ptRFile));
            String s = "";
            s = br.readLine();
            while ((s = br.readLine()) != null) { //一次读一行内容
                if (StringUtils.isEmpty(s)) {
                    continue;
                }
                s = s.replaceAll("\\s*|\t|\r|\n", "");
                s = s.replaceAll("\"", "");
                if (StringUtils.isEmpty(s)) {
                    continue;
                }

                String[] keyList = s.split(",");

                String key = keyList[0];
                String value = keyList[1];
                urlMap.put(Integer.valueOf(key), value);
            }
            br.close();
        }
    }

    private static void dealPic(String src, String dist) throws IOException {
        File folder = new File(src);
        List<String> sonSrcPath = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            String fileName = fileEntry.getName();
            try {
                Thumbnails.of(src + "/" + fileName).size(350, 350).toFile(dist + "/" + fileName);
            } catch (Exception e) {
                System.out.println("------------" + fileName);
            }
        }

    }

}
