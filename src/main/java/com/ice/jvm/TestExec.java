package com.ice.jvm;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1.javac TestExec.java 编译java文件，生成字节码文件TestExec.class
 * 2.javap -verbose TestExec.class 分析字节码文件
 * @ClassName TestExec
 * @Description TODO
 * @Author liubin
 * @Date 2019/12/9 5:35 PM
 **/
public class TestExec {

    /**
     * 测试方法
     */
    public void add(){
        int a = 100;
        int b = 200;
        int c = a+b;
        System.out.println(c);
    }
}
