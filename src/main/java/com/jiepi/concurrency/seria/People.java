package com.jiepi.concurrency.seria;

import java.io.*;

public class People implements Serializable {

    private int age;

    private String name;

    private String gender;

    public People() {
        System.out.println("无参构造函数");
    }

    public People(int age, String name, String gender) {
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


class Test51 {

    public static void main(String[] args) throws Exception {
        File file = new File("test.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        People people = new People(29, "洁癖", "母");
        out.writeObject(people);
        out.flush();
        System.out.println(new File("test.txt").length());
        out.writeObject(people);
        out.close();
        System.out.println(new File("test.txt").length());


        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        People std1 = (People)in.readObject();
        People std2 = (People)in.readObject();

        System.out.println(std1==std2);
        in.close();
    }

}
