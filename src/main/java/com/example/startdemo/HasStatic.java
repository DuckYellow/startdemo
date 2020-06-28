package com.example.startdemo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * HasStatic
 *
 * @author xuweihang@qbb.com
 * @date 2020-02-12 01:52
 */
public class HasStatic {
    private static final String KEY = "";
    private static int x = 100;

    public static void main(String[] args) {
        BigDecimal splitPrice = new BigDecimal("1601").divide(new BigDecimal(3), 0, RoundingMode.FLOOR);
        System.out.println(splitPrice);
    }
}
