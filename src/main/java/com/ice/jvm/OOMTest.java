package com.ice.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @ClassName OOMTest
 * @Description TODO 测试oom堆内存溢出
 * @Author liubin
 * @Date 2019/11/21 7:06 PM
 **/
public class OOMTest {


    /**
     * 这里设置参数 -Xms5m -Xmx5m -XX:+PrintGC
     * 报错信息：Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     */
    public static void test(){
        List<User> list = new LinkedList<>();
        for(int i=0;i<100000;i++){
            list.add(new User("aaa",1L));
        }
    }

    /**
     * 这里是分配是，剩余空间不足
     * 报错信息:Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    public static void test1(){
        String[] s = new String[1000000];
    }

    public static void main(String[] args) {
        test1();
    }
}
