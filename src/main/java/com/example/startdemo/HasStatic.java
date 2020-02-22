package com.example.startdemo;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * HasStatic
 *
 * @author xuweihang@qbb.com
 * @date 2020-02-12 01:52
 */
public class HasStatic {
    private static final String KEY = "";
    private static int x = 100;

    static String TURING_DEVICE_ID = "ai00000000000015";
    static String TURING_SECRET = "473U7w52fY02Br24";
    static String TURING_APIKEY = "f5bc0c78b2694c58afe2c7e71a800c8e";

    String uid = "cfbf221fe07828d1cc8fe4748b127a30";

    public static void main(String args[]) throws Exception {
        System.out.println(encrypt(TURING_DEVICE_ID, "utf-8", TURING_SECRET, TURING_APIKEY, 32));
    }

    public static String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter, Integer length) throws Exception {
        if (ivParameter.length() > 16) {
            ivParameter = ivParameter.substring(0, 16);
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        return parseByte2HexStr(encrypted).substring(0, length);// 此处使用BASE64做转码。
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
