package org.dili.xstream;

import com.thoughtworks.xstream.XStream;

public class CarTest {
    public static void main(String[] args) {
        // // 序列化
        // Car car = new Car("benchi", 2000000);
        // XStream xStream = new XStream();
        // String xml = xStream.toXML(car);
        // System.out.print(xml);

        // 反序列化
        String xml = "<Xstream.Car serialization=\"custom\">\n" +
                "  <Xstream.Car>\n" +
                "    <default>\n" +
                "      <price>2000000</price>\n" +
                "      <name>benchi</name>\n" +
                "    </default>\n" +
                "  </Xstream.Car>\n" +
                "</Xstream.Car>";
        XStream xStream = new XStream();
        Car car = (Car) xStream.fromXML(xml);
        System.out.print(car);
    }
}