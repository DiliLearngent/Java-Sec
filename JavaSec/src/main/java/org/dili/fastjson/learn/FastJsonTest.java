package org.dili.fastjson.learn;

import com.alibaba.fastjson.JSON;
import org.dili.fastjson.learn.Person;
import org.dili.fastjson.learn.Person1;

import java.io.IOException;

public class FastJsonTest {
    public static void main(String[] args) throws IOException {
        // 将Java对象序列化成JSON字符串
        Person person = new Person("mike", 18);
        String personString = JSON.toJSONString(person);
        System.out.println(personString);

        // 将Json字符串反序列化成Java对象
        String str = "{\"@type\":\"java.FastJson.Person\",\"name\":\"lucy\",\"age\":18}";
        Object person1 = JSON.parseObject(str);
        System.out.println(person1.toString());

        // 将Json字符串反序列化成Java对象   使用注解映射
        String str1 = "{ \"user_name\":\"YYY\", \"user_age\":18}";
        Person1 person11 = JSON.parseObject(str1, Person1.class);
        System.out.println(person11.toString());

        // String json = "{\"@type\":\"java.lang.Runtime\"}";
        // ParserConfig.getGlobalInstance().addAccept("java.lang");
        // Runtime runtime = (Runtime) JSON.parseObject(json, Object.class);
        // runtime.exec("calc.exe");
    }
}
