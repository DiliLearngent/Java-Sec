package org.dili.javaasm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UpdateMethodClassVisitor extends ClassVisitor {
    private String deleteMethodName;
    private String deleteMethodDesc;
    private int addMethodAcc;
    private String addMethodName;
    private String addMethodDesc;
    private boolean flag = false;

    protected UpdateMethodClassVisitor(ClassVisitor cv, String deleteMethodName, String deleteMethodDesc, int addMethodAcc,
                                       String addMethodName, String addMethodDesc) {
        super(Opcodes.ASM5, cv);
        this.deleteMethodName = deleteMethodName;
        this.deleteMethodDesc = deleteMethodDesc;
        this.addMethodAcc = addMethodAcc;
        this.addMethodName = addMethodName;
        this.addMethodDesc = addMethodDesc;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // 删除名为deleteMethodName且描述为deleteMethodDesc的方法
        // 因为有的方法可能name一致，但是参数不同
        if (name.equals(deleteMethodName) && descriptor.equals(deleteMethodDesc)) {
            return null;
        }
        if (name.equals(addMethodName) && descriptor.equals(addMethodDesc)) flag = true;
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        // 添加名为addMethodName且描述为addMethodDesc的方法
        if (!flag) {
            MethodVisitor methodVisitor = super.visitMethod(addMethodAcc, addMethodName, addMethodDesc, null, null);
            if (methodVisitor != null) {
                // 访问方法的字节码
                methodVisitor.visitCode();
                // 添加return指令
                methodVisitor.visitInsn(Opcodes.RETURN);
                // 设置方法的最大操作数栈深度和最大局部变量表大小，空方法设置00即可
                methodVisitor.visitMaxs(0, 0);
                // 结束访问
                methodVisitor.visitEnd();
            }
        }
        super.visitEnd();
    }
}
