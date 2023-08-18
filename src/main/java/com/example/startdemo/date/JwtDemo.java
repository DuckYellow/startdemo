package com.example.startdemo.date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtDemo {


    public static void main(String[] args) throws InterruptedException {
        String jwtString = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJoYW5kbGUiOiJ4eHd3aGgiLCJzaWQiOiI3QzI2QkYwRjg3QjQ0Qjc2MUEyRUU5RDg5QzUzRDVGQSIsImlzcyI6Inh4d3doaC5teXNob3BsaW5lc3RnLmNvbSIsImF1ZCI6IjhhODBmNDI5NmQzZDM1MzNjNGYzZTJkZTU1MTc1YjVjYjgzNTY2MDkiLCJzdWIiOiI0MjA4ODE4NTAyIiwiaWF0IjoxNjgzNjIzMDQ5LCJuYmYiOjE2ODM2MjMwNDksImV4cCI6MTY4MzYyMzEwOSwianRpIjoiY2E2NjQwZTItODA2Mi00M2ZkLTg4NTItZmY4MTZlNmZhZGE3In0.p3c-EV1tT9DhsM9lYEG63MtCjyPCfuTTAc5s2tABRS8";
        String appSecret = "26d09cd5779d11382f696626dc1a39deb13b6c3a";

//        String[] jwtParts = jwtString.split("\\.");
//        String encodedHeader = jwtParts[0];
//        String decodedHeader = new String(Base64.getUrlDecoder().decode(encodedHeader), StandardCharsets.UTF_8);
//
//        // 解码JWT的Payload
//        String encodedPayload = jwtParts[1];
//        String decodedPayload = new String(Base64.getUrlDecoder().decode(encodedPayload), StandardCharsets.UTF_8);
//
//        // 打印JWT的Header和Payload
//        System.out.println("Header: " + decodedHeader);
//        System.out.println("Payload: " + decodedPayload);

        // 生成密钥
        String key = "";
        Map<String, Object> map = new HashMap<>();
        map.put("handle", "xxxwwww");
        map.put("aud", "appasdasdsa");


        // 构建JWT
        String jwt = Jwts.builder()
                .setClaims(map)
                .setIssuedAt(new Date()) // 设置JWT签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 6 * 60 * 60 * 1000)) // 设置JWT过期时间
                .signWith(generateSignKeyWithHs256(appSecret)) // 使用密钥进行签名
                .compact();

        System.out.println("生成的JWT: " + jwt);


        // 验证JWT
        Claims claims2 = Jwts.parserBuilder()
                .setSigningKey(generateSignKeyWithHs256(appSecret)) // 使用密钥进行验证
                .build()
                .parseClaimsJws(jwtString)
                .getBody();

        System.out.println("验证的主题: " + claims2.get("handle"));
        System.out.println("验证的主题: " + claims2.getAudience());
        System.out.println("验证的主题: " + claims2.getSubject());
        System.out.println("验证的签发时间: " + claims2.getIssuedAt());
        System.out.println("验证的过期时间: " + claims2.getExpiration());
    }

    public static Key generateSignKeyWithHs256(String secretKey) {
        Key key = null;

        byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

        return key;
    }
}

