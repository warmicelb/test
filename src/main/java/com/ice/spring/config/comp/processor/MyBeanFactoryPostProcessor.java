package com.ice.spring.config.comp.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @ClassName MyBeanFactoryPostProcessor
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/16 7:01 PM
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcesso开始调用--------");
        //这里所有的bean的定义，已经加载到beanFactory,但是bean的实例没有创建
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
}
}
