package org.dili.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LdapClient {
    public static void main(String[] args) throws NamingException {
        new InitialContext().lookup("ldap://127.0.0.1:9999/test");
    }
}
