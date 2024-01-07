package org.dili;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassInfoTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // String format = String.format("ClassName: %s\nClassLoader:%s\nClassBeingRedefined:%s\nProtectionDomain:%s\n", className, loader, classBeingRedefined, protectionDomain);
        // 输出加载的类名
        String format = String.format("Premain load className:%s", className);
        System.out.println(format);
        return new byte[0];
    }
}
