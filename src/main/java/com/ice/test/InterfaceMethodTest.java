package com.ice.test;

/**
 * 测试接口中方法
 * @ClassName InterfaceMethodTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 6:02 PM
 **/
public class InterfaceMethodTest {
    public static void main(String[] args) {
        MyMethod myMethod = new MyMethod();
        myMethod.defaultMethod();
    }
}
class MyMethod implements CommonMethod1,CommonMethod{

    @Override
    public void nonStatic() {
    }

    /**
     * 这里编译器无法判断默认调用哪个defaultMethod方法，所以必须要手动覆盖方法
     */
    @Override
    public void defaultMethod() {

    }
}
interface CommonMethod{
    
    int i = 0;
    /**
     * 这里可以定义静态方法
     */
    static void print(){
        System.out.println("print");
    }
    void nonStatic();

    /**
     * 这里也可以定义方法的同时，定义默认的实现
     */
    default void defaultMethod(){
        System.out.println("default Method");
    }
}
interface CommonMethod1{

    default void defaultMethod() {
        System.out.println("subclass default Method");
    }
}