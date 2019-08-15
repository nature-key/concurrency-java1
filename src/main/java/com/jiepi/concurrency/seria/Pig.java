package com.jiepi.concurrency.seria;

import java.io.*;

class Pig implements Serializable {
   private String name = null;
    private Integer age = null;
   private String gender = null;

   public Pig() {
       System.out.println("none-arg constructor");
   }

    @Override
    public String toString() {
        return "[" + name + ", " + age + ", " + gender + "]";
    }

   public Pig(String name, Integer age, String gender) {
       System.out.println("arg constructor");
       this.name = name;
       this.age = age;
       this.gender = gender;
   }
    private void writeObject(ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField putFields = out.putFields();
        System.out.println("原name:" + name);
        name = "jiepi";//模拟加密
        putFields.put("name", name);
        System.out.println("加密后的密码" + name);
        out.writeFields();

    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField readFields = in.readFields();
        Object object = readFields.get("name", "");
        System.out.println("要解密的字符串:" + object.toString());
        name = "洁癖";//模拟解密,需要获得本地的密钥
    }
}

class test5 {
    public static void main(String[] args) throws Exception {
        File file = new File("Dog1.txt");
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file)); // 注意这里使用的是 ObjectOutputStream 对象输出流封装其他的输出流
        Pig pig = new Pig("洁癖", 101, "母");
        oout.writeObject(pig);
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  // 使用对象输入流读取序列化的对象
        Object newPerson = oin.readObject(); 
        oin.close();
        System.out.println(newPerson);
    }
}