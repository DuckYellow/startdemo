package com.example.startdemo.date.chanel;

import com.alibaba.excel.util.StringUtils;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.KeyPair;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 徐炜航
 * @version 1.0
 * @date 2023-03-27 14:38
 * @mail xuweihang@joyy.sg
 */
public class RSA {
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();


    public static void main(String[] args) throws Exception {
       writePrivateKey();
    }

    public static void writePrivateKey() throws Exception {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJLoqybA/6z/Mbt5nj2cgnJl/7cb" +
                "s7HKgLonLXEx8iJxAjrYZ/k0/puG3uABQOpB6CTm8qkF28poP0pULLriRdBK147/7PVsERIz+k71" +
                "6gTgo1nKUdFWUGeTwST4jvjTeEG8lsolKzw+zuobsFRqzGJIwxy7Ba6iJ9TsVzaRF88zAgMBAAEC" +
                "gYA0YKwU5x7BSUMr+lKG2PRRYThoyoGo0/BNboZvI0WXLf+Dh4Cag2B7hvyTlR7V5qWj8ot149bX" +
                "NY/LKnPANch5zjluSJ4iQUlLF/x4Mb1lki75AZA/xW89iZDhZd+iE+RffYmJkhEKLJ7Z0bfOuSrx" +
                "35/A8o075fimdLyvkTIlsQJBANRmrRmC8uz/ltAzDNOlFxLvXQ825vu3X1fwcDGCB5Amwbsyz3fS" +
                "uSj5AlUnIkNns4IOt2HfNteyykCBKtjt630CQQCxEHw0gSlPJruOd9Mf3QdmzCpNZiWyD2Qf32YP" +
                "i/Iqo+sik4Q5ZtcI2jIB/N2P6wPPph/y8yqlkq8mmPkjn8RvAkA4fiK2MV0ZahWbxKg5tqQ/dgjD" +
                "Xi/rbWdfhWTh1hra3WQTvTzvOs3itvBG48S6Nt/IzM87qdn1XulqZLCXyBglAkBMkUhknAX1CFbK" +
                "wN43RSlSNq+EUMu0OBuf/Mbxqh7KeZ/Qj+H5wJL0bQZ6/ZICDirTvI7MTIMAnnHHSxdRZGFZAkEA" +
                "g4wxN3MoxfUalz2eP5fXGvnyn5dXDGs/sUZMSzR4xtxBypRvnaGbXzU1gDbsEajZHeMOdlrtnmgY" +
                "WMWyfMsGQA==";
        // 生成RSA密钥对
        // 保存公钥和私钥到文件中
        String publicKeyFile = "/Users/xuweihang/Desktop/id_rsa.pub";
        String privateKeyFile = "/Users/xuweihang/Desktop/id_rsa.key";

        JSch jsch = new JSch();
        // Generate a key pair with a passphrase
        KeyPair kpair = KeyPair.genKeyPair(jsch, KeyPair.RSA);
        kpair.writePrivateKey(privateKeyFile, Base64.getDecoder().decode(privateKey.getBytes()));
        kpair.writePublicKey(publicKeyFile, "sftp@example.com");

        //kpair.dispose();
    }


    public static String hmacSha256(String source, String secret) {
        if (StringUtils.isEmpty(secret) || StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(
                    secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(
                    source.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encodeHex(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 随机生成密钥对
     *
     * @throws Exception
     */
//    public static void genKeyPair() throws Exception {
//        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
//        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        // 初始化密钥对生成器
//        keyPairGen.initialize(1024, new SecureRandom());
//        // 生成一个密钥对，保存在keyPair中
//        KeyPair keyPair = keyPairGen.generateKeyPair();
//        // 得到私钥
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        // 得到公钥
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        String publicKeyString = encryptBASE64(publicKey.getEncoded());
//        // 得到私钥字符串
//        String privateKeyString = encryptBASE64(privateKey.getEncoded());
//        // 将公钥和私钥保存到Map
//        //0表示公钥
//        keyMap.put(0, publicKeyString);
//        //1表示私钥
//        keyMap.put(1, privateKeyString);
//
//        System.out.println("公钥:" + keyMap.get(0));
//        System.out.println("私钥:" + keyMap.get(1));
//    }

    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.getEncoder().encodeToString(key);
    }

    //解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.getDecoder().decode(key);
    }

    private static void test1() throws Exception {
        // Read public key from a file or a string
        //MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJgKGY8bBm1RYDqI0XPOKjJvhsRTPJmm+oH/LJjv1gWd+MKCvv1tdX9yrtOvP8CYTVbb58oj+QrOxZ28CvZ56WJINB4Qd/5zL8ThcNxVDlN5BjK5+5Ab5NYzl7UuKjUH9CnDyM+8e6dcu70Jn3V7pnGnNhtgM+P4O4jMv4ysYn3wIDAQAB
        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJgKGY8bBm1RYDqI0XPOKjJvhsRTPJmm+oH/LJjv1gWd+MKCvv1tdX9yrtOvP8CYTVbb58oj+QrOxZ28CvZ56WJINB4Qd/5zL8ThcNxVDlN5BjK5+5Ab5NYzl7UuKjUH9CnDyM+8e6dcu70Jn3V7pnGnNhtgM+P4O4jMv4ysYn3wIDAQAB";


        // Decode the Base64-encoded public key string
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr.getBytes());


        // Create a KeyFactory object for RSA keys
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // Create an X509EncodedKeySpec object using the public key bytes
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        // Generate a public key object using the key factory and the key spec
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);


        PrivateKey privateKey = keyFactory.generatePrivate(publicKeySpec);

        // Message to encrypt
        String message = "toOrder";

        // Encrypt the message using the public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        // Base64 encode the encrypted message
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessage);
        System.out.println("Encrypted message: " + encodedMessage);

//        // Decrypt the message using the private key
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encodedMessage));
//        System.out.println("Decrypted message: " + new String(decryptedMessage, "UTF-8"));
    }
}
