package com.example.startdemo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * HasStatic
 *
 * @author xuweihang@qbb.com
 * @date 2020-02-12 01:52
 */
public class HasStatic {
    private static final String KEY = "";
    private static int x = 100;

    public static void testBigdecimal() {
        String weightAlarm = "10";
        BigDecimal qbbWeight = new BigDecimal("8.5");
        BigDecimal sfWeight = new BigDecimal("10");
        BigDecimal diff = qbbWeight.divide(sfWeight, 4, ROUND_HALF_UP).subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).abs();

        if (diff.compareTo(new BigDecimal(weightAlarm)) > 0) {
            //发货单号
            String deliveryCode = "aaa";
            //包材编码
            String extra = "【发货单重量校验报警】发货单=" + deliveryCode + ",包材编码=" + "aaa" +
                                   ",顺丰回传重量=" + sfWeight + ",货品计算重量=" + qbbWeight + ",核算误差=" + diff;
            System.out.println(extra);
        }
        System.out.println("ok");
    }


}
