package com.ice.concurrent.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FutureTaskTest
 * @Description TODO 测试futureTask类的使用
 * @Author liubin
 * @Date 2019/7/5 11:48 AM
 **/
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return new Random().nextInt(100);});
        new Thread(futureTask).start();
        System.out.println("已开启线程："+System.currentTimeMillis());
        futureTask.get();
        System.out.println("已完成任务："+futureTask.get()+":"+System.currentTimeMillis());
    }
}
