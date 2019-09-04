package com.ice.spring.bean.lifecycle;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 这里也可以通过实现接口InitializingBean来定义创建对象的初始化方法,通过实现DisposableBean接口来定义对象销毁方法
 * @ClassName Daughter
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 4:40 PM
 **/
@Data
@ToString
public class Subway implements InitializingBean, DisposableBean {
    public Subway() {
        System.out.println("这里创建了一个Subway对象");
    }


    @Override
    public void destroy(){
        System.out.println("这里调用Subway的销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("这里调用Subway的初始化方法");
    }
}
