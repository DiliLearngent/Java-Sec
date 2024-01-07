package org.dili.bytecode;

public class bytecodeTest extends Person implements helloInterface{
    private String sex;

    public bytecodeTest(String name, int age, String sex) {
        super(name, age);
        this.sex = sex;
    }

    public void sayHello() {
        System.out.println("Hello" + super.name);
    }
}
