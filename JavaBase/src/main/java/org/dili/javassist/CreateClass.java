package org.dili.javassist;

import javassist.*;

public class CreateClass {
    public static void createPerson() throws Exception {
        // 1.创建一个ClassPool
        // ClassPool 对象保存所有已创建的 CtClasses，以便可以保证修改后的类之间的一致性
        ClassPool pool = ClassPool.getDefault();

        // 2.使用ClassPool创建一个新的类，即CtClass对象
        CtClass newClass = pool.makeClass("org.dili.javassist.Person");

        // 3.创建一个名为name的CtField字段对象
        CtField field1 = new CtField(pool.get("java.lang.String"), "name", newClass);
        // 设置字段名称的属性
        field1.setModifiers(Modifier.PRIVATE);
        // 将字段添加至新的类中, 并设置该字段的初始化值为Mike
        newClass.addField(field1, CtField.Initializer.constant("Mike"));

        // 4. 添加get和set方法
        newClass.addMethod(CtNewMethod.getter("getname", field1));
        newClass.addMethod(CtNewMethod.setter("setname", field1));

        // 5. 构造无参数构造器
        CtConstructor constructor = new CtConstructor(new CtClass[]{}, newClass);
        constructor.setBody("{name = \"lucy\";}");
        newClass.addConstructor(constructor);

        // 6. 构造有参数构造函数
        CtConstructor constructor1 = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, newClass);
        // $0 表示this    $1,$2,$3...表示方法参数
        constructor1.setBody("{$0.name = $1;}");
        newClass.addConstructor(constructor1);

        // 7.创建一个sayHello方法, 该方法没有参数，无返回值
        CtMethod method = new CtMethod(CtClass.voidType, "sayHello", new CtClass[]{}, newClass);
        // 设置方法的属性是public
        method.setModifiers(Modifier.PUBLIC);
        method.setBody("{System.out.println(name);}");
        newClass.addMethod(method);

        // 8.创建一个judgeName方法, 该方法返回布尔类型, 是否修改成功; 传入参数name
        CtMethod method1 = new CtMethod(CtClass.booleanType, "judgeName", new CtClass[]{pool.get("java.lang.String")}, newClass);
        method1.setModifiers(Modifier.PUBLIC);
        method1.setBody("{if($0.name==$1) return true; else return false;}");
        newClass.addMethod(method1);

        // 9.将创建的类编译为class文件
        newClass.writeFile("C:\\code\\javacode\\javassist-learn\\src\\main\\java");

        // 10.释放内存
        newClass.detach();

    }

    public static void main(String[] args) {
        try {
            createPerson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
