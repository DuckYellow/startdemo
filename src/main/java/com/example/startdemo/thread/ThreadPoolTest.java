package com.example.startdemo.thread;

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
//        testContinue();
//        testContinue2();
//        testBreak();

        ExecutorService exec = new ThreadPoolExecutor(0, 5,
                60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(15));

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


    public static void testContinue() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if (j == 3) {
                    break;
                }
            }
        }
        System.out.println(" >>> OK");
    }

    public static void testContinue2() {
        retry:
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if(j == 3) {
                    continue retry;
                }
            }
        }
        System.out.println(" >>> OK");
    }

    public static void testBreak() {
        label59:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if (j == 3) {
                    break label59;
                }
            }
        }
        label59:
        System.out.println(" >>> OK");
    }
}
