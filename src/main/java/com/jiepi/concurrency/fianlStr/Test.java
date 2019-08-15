package com.jiepi.concurrency.fianlStr;

public class Test {


    public static void main(String[] args) {
        String str = "jiepi";
        System.out.println(str.hashCode());
        str = "this is dog";
        System.out.println(str.hashCode());
        System.out.println(str);
    }

}
