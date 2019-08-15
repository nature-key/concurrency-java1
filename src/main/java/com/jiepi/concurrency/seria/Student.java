package com.jiepi.concurrency.seria;

import java.io.*;

public class Student implements Serializable {

    private int age;

    private String name;

    private String gender;

    public Student() {
        System.out.println("无参构造函数");
    }

    public Student(int age, String name, String gender) {
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

class Test {
    public static void main(String[] args) throws Exception {
        File file = new File("test.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        Student student = new Student(29, "洁癖", "母");
        out.writeObject(student);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Object std = in.readObject();
        in.close();
        System.out.println(std);
    }
}
