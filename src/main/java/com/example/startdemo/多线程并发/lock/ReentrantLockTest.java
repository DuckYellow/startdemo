package com.example.startdemo.多线程并发.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 读读互斥
 * 读写互斥
 * 写写互斥
 */
public class ReentrantLockTest {
    private static final Lock lock = new ReentrantLock();

    private int num;

    public void add() {
        lock.lock();
        try {
            num++;
        } catch (Exception e) {
            // ignored
        } finally {
            lock.unlock();
        }
    }
}
