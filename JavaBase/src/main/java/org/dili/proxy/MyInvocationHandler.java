package org.dili.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    //使用被代理类的对象进行赋值
    private Object object;

    //绑定被代理对象  也可以写在构造函数中
    public void bind(Object object) {
        this.object = object;
    }

    // 当通过代理类对象调用方法时（也是被代理类需要调用的方法），会自动调用invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里的Method是代理对象调用的方法，同时也是被代理对象需要调用的方法
        // object 即是前面绑定的被代理类对象
        Object returnVlaue = method.invoke(object,args);
        return returnVlaue;
    }
}
