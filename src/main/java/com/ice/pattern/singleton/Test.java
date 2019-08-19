package com.ice.pattern.singleton;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class Test {
    public static void main(String[] args) {
        //饿汉式
        AccountBook instance = HungrySingleton.getInstance();
        System.out.println(instance.addCustomer());
        AccountBook instance1 = HungrySingleton.getInstance();
        System.out.println(instance1.addCustomer());
        //懒汉式
        AccountBook instance3 = LazySingleton.getInstance();
        System.out.println(instance3.addCustomer());
        AccountBook instance4 = LazySingleton.getInstance();
        System.out.println(instance4.addCustomer());

    }
}
