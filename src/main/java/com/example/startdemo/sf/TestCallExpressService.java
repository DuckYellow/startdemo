package com.example.startdemo.sf;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweihang@qbb.com
 * @date 2020-06-28 21:50
 */
public class TestCallExpressService {
    @SuppressWarnings("static-access")
    public static void test1() {
        String reqXml = "<Request service='RouteService' lang='zh-CN'>\n" +
                                "<Head>QBB</Head>\n" +
                                "<Body>\n" +
                                "<RouteRequest\n" +
                                "tracking_type='1'\n" +
                                "method_type='1'\n" +
                                "tracking_number='SF1011789898852'/>\n" +
                                "</Body>\n" +
                                "</Request>";
        //丰桥平台公共测试账号
        //SLKJ2019
        //FBIqMkZjzxbsZgo7jTpeq7PD8CVzLT4Q
        String reqURL = "http://218.17.248.244:11080/bsp-oisp/sfexpressService";
        String clientCode = "QBB";//此处替换为您在丰桥平台获取的顾客编码
        String checkword = "u2EfetB5IR6uGtjG";//此处替换为您在丰桥平台获取的校验码
        System.out.println("请求报文：" + reqXml);
        String respXml = callSfExpressServiceByCSIM(reqURL, reqXml, clientCode, checkword);


        if (respXml != null) {
            System.out.println("--------------------------------------");
            System.out.println("返回报文: " + respXml);
            System.out.println("--------------------------------------");
        }
    }

    public static void main(String[] args) throws Exception {
        test1();
        String logistics_interface = "<Request service=\"RoutePushService\" lang=\"zh-CN\"> <Body>\n" +
                                             "<WaybillRoute id=\"10049361064087\" mailno=\"444003079772\" orderid=\"TE201500106\" acceptTime=\"2015-01-04 17:42:00\"\n" +
                                             "acceptAddress=\"深圳\"\n" +
                                             "remark=\"上门收件\"\n" +
                                             "opCode=\"50\"/> </Body>\n" +
                                             "</Request>";
        Request request = (Request) XmlUtils.xmlToBean(logistics_interface, Request.class);
        System.out.println("a");
    }


    /*********************标准返回报文参考*****************************/
    //1.下订单-请求返回结果
    // <?xml version='1.0' encoding='UTF-8'?><Response service="OrderService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="020" mailno="444000010085" origincode="755" orderid="QIAO-20171017001"/></Body></Response>


    //2.订单结果查询 -请求返回结果
    //<?xml version='1.0' encoding='UTF-8'?><Response service="OrderSearchService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="020" mailno="444000010085,819000008006,819000008015" origincode="755" orderid="QIAO-20171017001"/></Body></Response>


    //3.订单取消-请求返回结果
    // <?xml version='1.0' encoding='UTF-8'?><Response service="OrderConfirmService"><Head>OK</Head><Body><OrderConfirmResponse res_status="2" orderid="QIAO-20171017001"/></Body></Response>

    //4.订单筛选-请求返回结果
    // <?xml version='1.0' encoding='UTF-8'?><Response service="OrderFilterService"><Head>OK</Head><Body><OrderFilterResponse filter_result="1" orderid="QIAO-20171017001"/></Body></Response>

    //5.路由查询-请求返回结果
    // <?xml version='1.0' encoding='UTF-8'?><Response service="RouteService"><Head>OK</Head><Body><RouteResponse mailno="444000010085" orderid="QIAO-20171017001"><Route remark="已经收件" accept_time="2017-08-31 02:01:44" accept_address="广东省深圳市软件产业基地" opcode="50"/><Route remark="已经收件" accept_time="2017-08-31 02:01:44" accept_address="广东省深圳市软件产业基地" opcode="80"/></RouteResponse></Body></Response>

    //6.路由推送

    //7.子单号申请-请求返回结果
    //<?xml version='1.0' encoding='UTF-8'?><Response service="OrderZDService"><Head>OK</Head><Body><OrderZDResponse><OrderZDResponse main_mailno="444000010085" mailno_zd="819000008006,819000008015" orderid="QIAO-20171017001"/></OrderZDResponse></Body></Response>
    public static String callSfExpressServiceByCSIM(String reqURL, String reqXML, String clientCode, String checkword) {
        String result = null;
        String verifyCode = md5EncryptAndBase64(reqXML + checkword);
        result = querySFAPIservice(reqURL, reqXML, verifyCode);
        return result;
    }

    public static String querySFAPIservice(String url, String xml, String verifyCode) {
        String result = null;
        try {
            result = postSFAPI(url, xml, verifyCode);
            return result;
        } catch (Exception var6) {
            return null;
        }
    }

    public static String post(String url, StringEntity entity) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = postForm(url, entity);
        String body = "";
        body = invoke(httpClient, post);

        try {
            httpClient.close();
        } catch (IOException var7) {

        }

        return body;
    }

    public String post(String url, String name, String value) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> parameters = new ArrayList();
        parameters.add(new BasicNameValuePair("content", value));
        HttpPost post = this.postForm(url, new UrlEncodedFormEntity(parameters, Charset.forName("UTF-8")));
        String body = "";
        body = this.invoke(httpClient, post);

        try {
            httpClient.close();
        } catch (IOException var9) {

        }

        return body;
    }

    public static String postSFAPI(String url, String xml, String verifyCode) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> parameters = new ArrayList();
        parameters.add(new BasicNameValuePair("xml", xml));
        parameters.add(new BasicNameValuePair("verifyCode", verifyCode));
        HttpPost post = postForm(url, new UrlEncodedFormEntity(parameters, Charset.forName("UTF-8")));
        String body = "";
        body = invoke(httpClient, post);

        try {
            httpClient.close();
        } catch (IOException var9) {

        }

        return body;
    }

    public String get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        String body = "";
        body = this.invoke(httpClient, get);

        try {
            httpClient.close();
        } catch (IOException var6) {
        }

        return body;
    }

    public String delete(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(url);
        String body = "";
        body = this.invoke(httpClient, delete);

        try {
            httpClient.close();
        } catch (IOException var6) {
        }

        return body;
    }

    public static String invoke(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = "";
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            body = parseResponse(response);
        }

        return body;
    }

    private static String parseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = "";

        try {
            if (entity != null) {
                body = EntityUtils.toString(entity);
            }
        } catch (ParseException var4) {
        } catch (IOException var5) {
        }

        return body;
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException var4) {
        } catch (IOException var5) {
        }

        return response;
    }

    public static HttpPost postForm(String url, StringEntity entity) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        return httpPost;
    }

    public static String loadFile(String fileName) {
        try {
            InputStream fis = new FileInputStream(fileName);
            byte[] bs = new byte[fis.available()];
            fis.read(bs);
            String res = new String(bs);
            fis.close();
            return res;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String md5EncryptAndBase64(String str) {
        return encodeBase64(md5Encrypt(str));
    }

    private static byte[] md5Encrypt(String encryptStr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(encryptStr.getBytes("utf8"));
            return md5.digest();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    private static String encodeBase64(byte[] b) {
        String str = (new Base64()).encodeAsString(b);
        return str;
    }
}
