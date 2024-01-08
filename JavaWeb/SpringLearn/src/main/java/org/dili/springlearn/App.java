package org.dili.springlearn;

import org.dili.springlearn.object.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        // 实例化一个容器
        ClassPathXmlApplicationContext cl = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) cl.getBean("user");
        // User user = cl.getBean("user", User.class);
        System.out.print(user);
    }
}
