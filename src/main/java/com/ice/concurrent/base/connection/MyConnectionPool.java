package com.ice.concurrent.base.connection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @ClassName MyConnectionPool
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/26 5:47 PM
 **/
public class MyConnectionPool {

    private static LinkedList<Connection> CONNECTION_POOL = new LinkedList<>();

    public MyConnectionPool(int size) {
        for(int i=0;i<size;i++){
            CONNECTION_POOL.add(MyConnection.fetchConnection());
        }
    }

    /**
     * 获取连接
     * @param millis 超时等待毫秒数
     * @return
     */
    public Connection fetchConnection(long millis){
        synchronized (CONNECTION_POOL) {
            if (millis <= 0) {
                while (CONNECTION_POOL.isEmpty()) {
                    try {
                        CONNECTION_POOL.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return CONNECTION_POOL.removeFirst();
            } else {
                long expire = System.currentTimeMillis() + millis;
                long remain = millis;
                while (CONNECTION_POOL.isEmpty()&&remain>0) {
                    try {
                        CONNECTION_POOL.wait(remain);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //重新计算剩余等待时间
                    remain = expire - System.currentTimeMillis();
                }
                if(CONNECTION_POOL.isEmpty()){
                    System.out.println(Thread.currentThread().getName()+"获取连接失败");
                    return null;
                }
                return CONNECTION_POOL.removeFirst();
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection!=null){
            synchronized (CONNECTION_POOL){
                CONNECTION_POOL.addLast(connection);
                CONNECTION_POOL.notifyAll();
            }
        }
    }
}
