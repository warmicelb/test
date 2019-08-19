package com.ice.concurrent.threadpool;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Random;
import java.util.concurrent.Executors;

/**
 * @ClassName MyThreadPoolTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/17 3:46 PM
 **/
public class MyThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool = new MyThreadPool(5);
        Runnable runnable = ()-> {
                System.out.println(Thread.currentThread().getName()+"executing");
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName()+"executed");
        };
        new Thread(runnable).start();
        for(int i=0;i<10;i++){
            pool.execute(runnable);
        }
        System.out.println("任务提交完毕");
        //这里保证所有任务执行完成后再destroy
        Thread.sleep(10000);
        pool.destroy();
        printRunningThread();
    }

    public static void printRunningThread(){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadName());
        }
    }
}
