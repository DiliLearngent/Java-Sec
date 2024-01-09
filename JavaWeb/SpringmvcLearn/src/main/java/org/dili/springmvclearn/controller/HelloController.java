package org.dili.springmvclearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//声明控制器
@Controller
//主路径，此类的所有方法对应的路径都以此开头
@RequestMapping("/home")
public class HelloController {

    //从路径   即/home/login
    @RequestMapping("/login")
    public String login(Model model) {
        System.out.println("执行登录方法");
        model.addAttribute("username", "springmvc");
        return "login";
    }

    // 从路径   即/home/register
    @RequestMapping("/register")
    public String register() {
        System.out.println("执行注册方法");
        return "register";
    }

    @RequestMapping(
            value = {"/test", "/test1"},
            method = {RequestMethod.GET, RequestMethod.POST},
            params = {"username", "password"}
    )
    public String test(String username, String password) {
        System.out.println("username"+username);
        System.out.println("password"+password);
        return "test";
    }

    // RequestParam注解
    @RequestMapping(path="/testRequestParam")
    public String testRequestParam(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "password", required = false) String password)
    {
        System.out.println("username"+username);
        System.out.println("password"+password);
        return "test";
    }

    @RequestMapping(path="/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") String id) {
        System.out.println("id"+id);
        return "test";
    }

    @RequestMapping(path="/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println(body);
        return "test";
    }

    @RequestMapping(path = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Host") String host,
                                    @RequestHeader(value = "Accept-Encoding") String code) {
        System.out.println(host+"...."+code);
        return "test";
    }

    @RequestMapping(path="/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookie) {
        System.out.println(cookie);
        return "test";
    }
}
