package org.dili.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        // 被代理对象
        Ouc ouc = new Ouc();
        // 获取代理对象
        University proxyInstance = (University) ProxyClass.getProxyInstance(ouc);
        // 当通过代理对象调用方法时，会调用被代理对象中的同名方法
        String name = proxyInstance.getName();
        System.out.println(name);
        proxyInstance.Info("Qingdao");
    }
}
