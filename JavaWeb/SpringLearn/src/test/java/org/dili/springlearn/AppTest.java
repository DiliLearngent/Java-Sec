package org.dili.springlearn;

import static org.junit.Assert.assertTrue;

import org.dili.springlearn.object.*;
import org.dili.springlearn.object.NewPerson;
import org.dili.springlearn.object.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void TestJavaConfig() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        SayHello hello = ctx.getBean(SayHello.class);
        System.out.print(hello.sayHello("mike"));
    }

    @Test
    public void TestPerson() {
        ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) cx.getBean("person");
        person.getCat().shout();
        person.getDog().shout();
    }

    @Test
    public void TestNewPerson() {
        ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("newApplicationContext.xml");
        NewPerson newperson = (NewPerson) cx.getBean("newperson");
        newperson.getCat().shout();
        newperson.getDog().shout();
    }

    @Test
    public void TestService() {
        ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("aopContext.xml");
        ServiceInterface service = (ServiceInterface) cx.getBean("userservice");
        service.delete();
    }

    @Test
    public void TestService2() {
        ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("aopContext2.xml");
        ServiceInterface service = (ServiceInterface) cx.getBean("userservice");
        service.delete();
    }

    @Test
    public void TestService3() {
        ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("aopContext3.xml");
        ServiceInterface service = (ServiceInterface) cx.getBean("userservice");
        service.delete();
    }
}
