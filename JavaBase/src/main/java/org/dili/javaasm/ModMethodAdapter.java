package org.dili.javaasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ModMethodAdapter extends MethodVisitor {

    public ModMethodAdapter(MethodVisitor methodVisitor) {
        super(Opcodes.ASM5, methodVisitor);
    }

    @Override
    public void visitCode() {
        // 在方法前面添加输出
        // 从java/lang/System类中获取名为out的静态字段，该字段的类型为java/io/PrintStream
        // GETSTATIC指令将该字段的值压入操作数栈上
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // LDC指令用于将常量加载到栈上
        mv.visitLdcInsn("Hello, World!");
        // 调用java/io/PrintStream类的println方法，它接受一个java/lang/String类型的参数，并且没有返回值
        // INVOKEVIRTUAL指令用于调用实例方法
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        super.visitCode();
    }

    // @Override
    // public void visitMethodInsn(int opcode, String owner, String name, String descriptor) {
    //     // 在方法前面添加输出
    //     // 从java/lang/System类中获取名为out的静态字段，该字段的类型为java/io/PrintStream
    //     // GETSTATIC指令将该字段的值压入操作数栈上
    //     mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    //     // LDC指令用于将常量加载到栈上
    //     mv.visitLdcInsn("Hello, World!");
    //     // 调用java/io/PrintStream类的println方法，它接受一个java/lang/String类型的参数，并且没有返回值
    //     // INVOKEVIRTUAL指令用于调用实例方法
    //     mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    //     super.visitMethodInsn(opcode, owner, name, descriptor);
    // }
}

