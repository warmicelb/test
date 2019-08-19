package com.ice.concurrent.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName CompletionTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/18 3:05 PM
 **/
public class CompletionTest {
    public static Callable<Integer> callable = ()->{Integer s = new Random().nextInt(1000);Thread.sleep(s);return s;};

    public static void main(String[] args) {
        //返回原生的object的hashCode。
        System.identityHashCode(new Object());
        //cpu核心数
        int cpu = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newCachedThreadPool();
        //包装pool对象，通过completionService直接提交任务。获取任务执行结果时，则可通过completion按照完成的顺序来取得。
        CompletionService<Integer> completionService = new ExecutorCompletionService(pool);
        for(int i=0;i<10;i++){
            completionService.submit(callable);
        }

        try {
            for(int i=0;i<10;i++){
                Integer sleep = completionService.take().get();
                System.out.println(sleep);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
