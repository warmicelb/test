package com.ice.spring.bean.lifecycle;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 这里也可以通过JSR250提出的@PreDestroy和@PostConstruct注解进行初始化和销毁方法标记
 * @ClassName Daughter
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 4:40 PM
 **/
@Data
@ToString
public class Train{
    public Train() {
        System.out.println("这里创建了一个Train对象");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("这里调用Train的销毁方法");
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("这里调用Train的初始化方法");
    }
}
