package com.ice.concurrent.base.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 通过Lock接口，AbstractQueuedSynchronizer 实现一个简单的同步锁
 * @ClassName SimpleLock
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/12 3:06 PM
 **/
public class SimpleLock implements Lock {

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.new ConditionObject();
    }

    /**
     * 独占锁的一个简单实现
     * 这里通过控制state值来表示获取锁的状态：1获取，2未获取
     */
    private static class Sync extends AbstractQueuedSynchronizer{
        protected Sync() {
            super();
        }

        @Override
        protected boolean tryAcquire(int arg) {
            //之类compareAndSetState是一个原子操作
                if(compareAndSetState(0,1)){
                    //这里要设置当前持有锁的线程
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==1){
                setExclusiveOwnerThread(null);
                setState(0);
            }
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }
    }
}
