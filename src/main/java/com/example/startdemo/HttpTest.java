package com.example.startdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author xuweihang@qbb.com
 * @date 2019-10-29 11:38
 */
public class HttpTest {

    private static String IP = "http://192.168.5.3:10044";
    private static String FILE_PATH = "/internal/file/content/upload";
    private static String desktop = "/Users/xuweihang/Desktop/disc/2721.jpg";

    public static void main(String[] args) throws Exception {
        //sendPic(desktop);
        sendPic2(desktop);
    }

    private static void sendPic2(String dist) throws Exception {
        File fileEntry = new File(dist);
        System.out.println(fileEntry.getName());

        FileInputStream fileInput = new FileInputStream(fileEntry);

        MultipartFile file = new MockMultipartFile("file", fileEntry.getName(), "application/octet-stream", IOUtils.toByteArray(fileInput));
        String md5 = getFileMD5(fileEntry);
        String url = IP + FILE_PATH;

        HttpPost httppost = new HttpPost(url);
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

        entity.addPart("type", strBody("file"));
        entity.addPart("name", strBody(file.getName()));
        entity.addPart("file", new InputStreamBody(file.getInputStream(), file.getContentType(), file.getOriginalFilename()));
        entity.addPart("fileName", strBody(file.getOriginalFilename()));
        entity.addPart("farm", strBody("content"));
        entity.addPart("needCompress", new StringBody("true"));

        httppost.setEntity(entity);
        httppost.setHeader("Accept", "application/json");

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpClient httpclient = new DefaultHttpClient();
        String responseBody = httpclient.execute(httppost, responseHandler);
        System.out.println(responseBody);
    }

    private static StringBody strBody(String value) throws UnsupportedEncodingException {
        return new StringBody(value, Charset.forName("utf-8"));
    }

    private static void sendPic(String dist) throws Exception {
        File fileEntry = new File(dist);
        FileInputStream fileInput = new FileInputStream(fileEntry);

        MultipartFile file = new MockMultipartFile("file", fileEntry.getName(), "application/octet-stream", IOUtils.toByteArray(fileInput));
        String md5 = getFileMD5(fileEntry);

        String url = "http://192.168.5.3:10044" + FILE_PATH;
        Map<String, Object> params = new HashMap<>();
        params.put("file", file);
        params.put("farm", "content");
        params.put("md5", md5);
        HttpPost httpPost = getHttpPost(params, url, "application/json", null, "UTF-8");
        CloseableHttpResponse closeableHttpResponse = executeHttpPost(httpPost);
        String result = ObjectToString(closeableHttpResponse, "UTF-8");
        System.out.println(result);
    }

    public static String ObjectToString(CloseableHttpResponse response, String charset) throws IOException {
        try {
            HttpEntity resEntity = response.getEntity();
            String responseBaby = null;
            if (resEntity != null) {
                responseBaby = EntityUtils.toString(resEntity, charset);
            }
            return responseBaby;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public static CloseableHttpResponse executeHttpPost(HttpPost httpPost) throws Exception {
        int socketTimeout = 1000;
        int connectionTimeout = 3000;
        int connectionRequestTimeout = 3000;
        int maxTotal = 100;
        int defaultMaxPerRoute = 100;

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectionTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Registry<ConnectionSocketFactory> socketFactoryRegistry
                = RegistryBuilder.<ConnectionSocketFactory>create().register("http",
                PlainConnectionSocketFactory.INSTANCE).register("https",
                sslsf).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm).setDefaultRequestConfig(requestConfig)
                .build();
        return httpclient.execute(httpPost);
    }


    public static HttpPost getHttpPost(Map<String, Object> params, String url, String accept, String contentType, String charset) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (accept != null) {
            httpPost.setHeader("Accept", accept);
        }

        if (contentType != null) {
            httpPost.setHeader("Content-type", contentType);
        }

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String parameterName = entry.getKey();
            Object parameterValue = entry.getValue();
            nvps.add(new BasicNameValuePair(parameterName, String
                    .valueOf(parameterValue)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
        return httpPost;
    }

    public static String getFileMD5(File file) {
        if (file.exists() && file.isFile()) {
            MessageDigest digest = null;
            FileInputStream in = null;
            byte[] buffer = new byte[1024];

            try {
                digest = MessageDigest.getInstance("MD5");
                in = new FileInputStream(file);

                while (true) {
                    int len;
                    if ((len = in.read(buffer, 0, 1024)) == -1) {
                        in.close();
                        break;
                    }

                    digest.update(buffer, 0, len);
                }
            } catch (Exception var11) {
                var11.printStackTrace();
                return null;
            }

            byte[] bufDig = digest.digest();
            StringBuffer buf = new StringBuffer();
            byte[] var7 = bufDig;
            int var8 = bufDig.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                byte temp = var7[var9];
                buf.append(toFullHex(temp));
            }

            return buf.toString();
        } else {
            return null;
        }
    }

    public static String toFullHex(byte data) {
        int val = data & 255;
        return val < 16 ? "0" + Integer.toHexString(val) : Integer.toHexString(val);
    }


}
