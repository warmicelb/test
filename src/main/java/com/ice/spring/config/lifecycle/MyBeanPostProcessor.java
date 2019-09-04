package com.ice.spring.config.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自定义bean后置处理器（对bean的init方法进行拦截增强）
 * @ClassName MyBeanPostProcessor
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/3 2:27 PM
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization is called ,bean name:"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization is called ,bean name:"+beanName);
        return bean;
    }
}
