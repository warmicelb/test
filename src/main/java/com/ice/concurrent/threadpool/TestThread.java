package com.ice.concurrent.threadpool;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName TestThread
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/13 4:18 PM
 **/
public class TestThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testThread();
//        manageThreads();
        testInterrupt();
    }

    /**
     * 测试interrupted方式重置线程状态
     * Thread.interrupt()方法不会中断一个正在运行的线程。这一方法实际上完成的是，在线程受到阻塞时抛出一个中断信号，这样线程就得以退出阻塞的状态。更确切的说，如果线程被Object.wait, Thread.join和Thread.sleep三种方法之一阻塞，那么，它将接收到一个中断异常（InterruptedException），从而提早地终结被阻塞状态。
     */
    public static void testInterrupted(){
        System.out.println(Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println("end");
    }

    /**
     * 停用一个线程时：推荐使用interrupted方法
     * 已过时方法：stop（不能保证资源被释放），suspend（挂起，不会释放资源）,resume
     * 推荐使用interrupted方法来中断一个线程:调用后会将线程的中断标志位改为true。
     * interrupt:中断一个线程,interrupted(静态方法，总是会取当前线程):判断线程是否中断，并清空中断标志,isInterrupted（非静态方法，判断线程对象）:判断线程对象的中断标志
     * ps:java中多个线程之间是协助式运行的，调用interrupt之后，只是将标志位置为了中断状态，但不能确定一定就会马上执行。建议友好的方式，再线程执行任务时，加上判断当前标志位。
     */
    private static void testInterrupt() {
        Thread runnable = new Thread(new MyRunnable());
        runnable.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.interrupt();
        System.out.println("主线程查询runnable线程状态isInterrupted:"+runnable.getName()+runnable.isInterrupted());
        System.out.println("已设置中断位");
    }

    /**
     * 各种方式实现多线程
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testThread() throws ExecutionException, InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        Thread runnable = new Thread(new MyRunnable());
        runnable.start();
        //需现将callable接口转成futuretask对象，再开启线程。
        FutureTask futureTask = new FutureTask(new MyCallable());
        Thread callable = new Thread(futureTask);
        callable.start();
        //这里futureTask的返回是阻塞的
        System.out.println(futureTask.get());
    }
    /**
     * 线程管理测试
     */
    public static void manageThreads(){
        /**
         * 虚拟机管理线程的对象
         */
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadName());
        }
    }
}

/**
 * 方式1：继承Thread类
 */
class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start");
        try {
            for(int i=0;i<10;i++) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" is running");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName()+" is interrupted");
        }
    }
}

/**
 * 实现runnable接口（实现接口，规避了java单继承的问题）
 */
class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start");
        try {
            for(int i=0;i<10;i++) {
                System.out.println(Thread.currentThread().getName()+" is running");
                System.out.println(Thread.currentThread().getName()+" is interrupted："+Thread.currentThread().isInterrupted());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
//            Thread.interrupted();
            System.out.println(Thread.currentThread().getName()+" is interrupted："+Thread.currentThread().isInterrupted());
            //找到原因后，这里标志位重置后，再调用interrupted
            Thread.currentThread().interrupt();
//            System.out.println(Thread.currentThread().getName()+" is interrupted："+Thread.interrupted());
//            System.out.println(Thread.currentThread().getName()+" is interrupted："+Thread.interrupted());
//            System.out.println(Thread.currentThread().getName()+" is interrupted："+Thread.currentThread().isInterrupted());
        }
        //这里测试时，会继续执行
        //原因：如果线程正在执行wait,sleep,join方法，你调用interrupt()方法，会抛出InterruptedException异常。而一个抛出了异常的线程的状态马上就会被置为非中断状态，如果catch语句没有处理异常，则下一次循环中isInterrupted()为false，线程会继续执行，可能你N次抛出异常，也无法让线程停止。
        for(int i=0;i<10;i++) {
//            System.out.println(Thread.currentThread().getName()+" is interrupted："+Thread.interrupted());
            System.out.println(Thread.currentThread().getName()+" is interrupted："+Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+" is go on running"+i);
        }
    }
}

/**
 * 实现callable接口（可以返回结果）
 */
class MyCallable implements Callable{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" start");
        try {
            for(int i=0;i<10;i++) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" is running");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName()+" is interrupted");
        }
        return "success";
    }
}
/**
 * 总结：interrupt方法只是给线程的状态标志位改为true，而并不会直接停止线程，应该在程序中通过该标志位以及其他标志判定退出。当调用interrupt方法后，最直接的作用是停止了线程中的sleep等阻塞的方法，会抛出InterruptedException异常。抛出了异常后线程的状态马上就会被置为非中断状态。
 */
