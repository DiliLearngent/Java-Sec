package org.dili.jndi;

import javax.naming.InitialContext;

public class ReferenceClient {
    public static void main(String[] args) throws Exception{
        InitialContext initialContext = new InitialContext();
        initialContext.lookup("rmi://127.0.0.1:7777/exec");
    }
}
