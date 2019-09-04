package com.ice;

import com.ice.spring.bean.Father;
import com.ice.spring.bean.Person;
import com.ice.spring.bean.Son;
import com.ice.spring.bean.lifecycle.Bus;
import com.ice.spring.config.*;
import com.ice.spring.config.lifecycle.MyLifeCycleConfig;
import com.ice.spring.controller.PersonController;
import com.ice.spring.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName SpringTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 3:15 PM
 **/
public class SpringTest {

    /**
     * 测试配置类启动，组件扫描
     */
    @Test
    public void test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        //获取容器中的所有组件名称
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    /**
     * 测试bean的加载方式，单例或多实例，懒加载
     */
    @Test
    public void test02(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig1.class);
        //这里还是可以获取到懒加载和多实例模式的bean的名称，但是是不存在相应实例的
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        Person person1 = applicationContext.getBean(Person.class);
        Person person2 = applicationContext.getBean(Person.class);
        //之类测试多实例下返回的对象是否相同
        System.out.println(person1==person2);
        System.out.println("before use son");
        Object son = applicationContext.getBean("son");
        System.out.println("after get son");
    }

    /**
     * 测试bean的加载方式，单例或多实例，懒加载
     */
    @Test
    public void test03(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConditionalConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
    }

    /**
     * 测试import注解
     */
    @Test
    public void test04(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyImportConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //注意：这里通过import导入的类实例的id为全类名
            System.out.println(beanDefinitionName);
        }
        /**
         * factoryBean可以通过&+beanName方式获取BeanFactory对象
         */
        Object bean = applicationContext.getBean("&father");
        System.out.println(bean);
    }

    /**
     * 测试bean的生命周期
     */
    @Test
    public void test05(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyLifeCycleConfig.class);
//        Bus bean1 = applicationContext.getBean(Bus.class);
//        Bus bean2 = applicationContext.getBean(Bus.class);
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    /**
     * 测试bean的注入
     */
    @Test
    public void test06(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyInjectConfig.class);
        Object brother = applicationContext.getBean("brother");
        System.out.println(brother);
        //这里可以取出环境变量中的值
        String name = applicationContext.getEnvironment().getProperty("user.name");
        System.out.println(name);

        System.out.println("测试注入的优先级");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //注意：这里通过import导入的类实例的id为全类名
            System.out.println(beanDefinitionName);
        }
//        applicationContext.getBean(PersonController.class);
        //测试注入的优先级
//        PersonController bean1 = applicationContext.getBean(PersonController.class);
//        bean1.printPerson();
    }
}
