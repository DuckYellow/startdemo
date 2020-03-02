package com.example.startdemo.sentinel;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xuweihang@qbb.com
 * @date 2020-02-29 14:29
 */
@Aspect
@Component
public class SentinelAspect {
    ////每秒只发出5个令牌，此处是单进程服务的限流,内部采用令牌捅算法实现
    private static RateLimiter rateLimiter = RateLimiter.create(1.0);

    @Pointcut("@annotation(com.example.startdemo.sentinel.Sentinel)")
    public void method() {
    }

    @Around("method()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("---------");

        Boolean flag = rateLimiter.tryAcquire();
        System.out.println(flag);
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }


}
