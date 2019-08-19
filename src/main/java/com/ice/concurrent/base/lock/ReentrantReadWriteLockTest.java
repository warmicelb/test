package com.ice.concurrent.base.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试synchronize和ReentrantReadWriteLock的读写情况
 * ReentrantReadWriteLock在多读，少写的情况下有很大提升。
 * @ClassName ReentrantReadWriteLockTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/11 3:56 PM
 **/
public class ReentrantReadWriteLockTest {

    public static int sum = 10;
    private static int writeThread = 3;
    private static int readWriteRate = 10;
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static Lock readLock = lock.readLock();
    public static Lock writeLock = lock.writeLock();

    public static void main(String[] args) {
        for(int i=0;i<writeThread;i++){
            new Thread(new WriteWork()).start();
            for(int j=0;j<readWriteRate;j++){
                new Thread(new ReadWork()).start();
            }
        }
    }

    public static synchronized int get(){
        readLock.lock();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
        return sum;
    }

    public static synchronized void substract(){
        writeLock.lock();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
        sum--;
    }

    //使用读写锁实现

    public static int getLock(){
        lock.readLock();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static synchronized void substractLock(){
        lock.writeLock();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sum--;
    }
    static class ReadWork implements Runnable{

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for(int i=0;i<10;i++) {
                getLock();
            }
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" read:总共用时："+(end-start));
        }
    }
    static class WriteWork implements Runnable{

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for(int i=0;i<10;i++) {
                substractLock();
            }
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" write:总共用时："+(end-start));
        }
    }
}
