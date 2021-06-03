package com.example.startdemo.多线程并发.thread;

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


    }

    public static void FutureTest() {
        //1
        CompletableFuture<String> bookFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("000000000");
            return "xanyi000001";
        });

        //2
        CompletableFuture<String> tableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("沉睡5秒");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "xanyi111110";
        });

        //CompletableFuture.allOf(bookFuture, tableFuture).join();
        CompletableFuture.anyOf(bookFuture, tableFuture).join();
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if (j == 3) {
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
