package com.ice.spring.config.extend;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现自定义的条件判断是否注入的bean
 * @ClassName SystemCondition
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 5:23 PM
 **/
public class SystemCondition implements Condition {

    /**
     *  之类判断当前的操作系统是不是mac，是的话才会注入bean到容器中
     *
     * @param conditionContext 上下文对象（可以获取环境相关信息）
     * @param annotatedTypeMetadata 注解元信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //这里可以获取beanFactory对象
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        Environment environment = conditionContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        if(property.contains("Mac")){
            return true;
        }
        return false;
    }
}
