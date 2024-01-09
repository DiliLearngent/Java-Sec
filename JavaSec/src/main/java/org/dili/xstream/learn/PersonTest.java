package org.dili.xstream.learn;

import com.thoughtworks.xstream.XStream;
import org.dili.xstream.learn.Person;

public class PersonTest {
    public static void main(String[] args) {
        // 序列化
        // Person person = new Person("lucy", 22);
        // XStream xStream = new XStream();
        // String xml = xStream.toXML(person);
        // System.out.print(xml);

        // 反序列化
        String xml = "<Xstream.Person>\n" +
                "  <name>lucy</name>\n" +
                "  <age>22</age>\n" +
                "</Xstream.Person>";
        XStream xStream = new XStream();
        Person person1 = (Person) xStream.fromXML(xml);
        System.out.print(person1);

    }
}
