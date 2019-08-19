package com.ice.pattern.proxy.test;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @ClassName CglibTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/17 10:52 AM
 **/
public class CglibTest {

    public static void main(String[] args) {
        //新建enhancer对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new UserServiceInterceptor());
        //通过enhancer创建代理对象，代理对象是实际对象的子类对象（实际对象不需要有实现的接口）
        UserServiceImpl proxy = (UserServiceImpl) enhancer.create();
        //通过代理对象进行调用
        proxy.addUser();
    }
}
