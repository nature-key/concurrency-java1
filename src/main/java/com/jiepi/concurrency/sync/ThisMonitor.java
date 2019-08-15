package com.jiepi.concurrency.sync;

public class ThisMonitor {


    public synchronized  void method1(){

    }
    public  synchronized void method2(){

    }
    public void method3(){

        synchronized (this){

        }
    }

    public static synchronized void method4(){

    }

    public static synchronized void method5(){

    }

    public static  void method6(){

        synchronized (ThisMonitor.class){

        }
    }

}
