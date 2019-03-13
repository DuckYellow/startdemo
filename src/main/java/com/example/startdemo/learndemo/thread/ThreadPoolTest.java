package com.example.startdemo.learndemo.thread;

import java.util.concurrent.*;

/**
 * @author blithe
 */
public class ThreadPoolTest {
    static class AtomTest implements Runnable {
        private volatile int i = 0;

        public int getVal() {
            return i;
        }

        public synchronized void inc() {
            i++;
            i++;
        }

        @Override
        public void run() {
            while (true) {
                inc();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        AtomTest at = new AtomTest();
        exec.execute(at);
        while (true) {
            int val = at.getVal();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
