package com.example.startdemo.aspect;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItestAspect {

    String v();

}
