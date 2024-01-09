package org.dili.mybatislearn;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dili.mybatislearn.object.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.执行sql
        List<User> users = sqlSession.selectList("test.selectAll");
        System.out.print(users);

        // 5.释放资源
        sqlSession.close();

    }
}

