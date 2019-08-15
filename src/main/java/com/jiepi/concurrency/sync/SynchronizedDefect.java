package com.jiepi.concurrency.sync;

import java.util.concurrent.TimeUnit;

public class SynchronizedDefect {


    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::method, "T1");
        t1.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("--------22222222------------------");
        Thread t2 = new Thread(defect::method, "T2");
        t2.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("--------------------------");
        t2.interrupt();
        System.out.println("-------444444444444-------------------");
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }

    public synchronized void method() {
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.MINUTES.sleep(1);
            System.out.println("2111111111111111111111");
        } catch (InterruptedException e) {
            System.out.println("---333");
            System.out.println(Thread.currentThread().isInterrupted());
        }
    }


}
