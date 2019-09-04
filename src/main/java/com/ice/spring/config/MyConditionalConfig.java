package com.ice.spring.config;

import com.ice.spring.bean.Person;
import com.ice.spring.config.extend.SystemCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyConditionalConfig
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 5:17 PM
 **/
@Configuration
public class MyConditionalConfig {

    @Bean
    //之类可以指定条件判断是否注册bean到容器中
    @Conditional(SystemCondition.class)
    public Person person(){
        return new Person("conditionPerson","female");
    }
}
