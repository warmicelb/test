package com.ice.spring.config.comp;

import com.ice.spring.bean.Person;
import com.ice.spring.config.comp.processor.MyBeanDefinitionRegistryPostProcessor;
import com.ice.spring.config.comp.processor.MyBeanFactoryPostProcessor;
import com.ice.spring.config.extend.MyTypeFilter;
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
@Import(MyBeanDefinitionRegistryPostProcessor.class)
public class ComponentConfig {

    @Bean
    public Person person(){
        return new Person("ice","female");
    }
}
