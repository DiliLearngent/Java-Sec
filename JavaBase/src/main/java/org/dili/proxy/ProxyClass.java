package org.dili.proxy;

import java.lang.reflect.Proxy;

public class ProxyClass {
    // 返回一个代理类对象
    // 这里object传入的是被代理类对象
    public static Object getProxyInstance(Object object) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);
    }
}
