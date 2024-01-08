package org.dili.jndi;

import javax.naming.InitialContext;

public class RmiClient {
    public static void main(String[] args) throws Exception {
        InitialContext initialContext = new InitialContext();
        Person p = (Person)initialContext.lookup("rmi://localhost:1099/person");
        String result = p.sayhello();
        System.out.print(result);
    }
}
