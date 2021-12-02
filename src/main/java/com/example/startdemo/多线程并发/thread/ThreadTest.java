package com.example.startdemo.多线程并发.thread;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        Thread t1 = new Thread(() -> {
            T.methodA();
        });
        Thread t2 = new Thread(() -> {
            K.methodB();
        });
        t1.start();
        t2.start();
        while (true) {}
    }
}
class K {
    public static synchronized void methodB() {
        System.out.println("running method B");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ending method B");
    }
}
class T {
    public static synchronized void methodA() {
        System.out.println("running method A");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ending method A");
    }
}