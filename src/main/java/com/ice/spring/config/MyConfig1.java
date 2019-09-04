package com.ice.spring.config;

import com.ice.spring.bean.Person;
import com.ice.spring.bean.Son;
import com.ice.spring.dao.PersonDao;
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
//配置扫描的组件包路径
@ComponentScan(value = "com.ice.spring")
public class MyConfig1 {

    /**
     * 基于注解的方式，配置bean，这里的bean的name就是方法名(ps:可以通过@bean注解中指定bean的名称)
     * @return
     */
    @Bean("person")
    //这里默认是单实例，容器初始化时就会创建并放入ioc容器中
//    可以指定为prototype，多实例，这种情况下，容器初始化时不会创建，当真正要使用时，都会创建一个对象
    @Scope("prototype")
    public Person person111(){
        return new Person("ice","female");
    }

    @Bean
    //延迟加载，容器初始化时不会创建对象，只有第一次需要使用时，才会创建对象
    @Lazy
    public Son son(){
        System.out.println("开始初始化 son 实例");
        return new Son(1L);
    }
}
