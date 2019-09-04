package com.ice.spring.service;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @ClassName PersonService
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 3:09 PM
 **/
@Service
@Data
@Lazy
public class PersonService {
    private Integer age;

    public PersonService() {
        System.out.println("这列创建了一个PersonService实例");
    }

    public PersonService(Integer age) {
        this.age = age;
    }
}
