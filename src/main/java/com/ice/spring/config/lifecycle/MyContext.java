package com.ice.spring.config.lifecycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 实现BeanNameAware,可以获取bean的名称
 * @ClassName MyContext
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/4 10:41 AM
 **/
@Component
public class MyContext implements BeanNameAware, EmbeddedValueResolverAware {
    /**
     * beanName
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("obtain beanName:" + name);
    }

    /**
     * 值解析
     * @param resolver
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String result = resolver.resolveStringValue("这里测试aware里填充属性值user.name${user.name},user.pwd:${user.pwd}");
        System.out.println(result);
    }
}
