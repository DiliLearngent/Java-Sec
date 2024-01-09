package org.dili.snakeyaml.learn;

public class Person {
    private String username;
    private int age;

    public Person() {}
    public Person(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public int getAge() {
        System.out.println("aaaa");
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setAge(int age) {
        System.out.println("bbbb");
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
