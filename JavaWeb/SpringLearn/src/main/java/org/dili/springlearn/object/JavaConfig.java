package org.dili.springlearn.object;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 这个注解表示这不是一个普通的类，这是一个配置类，相当于Spring的配置文件
@Configuration
public class JavaConfig {
    // 将这个方法的返回值注入到Spring容器中
    @Bean
    SayHello sayHello() {
        return new SayHello();
    }
}
