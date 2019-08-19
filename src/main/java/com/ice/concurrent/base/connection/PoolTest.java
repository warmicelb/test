package com.ice.concurrent.base.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PoolTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/26 7:18 PM
 **/
public class PoolTest {
    static CountDownLatch end;
    public static final MyConnectionPool POOL = new MyConnectionPool(10);
    public static AtomicInteger got = new AtomicInteger(0);
    public static AtomicInteger notGot = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        int threads = 20;
//        CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程执行完后再执行。
        end  = new CountDownLatch(threads);
        for(int i=0;i<threads;i++){
            new Thread(new Worker(20,got,notGot)).start();
        }
        //主线程等待
        end.await();
        System.out.println("获取连接成功次数:"+got.get());
        System.out.println("获取连接失败次数:"+notGot.get());
    }

}
class Worker implements Runnable {

    private Integer count;
    private AtomicInteger got;
    private AtomicInteger notGot;

    public Worker(Integer count, AtomicInteger got, AtomicInteger notGot) {
        this.count = count;
        this.got = got;
        this.notGot = notGot;
    }

    @Override
    public void run() {
        while (count > 0) {
            Connection connection = PoolTest.POOL.fetchConnection(1000);
            if(connection!=null){
                try {
                    connection.createStatement();
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    PoolTest.POOL.releaseConnection(connection);
                    got.incrementAndGet();
                }
            }else{
                notGot.incrementAndGet();
                System.out.println(Thread.currentThread().getName()+"获取连接超时");
            }
            System.out.println("剩余连接次数:"+--count);
        }
        PoolTest.end.countDown();
    }
}
