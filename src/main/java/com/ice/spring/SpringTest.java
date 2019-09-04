package com.ice.spring;

import com.ice.spring.bean.Person;
import com.ice.spring.config.MyConfig;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName SpringTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/17 2:34 PM
 **/
public class SpringTest {

    public static void main(String[] args) {
        //1基于配置文件的方式
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        //2基于注解的方式
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        System.out.println(beanNamesForType[0]);
        //获取指定的bean
        Object person = applicationContext.getBean("person");
        System.out.println(person.toString());
    }
}
