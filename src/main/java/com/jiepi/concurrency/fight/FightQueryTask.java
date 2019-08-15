package com.jiepi.concurrency.fight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends  Thread implements  FightQuery {

    private final  String origin;

    private final  String destination;


    private final List<String> flightlist = new ArrayList<>();


     public  FightQueryTask(String origin,String destination,String airline){
         super("[+"+airline+"+]");
         this.destination=destination;
         this.origin=origin;
     }

    @Override
    public void run() {
        System.out.println(getName()+"--query--"+origin+"-->"+destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightlist.add(getName()+"_"+randomVal);
            System.out.println("successful: "+getName());
        }catch (Exception e){
            e.getStackTrace();
        }



    }

    @Override
    public List<String> get() {
        return this.flightlist;
    }
}
