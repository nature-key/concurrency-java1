package com.jiepi.concurrency.stop;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {


    public static void main(String[] args) throws  Exception{
//        System.out.println("main thread is interrupted"+Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("main thread is interrupted"+Thread.interrupted());


        Thread.currentThread().interrupt();


        System.out.println("main thread is interrupted"+Thread.currentThread().isInterrupted());
        try{
            TimeUnit.MINUTES.sleep(1);
        }catch (InterruptedException e){
            System.out.println("i am be interrupted still");
        }


    }
}
