package com.jiepi.concurrency.seria;

import java.io.*;

 class Person implements Serializable {
    private String name = null;
    transient private Integer age = null;
    private String gender = null;

    public Person() {
        System.out.println("none-arg constructor");
    }

    public Person(String name, Integer age, String gender) {
        System.out.println("arg constructor");
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
class SimpleSerial {
    public static void main(String[] args) throws Exception {
        File file = new File("person.txt");
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file)); // 注意这里使用的是 ObjectOutputStream 对象输出流封装其他的输出流
        Person person = new Person("John", 101, "test");
        oout.writeObject(person);
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  // 使用对象输入流读取序列化的对象
        Object newPerson = oin.readObject(); // 没有强制转换到Person类型
        oin.close();
        System.out.println(newPerson);
    }
}