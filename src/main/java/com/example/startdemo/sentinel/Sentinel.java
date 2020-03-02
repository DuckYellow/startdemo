package com.example.startdemo.sentinel;

import java.lang.annotation.*;

/**
 * @author xuweihang@qbb.com
 * @date 2020-02-28 19:55
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Sentinel {
    String description() default "";
}