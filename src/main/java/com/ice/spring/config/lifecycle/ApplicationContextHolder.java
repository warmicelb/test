package com.ice.spring.config.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 测试实现ApplicationContextAware，容器启动后，后置处理器会运行，设置上下文对象
 * @ClassName ApplicationContextHolder
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/3 3:36 PM
 **/
public class ApplicationContextHolder implements ApplicationContextAware {
    public static ApplicationContext ac = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
