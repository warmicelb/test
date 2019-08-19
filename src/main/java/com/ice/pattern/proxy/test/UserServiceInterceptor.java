package com.ice.pattern.proxy.test;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 进行增强
 * @ClassName UserServiceIntercepter
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/17 10:52 AM
 **/
public class UserServiceInterceptor implements MethodInterceptor {


    /**
     * 这里是对方法进行拦截并进行增强操作
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(objects!=null&&objects.length>0&&objects[0] instanceof Object){
            System.out.println("这里调用有参数的方法前进行了一下增强");
        }
        System.out.println("这里调用无参数的方法前进行了一下增强");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("这里也可以再方法调用后进行增强");
        return result;
    }

}
