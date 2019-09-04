package com.ice.spring.config.lifecycle;

import com.ice.spring.bean.lifecycle.Bus;
import com.ice.spring.bean.lifecycle.Subway;
import com.ice.spring.bean.lifecycle.Train;
import org.springframework.context.annotation.*;

/**
 * 配置类（基于注解的方式）
 * @ClassName Config
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 2:40 PM
 **/
//配置类
@Configuration
@ComponentScan(value = "com.ice.spring")
@Import({Subway.class, Train.class,MyBeanPostProcessor.class})
public class MyLifeCycleConfig {

    /**
     * 基于注解的方式，配置bean(这里可以通过@Bean中的注解指定创建，销毁的方法)
     * @return
     */
    @Bean(initMethod = "init",destroyMethod = "destroy")
    //这里如果是多实例的情况下，则会每次创建实例后，调用init方法。destroy方法不会被调用.
//    @Scope("prototype")
    public Bus bus(){
        return new Bus();
    }
}
