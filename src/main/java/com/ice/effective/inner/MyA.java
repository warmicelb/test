package com.ice.effective.inner;

/**
 * 内部类
 * @author: ice
 * @create: 2019/4/2
 **/
public class MyA {
    private Integer a =1;
    private static Integer b = 1;

    private void test(){
        System.out.println(a);
        System.out.println(MyAA.c);
        System.out.println(MyAAA.d);

    }
    public static class MyAA{
        private static Integer c =1;
        private void test(){
            System.out.println(b);
            System.out.println(MyAAA.d);
        }
    }
    private static class MyAAA{
        private static Integer d =1;
        private void test(){

        }
    }
    class MyB{
        //非静态内部类不能有静态域（静态方法）
//        private static Integer e =1;
        public void test(){
            //局部类，只有方法内部用到
            class C{

            }
            System.out.println(b);
        }
    }
    private class MyBB{
        private Integer a = 2;
        private void test(){
            //可以访问调用外围类的实例方法
            MyA.this.test();
            System.out.println(a);
            System.out.println(MyA.this.a);
            System.out.println(b);
        }
    }
}
