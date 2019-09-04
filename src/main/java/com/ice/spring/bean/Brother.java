package com.ice.spring.bean;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName Brother
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/3 4:07 PM
 **/
@ToString
public class Brother {

    /**
     * 可以使用@Value注解进行赋值(1字符串，2spel表达式，3配置文件)
     */
    @Value("${user.name}")
    private String name;
    @Value("female")
    private String sex;
    @Value("#{20-1}")
    private Integer age;
}
