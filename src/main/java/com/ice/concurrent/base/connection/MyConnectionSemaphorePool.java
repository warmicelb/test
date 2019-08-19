package com.ice.concurrent.base.connection;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @ClassName MyConnectionPool
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/26 5:47 PM
 **/
public class MyConnectionSemaphorePool {

    private  final LinkedList<Connection> CONNECTION_POOL = new LinkedList<>();
    private  final Semaphore enable;
    private  final Semaphore disable;

    public MyConnectionSemaphorePool(int size) {
        for(int i=0;i<size;i++){
            CONNECTION_POOL.add(MyConnection.fetchConnection());
        }
        enable = new Semaphore(size);
        disable = new Semaphore(0);
    }


    public Connection fetchConnectionSemaphore(){
        System.out.println(Thread.currentThread().getName()+"Semaphore获取前：当前等待链接的数量："+enable.getQueueLength()+"当前可用链接数："+enable.availablePermits());
        try {
            //这里能保证可以线程安全的获取一个链接，单不会保证后面程序的执行顺序和执行结果（enable.availablePermits()）
            //这里测试下面的两次打印时，可用的许可数都是不同的
            enable.acquire();
            System.out.println(Thread.currentThread().getName()+"当前可用链接数："+enable.availablePermits());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"Semaphore获取后：当前等待链接的数量："+enable.getQueueLength()+"当前可用链接数："+enable.availablePermits());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (CONNECTION_POOL){
                disable.release();
                return CONNECTION_POOL.removeFirst();
            }
    }

    public void releaseConnectionSemaphore(Connection connection){
        System.out.println(Thread.currentThread().getName()+"当前等待释放链接的数量："+enable.getQueueLength());

        synchronized (CONNECTION_POOL){
                CONNECTION_POOL.addLast(connection);
                try {
                    disable.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                enable.release();
            System.out.println(Thread.currentThread().getName()+"释放链接完毕");

            }
    }

}
