package com.ice.spring.controller;

import com.ice.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @ClassName PersonController
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 3:09 PM
 **/
@Controller
public class PersonController {

    /**
     * 这里会先创建PersonController实例，此时他的属性都还未复制，之后如果personService为空，则会实例化personService对象并注入。
     */
    public PersonController() {
        System.out.println("这里创建了一个personController实例");
        System.out.println("这里personSevice："+personService==null);
    }

    //之类会优先注入personService对象,如果要指定其他的，可以使用@Qualifier注解
    @Qualifier("personService")
    //作用于字段，也可以作用于set方法，构造函数，方法内参数位置
    @Autowired
    //@resource注解与autowired效果一样
//    区别：@resource不支持@Primary,并且不支持设置Requeried属性（指定是否必须注入非空对象）
//      所以推荐使用@Autowired
//    @Resource

    /**
     * 需要额外引入javax.inject包
     * 区别：支持@Primary注解，不支持@Autowired的required属性.
     */
//    @Inject
    PersonService personService;

    public void printPerson(){
        System.out.println("personService: age"+personService.getAge());
    }
}
