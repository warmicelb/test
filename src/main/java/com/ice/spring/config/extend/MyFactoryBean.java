package com.ice.spring.config.extend;

import com.ice.spring.bean.Father;
import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义注册Bean的方式
 * @ClassName MyFactoryBean
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 7:40 PM
 **/
public class MyFactoryBean implements FactoryBean<Father> {
    @Override
    public Father getObject() throws Exception {
        return new Father();
    }

    @Override
    public Class<?> getObjectType() {
        return Father.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
