package org.dili.snakeyaml.learn;

import org.yaml.snakeyaml.Yaml;

public class SnakeYamlTest {
    public static void main(String[] args) {
        // 序列化
        // Yaml yaml = new Yaml();
        // Person person = new Person("mike", 18);
        // String str = yaml.dump(person);
        // System.out.println(str);

        // 反序列化
        String str = "!!SnakeYaml.Person {age: 18, username: mike}";
        Yaml yaml = new Yaml();
        Person person = (Person) yaml.load(str);
        System.out.print(person);
    }
}
