package org.dili.jndi;

import java.io.Serializable;
import java.rmi.Remote;

public class Person implements Remote, Serializable {
    // 远程调用的类需要满足的条件
    // 继承Remote、Serializable
    // 方法为public
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String sayhello(){
        return "Hello";
    }
}