package com.example.startdemo.learndemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(3);

    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {

        countDownLatch.await();
        countDownLatch.countDown();

        semaphore.acquire();
        semaphore.release();
    }
}
