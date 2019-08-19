package com.ice.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ice
 * @create: 2019/2/28
 **/
public class TestThreadPool {

    public static void main(String[] args) {
         Integer cpus = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService1 = Executors.newFixedThreadPool(cpus);
        for(int i=0;i<100;i++) {
            executorService1.submit(() -> {
                System.out.println("1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService1.shutdown();
        while (executorService1.isTerminated()){
            System.out.println("finish");
        }
    }
}
