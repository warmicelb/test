package com.ice.concurrent.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName MyThreadPool
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/17 2:45 PM
 **/
public class MyThreadPool {
    private int worker_num = 5;
    private BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<>();
    private Worker[] threads;

    public MyThreadPool(int worker_num) {
        if(worker_num>0) {
            this.worker_num = worker_num;
            threads = new Worker[worker_num];
            for(int i=0;i<worker_num;i++){
                threads[i] = new Worker();
                threads[i].start();
            }
        }
    }

    public void execute(Runnable runnable){
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy(){
        if(threads!=null){
            for(int i=0;i<threads.length;i++){
                threads[i].terminate();
                threads[i] = null;//help gc
            }
            taskQueue.clear();
        }
    }


    private class Worker extends Thread{

        @Override
        public void run() {
            Runnable take;
            try {
                while(!isInterrupted()) {
                    take = taskQueue.take();
                    take.run();
                    take = null;//help gc
                }
            } catch (Exception e) {
            }
        }

        /**
         * 结束线程（协作式）
         */
        public void terminate(){
            interrupt();
        }
    }
}
