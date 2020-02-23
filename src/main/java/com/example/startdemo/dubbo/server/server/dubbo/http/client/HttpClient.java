package com.example.startdemo.dubbo.server.server.dubbo.http.client;


import com.example.startdemo.dubbo.api.Invocation;
import org.apache.http.Consts;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {
    public String post(String hostName, int port, Invocation invocation) {
        ObjectOutputStream objectOutputStream = null;
        try {
            URL url = new URL("http", hostName, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
            String body = null;
            StringBuffer result = new StringBuffer();
            while ((body = br.readLine()) != null) {
                result.append(body);
                System.out.println(body);
            }
            return result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
