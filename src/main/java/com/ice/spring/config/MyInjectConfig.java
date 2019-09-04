package com.ice.spring.config;

import com.ice.spring.bean.Brother;
import com.ice.spring.bean.Person;
import com.ice.spring.config.extend.MyTypeFilter;
import com.ice.spring.dao.PersonDao;
import com.ice.spring.service.PersonService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

/**
 * 配置类（基于注解的方式）
 * @ClassName Config
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 2:40 PM
 **/
//配置类
@Configuration
@ComponentScan({"com.ice.spring.service","com.ice.spring.controller"})
@PropertySource(value = "classpath:spring.properties")
public class MyInjectConfig {

    @Bean
    public Brother brother(){
        return new Brother();
    }

    /**
     * 这里在上面配置扫描的情况下，再定义一个personService
     * 测试结果：这里如果注册的beanNamepersonService，那最终只有一个bean注册，就是下面注解注册的bean
     * 如果之类定义的beanName为personService1（与扫描生成的beanName是personService的不冲突），则会注册两个相同类型的bean，但是beanName不同，这时注入时，默认@Autowired注解会注入personService，也可以通过@Qualifier注解指定注入特定beanName的bean对象。
     * 注意，如果没有使用@Qualifier时，如果这里注册bean时，使用了@Primary注解，则在注册相同类型的bean时，会优先注入使用了@Primary注解的bean.
     * @Primary spring装配时，默认首选的bean
     * @return
     */
    @Primary
    @Bean
    public PersonService personService1(){
        return new PersonService(3);
    }
}
