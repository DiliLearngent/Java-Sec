package org.dili.jndi;

import org.dili.bytecode.Person;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
    public static void start() throws RemoteException, NamingException {
        // 用于创建RMI注册表
        LocateRegistry.createRegistry(1099);
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");

        // 初始化
        InitialContext initialContext = new InitialContext();
        // 实例化对象
        Person person = new Person("mike", 22);
        // 将person对象绑定到JNDI服务中
        initialContext.bind("person", person);
        initialContext.close();
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws NamingException, RemoteException, MalformedURLException, AlreadyBoundException {
        new RmiServer().start();
    }
}
