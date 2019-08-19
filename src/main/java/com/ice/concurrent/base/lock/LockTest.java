package com.ice.concurrent.base.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试显示锁
 * Lock显示锁
 * synchronize内置锁
 * @ClassName LockTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/11 2:49 PM
 **/
public class LockTest {
    public static void main(String[] args) {
        //可重入锁
        Lock lock = new ReentrantLock();
        new ReentrantReadWriteLock();
        //静态工具类
        LockSupport.park();
    }
    public static void getLock(Lock lock ){
        lock.lock();
        //保证锁的释放
        try{

        }catch (Exception e){
            lock.unlock();
        }
    }

    //之类如果是递归，则这里就是使用了可重入锁，每次调用一次，内置的锁计数器就+1，当执行完一次后，-1.执行完毕后，锁计数器为0，锁释放。
    public synchronized static void testSync(int i){
        if(i>0) {
            testSync(--i);
        }
    }

}
