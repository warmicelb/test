package com.ice.concurrent.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 测试java fork/join框架--异步执行任务
 * @ClassName ForkJoinAsyncTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/3 2:21 PM
 **/
public class ForkJoinAsyncTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileIteratorTask rootTask = new FileIteratorTask(new File("/Users/liubin/Downloads"));
        forkJoinPool.execute(rootTask);
        //这里测试，再main任务执行期间，FileIteratorTask异步在运行。
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main 执行任务次数:"+i);
        }
        rootTask.join();
        System.out.println("任务结束");
    }
}
class FileIteratorTask extends RecursiveAction{

    private File file;

    public FileIteratorTask(File file) {
        this.file = file;
    }

    @Override
    protected void compute() {
        File[] files = file.listFiles();
        List<RecursiveAction> taskList = new ArrayList<>();
        for (File file1 : files) {
            if(file1.isDirectory()){
                taskList.add(new FileIteratorTask(file1));
            }else{
                System.out.println(file1.getAbsolutePath()+file1.getName());
            }
        }
        if(!taskList.isEmpty()){
            for (RecursiveAction recursiveAction : invokeAll(taskList)) {
                    recursiveAction.join();
            }
        }
    }
}
