package com.jiepi.concurrency.seria;

import java.io.*;

public class Employee implements Externalizable {
    private String name;
    private String age;
    public Employee() {
        System.out.println("无参构造函数");
    }
    public Employee(String name, String age) {
        this.name = name;
        this.age = age;
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(age);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        age = in.readUTF();
    }
    @Override
    public String toString() {
        return "[" + name + ", " + age + "]";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
    }
}
class test8 {
    public static void main(String[] args) throws Exception {
        File file = new File("employee.txt");
        ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(file));
        Employee employee = new Employee("洁癖", "23");
        out.writeObject(employee);
        out.close();
        ObjectInputStream in =new ObjectInputStream(new FileInputStream(file));
        Object newEmployee = in.readObject();
        in.close();
        System.out.println(newEmployee);
    }

}
