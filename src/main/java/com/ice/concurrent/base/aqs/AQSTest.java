package com.ice.concurrent.base.aqs;


import java.util.concurrent.locks.Lock;

/**
 * @ClassName AQSTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/12 10:48 AM
 **/
public class AQSTest {
    public static void main(String[] args) {
        Lock lock = new SimpleLock();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock.lock();
                        try {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + "holding the lock");
                        } finally {
                            lock.unlock();
                        }
                }
            });
            thread.setDaemon(true);
            //设置守护线程必须要start之前
            thread.start();
        }
        for(int i=0;i<30;i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
        System.out.println("main end");
    }

}
