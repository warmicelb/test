package com.ice.spring.config.extend;

import com.ice.spring.bean.Mother;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 可以通过ImportBeanDefinitionRegistrar接口自定义规则注册bean到容器（手动添加bean）
 * @ClassName MyImportBeanDefinitionRegistrar
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 7:29 PM
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *  把所有需要的bean加入到容器中
     * @param annotationMetadata 当前类的注解信息
     * @param beanDefinitionRegistry BeanDefinition注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        boolean hasSon = beanDefinitionRegistry.containsBeanDefinition("com.ice.spring.bean.Son");
        //这里如果有son，就往容器里添加类mother的bean实例
        if(hasSon){
            //这里通过bean的包装类RootBeanDefinition进行对实例类的包装，并进行注册
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Mother.class);
            beanDefinitionRegistry.registerBeanDefinition("mother",rootBeanDefinition);
        }
    }
}
