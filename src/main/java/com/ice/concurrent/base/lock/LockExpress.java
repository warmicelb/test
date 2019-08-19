package com.ice.concurrent.base.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  wait，notify/notifyAll机制，完成线程间的协作
 * @ClassName Express
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/26 4:11 PM
 **/
public class LockExpress {
    private static String district = "shanghai";
    private int distance;
    private String destination;
    private Lock lock = new ReentrantLock();
    //这里设置等待通知的两个条件
    private Condition kmCondition = lock.newCondition();
    private Condition disCondition = lock.newCondition();

    public LockExpress(int distance, String destination) {
        this.distance = distance;
        this.destination = destination;
    }

    /**
     * 更改配送距离/路线
     */
    public void changeDistance(){
        lock.lock();
        try{
            distance = distance+100;
            System.out.println(Thread.currentThread().getName()+" change distance:"+distance);
            //单个通知
            disCondition.signal();
        }finally{
            lock.unlock();
        }
    }

    /**
     * 更改配送地址
     */
    public void changeDestination(){
        lock.lock();
        try{
            destination = "北京";
            System.out.println(Thread.currentThread().getName()+" change destination:"+destination);
            kmCondition.signal();
        }finally{
            lock.unlock();
        }
    }
    //监听距离大于100的快递
    public void checkDistance(){
        lock.lock();
        try {
            while(distance<100){
                try {
                    System.out.println(Thread.currentThread().getName()+": wait the distance"+distance);
                    disCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+":the distance change:"+distance);

    }
    //监听北京的快递
    public void checkDestination(){
        lock.lock();
        try {
        while(!"北京".equals(destination)){
            try {
                System.out.println(Thread.currentThread().getName()+": wait the destination"+destination);
                kmCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }finally{
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+":the destination change:"+destination);
    }
}
