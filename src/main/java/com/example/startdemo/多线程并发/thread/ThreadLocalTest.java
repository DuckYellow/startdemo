package com.example.startdemo.多线程并发.thread;

public class ThreadLocalTest {
    private static final ThreadLocal<Long> TIME_ESTIMATE = new ThreadLocal<Long>();
    private static final InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

    public static void main(String[] args) {
        TIME_ESTIMATE.set(10l);
        TIME_ESTIMATE.get();

    }
}
