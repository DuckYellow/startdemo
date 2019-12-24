package com.example.startdemo.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author xuweihang@qbb.com
 * @date 2019-12-15 17:58
 */
public class ZeroEvenOdd {
    private int n;

    Semaphore A;
    Semaphore B;
    Semaphore C;

    public ZeroEvenOdd(int n) {
        this.n = n;
        A = new Semaphore(1);
        B = new Semaphore(0);
        C = new Semaphore(0);
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                B.release();
            } else {
                C.release();
            }
            printNumber.accept(i);
            A.acquire();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i += 2) {
            B.acquire();
            printNumber.accept(i);
            A.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i += 2) {
            C.acquire();
            printNumber.accept(i);
            A.release();
        }
    }

    public static void main(String[] args) {
        int a=2;
        for (int i = 0; i < a; i++) {
            if (i % 2 == 0) {

            } else {

            }
            System.out.println(i);
        }
    }
}
