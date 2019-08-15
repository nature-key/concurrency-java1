package com.jiepi.concurrency.stop;

import java.util.stream.IntStream;

public class ThreadYield {


    public static void main(String[] args) {
//        IntStream.range(0,20).mapToObj(ThreadYield::create).forEach(Thread::start);


        Thread t1 = new Thread(()->{
             while (true){
                 System.out.println("t1");
             }
        });
        t1.setPriority(3);

        Thread t2 = new Thread(()->{
            while (true){
                System.out.println("t2");
            }
        });
        t2.setPriority(10);

        t1.start();
        t2.start();



    }

    private static  Thread create(int index){
        return new Thread(()->{
            System.out.println(index);
        });
    }

}
