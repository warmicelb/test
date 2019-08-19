package com.ice.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * @ClassName JdkThreadPoolTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/17 7:23 PM
 **/
public class JdkThreadPoolTest {
    public static void main(String[] args) {
//        manualCreate();
        autoCreate();
    }

    private static void autoCreate() {
//        Executors.newFixedThreadPool(2);
//        Executors.newCachedThreadPool();
//        Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.schedule(()-> {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("begin");
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("这里被中断了");
                }
                //这里trycatch后标志位会被重置
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println("task executing");
            }
            },0,TimeUnit.SECONDS);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("尝试打断");
        scheduledExecutorService.shutdownNow();
        System.out.println("isShutdown:"+scheduledExecutorService.isShutdown());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("isTerminated:"+scheduledExecutorService.isTerminated());
        System.out.println("isShutdown:"+scheduledExecutorService.isShutdown());

    }

    /**
     * 手动创建线程池
     */
    private static void manualCreate() {
        Runnable r = ()-> System.out.println("啊啊啊");
        ExecutorService pool = new ThreadPoolExecutor(2,4,3, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10),new ThreadPoolExecutor.AbortPolicy());
        pool.execute(r);
        pool.shutdown();
        System.out.println(pool.isShutdown());
    }
}
