package com.example.startdemo.leetcode;

import java.util.concurrent.Semaphore;

/**
 * @author xuweihang@qbb.com
 * @date 2019-12-15 17:24
 */
public class Foo {


    Semaphore A;
    Semaphore B;
    Semaphore C;

    public Foo() {
        A = new Semaphore(1);
        B = new Semaphore(0);
        C = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        A.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        B.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        B.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        C.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        C.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}
