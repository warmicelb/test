package com.ice.spring.config.comp.processor;

import com.ice.spring.bean.Brother;
import com.ice.spring.bean.Son;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

/**
 * Bean的定义注册后置处理器
 * @ClassName MyBeanDefinitionRegistryPostProcessor
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/16 7:23 PM
 **/
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    //先执行
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry 开始调用postProcessBeanDefinitionRegistry-------"+registry.getBeanDefinitionCount());
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Brother.class);
        registry.registerBeanDefinition("brother",rootBeanDefinition);
        //也可以通过建造者模式构建bean定义对象
//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Son.class).getBeanDefinition();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry 开始调用postProcessBeanFactory-------"+beanFactory.getBeanDefinitionCount());
    }
}
