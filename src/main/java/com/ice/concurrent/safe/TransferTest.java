package com.ice.concurrent.safe;

import java.util.Random;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 这里测试死锁的两种解决的方案
 * @ClassName TransferTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/18 5:56 PM
 **/
public class TransferTest {
    public static void main(String[] args) {
        Account spp = new Account(1, "spp");
        Account myy = new Account(2, "myy");
        new Thread(new Transfer2(spp,myy)).start();
        new Thread(new Transfer2(myy,spp)).start();

    }
    //模拟死锁的情况
    static class Transfer implements Runnable{

        private Account from;
        private Account to;

        public Transfer(Account from, Account to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            synchronized (from){
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to){
                    System.out.println("完成转账");
                }
            }
        }
    }

    /**
     * 死锁解决方案一（竞争资源进行排序，保证锁的获取顺序有序，从而避免死锁）
     */
    static class Transfer1 implements Runnable{

        private static Object object = new Object();
        private Account from;
        private Account to;

        public Transfer1(Account from, Account to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            //这里如果保证账户id唯一，也可以以账户id直接进行比较
            int fromHash = System.identityHashCode(from);
            int toHash = System.identityHashCode(to);
            if(fromHash<toHash){
                synchronized (from){
                    synchronized (to){
                        System.out.println(Thread.currentThread().getName()+"完成转账");
                    }
                }
            }else if(fromHash>toHash){
                synchronized (to){
                    synchronized (from){
                        System.out.println(Thread.currentThread().getName()+"完成转账");
                    }
                }
            }else{
                //hash碰撞的情况下（可能千万分之一的概率)
                synchronized (object){
                    synchronized (to){
                        synchronized (from){
                            System.out.println(Thread.currentThread().getName()+"完成转账");
                        }
                    }
                }
            }
        }
    }

    /**
     * 活锁的方式（尝试拿到锁，拿到后获取另一个锁，若不成功，则释放锁，让其他线程拿）：
     */
    static class Transfer2 implements Runnable{

        private Account from;
        private Account to;

        public Transfer2(Account from, Account to) {
            this.from = from;
            this.to = to;
        }
        @Override
        public void run() {
            while (true) {
                if (from.lock.tryLock()) {
                    System.out.println(from.getName()+"from locked");
                    try {
                        if (to.lock.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName()+"完成转账");
                                break;
                            } finally {
                                to.lock.unlock();
                            }
                        }
                    } finally {
                        //拿到锁后一定要释放
                        from.lock.unlock();
                    }
                }
                //这里如果尝试获取锁失败，可能会与其他线程获取锁的步调一致了（可能会导致两个线程一直获取对方线程持有的锁失败，一直重试），所以这里可以随机进行休眠一小段时间，来调整
                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
