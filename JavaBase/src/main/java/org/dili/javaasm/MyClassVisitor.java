package org.dili.javaasm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {
    // 调用父类构造方法，使用ASM Opcodes版本
    public MyClassVisitor() {
        super(Opcodes.ASM5);
    }

    // 重写visit方法，输出类名
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // print class name
        System.out.println("The class name:" + name);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    // 重写visitMethod方法，输出方法名
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // print method name
        System.out.println("The method name:" + name);
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        // print field name
        System.out.println("The field name:" + name);
        return super.visitField(access, name, descriptor, signature, value);
    }
}

