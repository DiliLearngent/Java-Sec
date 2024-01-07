package org.dili.javaasm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class UpdateFieldClassVisitor extends ClassVisitor {
    // 删除字段的name
    private String deleteFieldName;
    // 添加字段的访问修饰符
    private int addFieldAcc;
    // 添加字段的name
    private String addFieldName;
    // 添加字段的描述符(类型)
    private String addFieldDesc;

    private Boolean flag = false;

    protected UpdateFieldClassVisitor(ClassVisitor cv, String deleteFieldName, int addFieldAcc, String addFieldName, String addFieldDesc) {
        super(Opcodes.ASM5, cv);
        this.deleteFieldName = deleteFieldName;
        this.addFieldAcc = addFieldAcc;
        this.addFieldName = addFieldName;
        this.addFieldDesc = addFieldDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        // 删除名为deleteFieldName的字段
        if (name.equals(deleteFieldName)) {
            return null;
        }
        if (name.equals(addFieldName)) flag = true;
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public void visitEnd() {
        // 添加名为addFieldName的字段
        if (!flag) {
            FieldVisitor fieldVisitor = super.visitField(addFieldAcc, addFieldName, addFieldDesc, null, null);
            if (fieldVisitor != null) {
                fieldVisitor.visitEnd();
            }
        }
        super.visitEnd();
    }
}
