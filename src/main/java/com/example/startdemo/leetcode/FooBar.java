package com.example.startdemo.leetcode;

import java.util.concurrent.Semaphore;

/**
 * @author xuweihang@qbb.com
 * @date 2019-12-15 17:44
 */
public class FooBar {
    private int n;

    Semaphore A;
    Semaphore B;


    public FooBar(int n) {
        this.n = n;
        A = new Semaphore(1);
        B = new Semaphore(0);

    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            A.acquire();
            printFoo.run();
            B.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            B.acquire();
            printBar.run();
            A.release();
        }
    }
}
