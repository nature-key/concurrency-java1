package com.jiepi.concurrency.seria;

import java.io.*;

class Dog implements Serializable {
    private String name = null;
    private Integer age = null;
    private String gender = null;
    public Dog() {
        System.out.println("none-arg constructor");
    }

    public Dog(String name, Integer age, String gender) {
        System.out.println("arg constructor");
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("name", String.class),
            new ObjectStreamField("gender", String.class)
    };
    @Override
    public String toString() {
        return "[" + name + ", " + age + ", " + gender + "]";
    }
}

class test4 {
    public static void main(String[] args) throws Exception {
        File file = new File("Dog1.txt");
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file)); // 注意这里使用的是 ObjectOutputStream 对象输出流封装其他的输出流
        Dog person = new Dog("洁癖2", 101, "test");
        oout.writeObject(person);
        oout.close();
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  // 使用对象输入流读取序列化的对象
        Object newPerson = oin.readObject(); // 没有强制转换到Person类型
        oin.close();
        System.out.println(newPerson);
    }
}