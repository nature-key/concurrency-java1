package com.jiepi.concurrency.seria;

import java.io.*;

public class Single implements  Serializable{

    private static Single sigle = null;
    private String name;
    private Single() {
    }
    public Single(String name) {
        this.name = name;
    }

    public static Single getInstance() {
        if (sigle == null) {
            sigle = new Single();
        }
        return sigle;
    }





    private Object readResolve() throws ObjectStreamException {
        return Single.getInstance();
    }
}







class test0{
    public static void main(String[] args) throws  Exception{
        File file = new File("sigle.txt");
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file)); // 注意这里使用的是 ObjectOutputStream 对象输出流封装其他的输出流

        oout.writeObject(Single.getInstance());
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  // 使用对象输入流读取序列化的对象
        Object newPerson = oin.readObject(); //
        oin.close();
        System.out.println( Single.getInstance()==newPerson);
    }

}
