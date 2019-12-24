package com.example.startdemo.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author xuweihang@qbb.com
 * @date 2019-12-18 21:11
 */
public class H2O {
    private Semaphore semaphoreH = new Semaphore(2);
    private Semaphore semaphore0 = new Semaphore(1);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        semaphoreH.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphore0.acquire();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "H". Do not change or remove this line.
        releaseOxygen.run();
        semaphore0.release();
    }

    public int maxSubArray(int[] nums) {
        int ans, ma, sum;
        int len=nums.length;
        ans = nums[0];
        for(int i = 0 ; i < len ; i ++) {
            ma = ans;
            sum = 0;
            for(int j = i ; j < len ; j ++) {
                sum += nums[j];
                ma = Math.max(ma, sum);
                ans = Math.max(ans, ma);
            }
        }
        return ans;
    }
}
