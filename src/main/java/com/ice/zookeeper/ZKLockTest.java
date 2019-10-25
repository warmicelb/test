package com.ice.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName ZKLockTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/10/24 10:54 AM
 **/
public class ZKLockTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(() -> {
                System.out.println("thread " + Thread.currentThread().getName() + "begin to get lock");
                Lock zkLock = new ZKOrderedLock("/zkLock","/zkOrderLock");
                zkLock.lock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zkLock.unlock();
                System.out.println("thread " + Thread.currentThread().getName() + "releaseLock");

            }).start();

        }
    }

    /**
     * zk分布式锁实现方式一:创建临时节点，其他线程监听临时节点的删除(羊群效应：这里一旦节点被删除，其它所有的节点都会收到通知，发生争抢，无序)
     */
    public static class ZKLock implements Lock {

        private ZkClient client = new ZkClient("127.0.0.1:2181", 5000);
        private String key = null;

        public ZKLock(String key) {
            this.key = key;
        }

        @Override
        public void lock() {
            while (!tryLock()) {
                try {
                    waitLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "getLock");
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            try {
                client.createEphemeral(key, "");
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            client.delete(key);
            client.close();
            System.out.println(Thread.currentThread().getName() + "releaseLock");

        }

        @Override
        public Condition newCondition() {
            return null;
        }

        //监听锁的释放
        public void waitLock() throws InterruptedException {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            client.subscribeDataChanges(key, new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {

                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        }
    }

    /**
     * 顺序锁：先插入一个临时节点，只需要监听前一个节点的删除
     */
    public static class ZKOrderedLock implements Lock {

        private ZkClient client = new ZkClient("127.0.0.1:2181", 5000);
        private String path = null;
        private String lockKey = null;

        public ZKOrderedLock(String path,String key) {
            this.path = path;
            if(!client.exists(path)){
                client.createPersistent(path);
            }
            lockKey = client.createEphemeralSequential(path+key, "");
        }

        @Override
        public void lock() {
            List<String> children = client.getChildren(path);
            children.sort(String::compareTo);
            String child = lockKey.substring(lockKey.lastIndexOf("/")+1);
            if (!children.get(0).equals(child)) {
                try {
                    waitLock(path+"/"+children.get(Collections.binarySearch(children,child) - 1));
                    lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "getLock");
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
           return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            client.delete(lockKey);
            client.close();
            System.out.println(Thread.currentThread().getName() + "releaseLock");
        }

        @Override
        public Condition newCondition() {
            return null;
        }

        //监听锁的释放
        public void waitLock(String before) throws InterruptedException {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            client.subscribeDataChanges(before, new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {

                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        }
    }
}
