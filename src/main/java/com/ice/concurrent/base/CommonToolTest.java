package com.ice.concurrent.base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @ClassName CommonTollTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/3 3:14 PM
 **/
public class CommonToolTest {
    public static void main(String[] args) {
//        testCountDownLatch();
        testCyclicBarrier();
    }

    /**
     * 测试countDownLatch
     * 线程调用await方法后（多个线程可以同时调用，同时也会被唤醒），进入等待，当扣减点扣除到达0时，被唤醒（扣除点和线程数量无关）。
     */
    public static void testCountDownLatch(){
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("这里可以再进行扣减");
                countDownLatch.countDown();
            }
        }).start();
        try {
            System.out.println("进入等待");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    /**
     * CyclicBarrier是多个线程互等，等大家都达到条件时，再同时放行，此时多个线程可以继续向下执行。CyclicBarrier中也可以指定另外的任务再放行后执行。
     */
    public static void testCyclicBarrier(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("放行后，执行另一项任务");
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread().getName()+"开始进入等待");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"这里继续往下执行");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread().getName()+"开始进入等待");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"这里继续往下执行");
            }
        }).start();
    }

    /**
     * 信号量，一般用于访问控制，限流
     */
    public static void testSemaPhore(){
        Semaphore semaphore = new Semaphore(5);
    }
}
