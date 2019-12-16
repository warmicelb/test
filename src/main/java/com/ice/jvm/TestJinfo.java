package com.ice.jvm;

import java.lang.management.ManagementFactory;
import java.util.Map;

/**
 * @ClassName TestJinfo
 * @Description TODO
 * @Author liubin
 * @Date 2019/11/26 4:13 PM
 **/
public class TestJinfo {

    /**
     * 打印各个线程及线程的栈
     * @param args
     */
    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
            System.out.println(threadEntry.getKey().getName());
            for (StackTraceElement stackTraceElement : threadEntry.getValue()) {
                System.out.println(stackTraceElement);
            }
        }
        
    }
    public void test(){
        //虚拟机管理参数都可以拿到
//        ManagementFactory
    }
}
