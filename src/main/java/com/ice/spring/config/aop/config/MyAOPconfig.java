package com.ice.spring.config.aop.config;

import com.ice.spring.config.aop.aspect.LogAspect;
import com.ice.spring.config.aop.aspect.SecondAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName MyAOPconfig
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/4 11:18 AM
 **/
@Configuration
/**
 * 开启切面支持
 */
@EnableAspectJAutoProxy
public class MyAOPconfig {

    @Bean
    public MyCalculator myCalculator(){
        return new MyCalculator();
    }

    /**
     * 这里定义另外一个切面对同一个方法进行拦截，测试作用的顺序
     * 结果：这里根据bean注册的顺序，顺序执行方法执行前的通知，方法执行后的通知倒序执行
     * @return
     */
    @Bean
    public SecondAspect secondAspect(){
        return new SecondAspect();
    }
    /**
     * 这里不通过扫描，直接通过@bean
     * 方式注入，需要在切面类中加上@Aspect注解，否则不生效
     * @return
     */
    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }

}
