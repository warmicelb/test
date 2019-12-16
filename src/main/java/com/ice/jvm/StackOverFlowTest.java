package com.ice.jvm;

/**
 * 设置参数 -Xss256k
 * @ClassName StackOverFlowTest
 * @Description TODO 测试栈内存溢出
 * @Author liubin
 * @Date 2019/11/21 7:10 PM
 **/
public class StackOverFlowTest {

    public static void test(){
        System.out.println("fsafsadfs");
        //这里无限递归，是栈帧内存溢出
        test();
    }

    public static void main(String[] args) {
        test();
    }
}
