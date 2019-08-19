package com.ice.concurrent.base;

/**
 * 测试join方法
 * @ClassName TestJoin
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/27 9:44 AM
 **/
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread jumpThread = Thread.currentThread();
        for(int i=0;i<20;i++){
            Thread thread = new Thread(new JumpWorker(jumpThread));
            thread.start();
            jumpThread = thread;
        }
        //主线程先休眠5秒
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+"terminated");
    }
}
class JumpWorker implements Runnable{

    private Thread thread;

    public JumpWorker(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            System.out.println("currentThread:"+Thread.currentThread().getName()+",now let"+thread.getName()+"join");
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"terminated");
    }
}
