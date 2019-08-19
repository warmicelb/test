package com.ice.concurrent.base;

/**
 * 饿汉式，线程安全
 * @ClassName Singleton
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/19 11:52 AM
 **/
public class Singleton {
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
//懒汉式，线程安全(延迟加载，当只有真正使用时，才会调用到SingletonI类的初始化，实例化对象并返回单例对象)
class Singleton1{
    public static Singleton1 getInstance() {
        return SingletonI.instance;
    }
    private static class SingletonI{
        private static Singleton1 instance = new Singleton1();
    }
}
//这里也可以延迟对象部分属性的初始化
class Singleton2{
    private int id;
    private SingleField field;

    private Singleton2() {
    }

    private  static Singleton2 singleton2 = new Singleton2();
    public static Singleton2 getInstance() {
        return singleton2;
    }
    //当使用时，才去进行属性的初始化，适用于部分属性比较复杂，比较大的情形
    public SingleField getField(){
        return SingletonF.instance;
    }

    private static class SingletonF{
        private static SingleField instance = new SingleField();
    }
}
class SingleField{

}
