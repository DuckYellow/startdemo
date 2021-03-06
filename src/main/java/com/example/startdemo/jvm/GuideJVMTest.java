package com.example.startdemo.jvm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuweihang@qbb.com
 * @date 2020-08-02 14:44
 */
@Controller
public class GuideJVMTest {

    //curl localhost:8080/cpu/loop
    //top -Hp PID(top -o -cpu)
    //printf '%x' PID

    /**
     * 模拟CPU占满
     */
    @GetMapping("/cpu/loop")
    public void testCPULoop() throws InterruptedException {
        System.out.println("请求cpu死循环");
        Thread.currentThread().setName("loop-thread-cpu");
        int num = 0;
        while (true) {
            num++;
            if (num == Integer.MAX_VALUE) {
                System.out.println("reset");
            }
            num = 0;
        }
    }

    //java -jar -Xms500m -Xmx500m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:/tmp/heaplog.log analysis-demo-0.0.1-SNAPSHOT.jar
    //jstat -gc pid
    //mat

    /**
     * 模拟内存泄漏
     */
    @GetMapping(value = "/memory/leak")
    public String leak() {
        System.out.println("模拟内存泄漏");
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
        localVariable.set(new Byte[4096 * 1024]);// 为线程添加变量
        return "ok";
    }

    ExecutorService service = new ThreadPoolExecutor(4, 10,
            0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    //ps -ef|grep java

    /**
     * 模拟死锁
     */
    @GetMapping("/cpu/test")
    public String testCPU() throws InterruptedException {
        System.out.println("请求cpu");
        Object lock1 = new Object();
        Object lock2 = new Object();
        service.submit(new DeadLockThread(lock1, lock2), "deadLookThread-" + new Random().nextInt());
        service.submit(new DeadLockThread(lock2, lock1), "deadLookThread-" + new Random().nextInt());
        return "ok";
    }

    class DeadLockThread implements Runnable {
        private Object lock1;
        private Object lock2;

        DeadLockThread(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "get lock2 and wait lock1");
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "get lock1 and lock2 ");
                }
            }
        }
    }

    /**
     * 线程频繁切换
     *
     * @param num
     * @return
     */
    @GetMapping(value = "/thread/swap")
    public String theadSwap(int num) {
        System.out.println("模拟线程切换");
        for (int i = 0; i < num; i++) {
            new Thread(new ThreadSwap1(new AtomicInteger(0)), "thread-swap" + i).start();
        }
        return "ok";
    }

    class ThreadSwap1 implements Runnable {
        private AtomicInteger integer;

        public ThreadSwap1(AtomicInteger integer) {
            this.integer = integer;
        }

        @Override
        public void run() {
            while (true) {
                integer.addAndGet(1);
                Thread.yield(); //让出CPU资源
            }
        }
    }

    //top命令使我们最常用的Linux命令之一，它可以实时的显示当前正在执行的进程的CPU使用率，内存使用率等系统信息。top -Hp pid 可以查看线程的系统资源使用情况。

    //vmstat是一个指定周期和采集次数的虚拟内存检测工具，可以统计内存，CPU，swap的使用情况，它还有一个重要的常用功能，用来观察进程的上下文切换。字段说明如下:
    //r: 运行队列中进程数量（当数量大于CPU核数表示有阻塞的线程）
    //b: 等待IO的进程数量
    //swpd: 使用虚拟内存大小
    //free: 空闲物理内存大小
    //buff: 用作缓冲的内存大小(内存和硬盘的缓冲区)
    //cache: 用作缓存的内存大小（CPU和内存之间的缓冲区）
    //si: 每秒从交换区写到内存的大小，由磁盘调入内存
    //so: 每秒写入交换区的内存大小，由内存调入磁盘
    //bi: 每秒读取的块数
    //bo: 每秒写入的块数
    //in: 每秒中断数，包括时钟中断。
    //cs: 每秒上下文切换数。
    //us: 用户进程执行时间百分比(user time)
    //sy: 内核系统进程执行时间百分比(system time)
    //wa: IO等待时间百分比
    //id: 空闲时间百分比

    //pidstat命令
    //
    //pidstat 是 Sysstat 中的一个组件，也是一款功能强大的性能监测工具，top 和 vmstat 两个命令都是监测进程的内存、CPU 以及 I/O 使用情况，而 pidstat 命令可以检测到线程级别的。pidstat命令线程切换字段说明如下：
    //UID ：被监控任务的真实用户ID。
    //TGID ：线程组ID。
    //TID：线程ID。
    //cswch/s：主动切换上下文次数，这里是因为资源阻塞而切换线程，比如锁等待等情况。
    //nvcswch/s：被动切换上下文次数，这里指CPU调度切换了线程。

    //jstack命令
    //jstack是JDK工具命令，它是一种线程堆栈分析工具，最常用的功能就是使用 jstack pid 命令查看线程的堆栈信息，也经常用来排除死锁情况。

    //jstat 命令
    //它可以检测Java程序运行的实时情况，包括堆内存信息和垃圾回收信息，我们常常用来查看程序垃圾回收情况。常用的命令是jstat -gc pid。信息字段说明如下：
    //S0C：年轻代中 To Survivor 的容量（单位 KB）；
    //S1C：年轻代中 From Survivor 的容量（单位 KB）；
    //S0U：年轻代中 To Survivor 目前已使用空间（单位 KB）；
    //S1U：年轻代中 From Survivor 目前已使用空间（单位 KB）；
    //EC：年轻代中 Eden 的容量（单位 KB）；
    //EU：年轻代中 Eden 目前已使用空间（单位 KB）；
    //OC：老年代的容量（单位 KB）；
    //OU：老年代目前已使用空间（单位 KB）；
    //MC：元空间的容量（单位 KB）；
    //MU：元空间目前已使用空间（单位 KB）；
    //YGC：从应用程序启动到采样时年轻代中 gc 次数；
    //YGCT：从应用程序启动到采样时年轻代中 gc 所用时间 (s)；
    //FGC：从应用程序启动到采样时 老年代（Full Gc）gc 次数；
    //FGCT：从应用程序启动到采样时 老年代代（Full Gc）gc 所用时间 (s)；
    //GCT：从应用程序启动到采样时 gc 用的总时间 (s)。

    //jmap命令
    //jmap也是JDK工具命令，他可以查看堆内存的初始化信息以及堆内存的使用情况，还可以生成dump文件来进行详细分析。查看堆内存情况命令jmap -heap pid。

    //mat内存工具
    //MAT(Memory Analyzer Tool)工具是eclipse的一个插件(MAT也可以单独使用)，它分析大内存的dump文件时，可以非常直观的看到各个对象在堆空间中所占用的内存大小、类实例数量、对象引用关系、利用OQL对象查询，以及可以很方便的找出对象GC Roots的相关信息。
}
