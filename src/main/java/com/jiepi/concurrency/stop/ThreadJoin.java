package com.jiepi.concurrency.stop;

import ch.qos.logback.core.util.TimeUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadJoin {


    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = IntStream.range(1,3).mapToObj(ThreadJoin::create).collect(Collectors.toList());

        threads.forEach(Thread::start);

        shortSleep();
        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i <10 ; i++) {

            System.out.println("=============main=========");
            System.out.println(Thread.currentThread().getName()+"+"+i);
            shortSleep();
        }


    }


    private  static  Thread create(int  seq){
        return new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"+"+i);
//                shortSleep();
            }

        },String.valueOf(seq));
    }


    private  static void shortSleep(){

        try{
            TimeUnit.SECONDS.sleep(15);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
