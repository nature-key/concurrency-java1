package com.jiepi.concurrency.seria;

import java.io.*;

public class People1 implements Serializable {

    public    int age;

    private String name;

    private String gender;


    public People1() {
        System.out.println("无参构造函数");
    }

    public People1(int age, String name, String gender) {
        System.out.println("有参构造函数");
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + age + ", " + gender + "]";
    }
}


class Test6 {

    public static void main(String[] args) throws Exception {
        File file = new File("test.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        People1 people1 = new People1();
        people1.age=1;
        out.writeObject(people1);
        out.flush();
        people1.age=2;
        out.writeObject(people1);
        out.close();


        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

       People1 people11 = (People1) in.readObject();
       People1 people12 = (People1) in.readObject();
        System.out.println(people11.age);
        System.out.println(people12.age);
        in.close();


    }

}
