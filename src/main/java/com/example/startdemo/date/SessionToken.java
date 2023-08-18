package com.example.startdemo.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 应用授权登录信息
 * @author: david.jiang
 * @date: 2021/9/14 12:04 下午
 **/
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SessionToken implements Serializable {

    private static final long serialVersionUID = 8403421868273710560L;

    /**
     * 店铺handle
     */
    private String handle;

    /**
     * 用户会话ID，当前为http session id
     */
    private String sid;

    /**
     * 发行者，当前为店铺的admin地址
     */
    private String iss;

    /**
     * audience，当前为应用的appKey
     */
    private String aud;

    /**
     * 签发主体，当前为登录用户的uid
     */
    private String sub;

    /**
     * 发型时间，当前为token的签发时间
     */
    private Long iat;

    /**
     * 激活时间，签发后立即生效
     */
    private Long nbf;
    
    /**
     * 超时时间，激活时间+1分钟
     */
    private Long exp;

    /**
     * ca6640e2-8062-43fd-8852-ff816e6fada7
     */
    private String jti;

}
