package org.dili.javaasm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) throws Exception{
        // ----------------解析字节码--------------------
        // // 实例化自定义Visitor
        // MyClassVisitor myClassVisitor = new MyClassVisitor();
        //
        // FileInputStream stream = new FileInputStream("JavaBase/target/classes/org/dili/bytecode/bytecodeTest.class");
        // // 加载字节码
        // ClassReader reader = new ClassReader(stream);
        // // 调用accept
        // reader.accept(myClassVisitor, 0);

        // -------------------添加与删除Field-------------------
        // FileInputStream stream = new FileInputStream("JavaBase/target/classes/org/dili/bytecode/bytecodeTest.class");
        // // 加载字节码
        // ClassReader reader = new ClassReader(stream);
        // ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        // // 实例化修改Field Visitor
        // UpdateFieldClassVisitor updateFieldClassVisitor = new UpdateFieldClassVisitor(writer, "sex", Opcodes.ACC_PRIVATE, "address", "Ljava/lang/String;");
        // // 调用accept
        // reader.accept(updateFieldClassVisitor, ClassReader.EXPAND_FRAMES);
        // FileOutputStream fileOutputStream = new FileOutputStream("JavaBase/out/temp.class");
        // byte[] updateByte = writer.toByteArray();
        // fileOutputStream.write(updateByte);
        // fileOutputStream.close();
        // // 测试
        // ClassReader classReader = new ClassReader(updateByte);
        // MyClassVisitor myClassVisitor = new MyClassVisitor();
        // classReader.accept(myClassVisitor, 0);

        // -------------------添加与删除Method-------------------
        // FileInputStream stream = new FileInputStream("JavaBase/target/classes/org/dili/bytecode/bytecodeTest.class");
        // // 加载字节码
        // ClassReader reader = new ClassReader(stream);
        // ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        // // 实例化修改Method Visitor
        // UpdateMethodClassVisitor updateMethodClassVisitor = new UpdateMethodClassVisitor(writer, "sayHello", "()V", Opcodes.ACC_PUBLIC, "newMethod", "()V");
        // reader.accept(updateMethodClassVisitor, ClassReader.EXPAND_FRAMES);
        // // 写入新class中
        // FileOutputStream fileOutputStream = new FileOutputStream("JavaBase/out/temp.class");
        // byte[] updateByte = writer.toByteArray();
        // fileOutputStream.write(updateByte);
        // fileOutputStream.close();
        // // 测试
        // ClassReader classReader = new ClassReader(updateByte);
        // MyClassVisitor myClassVisitor = new MyClassVisitor();
        // classReader.accept(myClassVisitor, 0);

        // -------------------修改Method：向方法开头加入输出-------------------
        FileInputStream stream = new FileInputStream("JavaBase/target/classes/org/dili/bytecode/bytecodeTest.class");
        // 加载字节码
        ClassReader reader = new ClassReader(stream);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        // 实例化修改Method Visitor
        ModMethodVisitor modMethodVisitor = new ModMethodVisitor(writer);
        reader.accept(modMethodVisitor, ClassReader.EXPAND_FRAMES);
        // 写入新class中
        FileOutputStream fileOutputStream = new FileOutputStream("JavaBase/out/temp.class");
        byte[] updateByte = writer.toByteArray();
        fileOutputStream.write(updateByte);
        fileOutputStream.close();
        // 测试
        ClassReader classReader = new ClassReader(updateByte);
        MyClassVisitor myClassVisitor = new MyClassVisitor();
        classReader.accept(myClassVisitor, 0);
    }
}

