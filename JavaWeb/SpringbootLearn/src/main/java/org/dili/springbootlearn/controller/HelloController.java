package org.dili.springbootlearn.controller;

import org.dili.springbootlearn.dao.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${name}")
    private String name;

    @Value("${Person.username}")
    private String username;

    @Value("${Person.age}")
    private int age;

    @Autowired
    private Environment env;

    @RequestMapping("/hello2")
    public String hello2() {
        System.out.println("name" + env.getProperty("name"));
        System.out.println("age" + env.getProperty("Person.age"));
        return "Hello SpringBoot!";
    }

    @RequestMapping("/hello1")
    public String hello1() {
        System.out.println("name:" + name);
        System.out.println("username:" + username);
        System.out.println("age:" + age);
        return "hello, springboot!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello, init springboot!";
    }

    @Autowired
    private Person person;
    @RequestMapping("/hello3")
    public String hello3() {
        System.out.println(person);
        return "hello, SpringBoot";
    }
}
