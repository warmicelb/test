package com.ice.concurrent.base.connection;

import java.sql.Connection;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用semaphore工具类实现数据库连接池的连接获取释放操作
 * @ClassName PoolTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/26 7:18 PM
 **/
public class SemaphorePoolTest {
    public static final MyConnectionSemaphorePool POOL = new MyConnectionSemaphorePool(5);
    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<20;i++){
            new Thread(new DbWorker()).start();
        }

    }

    static class DbWorker implements Runnable{

        @Override
        public void run() {
            long workTime = new Random().nextInt(2000);
            System.out.println(Thread.currentThread().getName()+"开始尝试获取连接");
            Connection connection = POOL.fetchConnectionSemaphore();
            System.out.println(Thread.currentThread().getName()+"获取连接成功，开始执行任务");
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行任务完成");
            POOL.releaseConnectionSemaphore(connection);
        }
    }

}
