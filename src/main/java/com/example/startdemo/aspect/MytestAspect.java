package com.example.startdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MytestAspect {

    @Pointcut("@annotation(itestAspect)")
    public void riskPointcut(ItestAspect itestAspect) {
    }

    @Before(value = "riskPointcut(itestAspect)", argNames = "joinPoint,itestAspect")
    public void a(JoinPoint joinPoint, ItestAspect itestAspect) {
        String v = itestAspect.v();
        String c = "";
        if (("AA").equals(v)) {
            Object[] args = joinPoint.getArgs();
            TestM object = (TestM) args[0];
            c = object.getA() + v;
            object.setA(c);
        }
    }

}