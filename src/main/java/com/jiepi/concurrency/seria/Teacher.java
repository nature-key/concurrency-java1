package com.jiepi.concurrency.seria;

import java.io.*;

public class Teacher implements Serializable{

    private String name = null;
    public  static Integer age = null;
    private String gender = null;

    public Teacher() {
        System.out.println("无参构造函数");
    }

    public Teacher(String name, Integer age, String gender) {
        System.out.println("有参构造函数");
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // 省略 set get 方法
    @Override
    public String toString() {
        return "[" + name + ", " + age + ", " + gender + "]";
    }
}

class test2 {

    public static void main(String[] args)  throws  Exception{
        File file = new File("Teacher1.out");
//        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file)); // 注意这里使用的是 ObjectOutputStream 对象输出流封装其他的输出流
//        Teacher person = new Teacher("洁癖",23,"母");
//        oout.writeObject(person);
//        oout.close();
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  // 使用对象输入流读取序列化的对象
        Teacher newPerson = (Teacher)oin.readObject(); // 没有强制转换到Person类型
        System.out.println(newPerson);
        oin.close();
    }
}
