package org.dili.mybatislearn;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dili.mybatislearn.mapper.UserMapper;
import org.dili.mybatislearn.object.User;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    @Test
    public void selectById() throws IOException {
        // 获取请求参数对象
        int id = 1;

        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.1 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 4.2 执行SQL语句
        User user = userMapper.selectById(id);
        System.out.print(user);
        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void selectByConditicon() throws IOException {
        // 获取请求参数对象
        int age = 23;
        String address = "Beijing";

        // 处理参数
        address = "%" + address + "%";

        // 封装对象
        // User user = new User();
        // user.setAge(age);
        // user.setAddress(address);

        // 构造map
        Map map = new HashMap();
        // map.put("age",age);
        map.put("address",address);

        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.1 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 4.2 执行SQL语句
        // 使用UserMapper接口方法1
        // List<User> users = userMapper.selectByCondition(age, address);
        // 使用UserMapper接口方法2
        // List<User> users = userMapper.selectByCondition(user);
        // 使用UserMapper接口方法3
        List<User> users = userMapper.selectByCondition(map);
        System.out.print(users);
        // 5.释放资源
        sqlSession.close();
    }


    @Test
    public void selectByConditiconSingle() throws IOException {
        // 获取请求参数对象
        int age = 23;
        String address = "Beijing";

        // 处理参数
        // address = "%" + address + "%";

        // 封装对象
        User user = new User();
        user.setAge(age);
        // user.setAddress(address);


        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.1 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 4.2 执行SQL语句
        // 使用UserMapper接口方法2
        List<User> users = userMapper.selectByConditionSingle(user);
        System.out.print(users);
        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void Add() throws IOException {
        // 获取请求参数对象
        String username = "liliu";
        String password = "111";
        int age = 23;
        String address = "Beijing";

        // 封装对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setAddress(address);

        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.1 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 4.2 执行SQL语句
        userMapper.add(user);
        // 提交事务
        sqlSession.commit();
        // 5.释放资源
        sqlSession.close();
    }


    @Test
    public void Update() throws IOException {
        // 获取请求参数对象
        int id = 4;
        String username = "liliu11";
        String password = "11111";
        int age = 23;
        String address = "Beijing";

        // 封装对象
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setAddress(address);

        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.1 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 4.2 执行SQL语句
        userMapper.update(user);
        // 提交事务
        sqlSession.commit();
        // 5.释放资源
        sqlSession.close();
    }
}