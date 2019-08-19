package com.ice.concurrent.base;

import java.util.Random;

/**
 * @ClassName VolatileTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/5 10:36 AM
 **/
public class VolatileTest {
    //这里只能保持读取到的volatile都是最新的，但是操作时并不能做到线程安全(所以volatile一般只适合单个线程写，其他线程读取)。
    public static volatile int num =10;

    /**
     * num++ 操作执行指令
     * Code:
     *       0: getstatic     #14                 // Field race:I
     *       3: iconst_1
     *       4: iadd
     *       5: putstatic     #14                 // Field race:I
     *       8: return
     * 这里由于是多个指令的执行，任意时刻都有可能切换到其他线程，所以num++并非线程安全的操作
     * @param args
     */
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Worker().start();
        }
        new SumWorker().start();
    }
    static class Worker extends Thread{
        @Override
        public void run() {
            try {

                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":当前数量num"+num);
        }
    }
    static class SumWorker extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"执行扣减操作");
            for(int i=0;i<10;i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num--;
            }
        }
    }
}

