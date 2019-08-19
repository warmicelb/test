package com.ice.concurrent.framework;

import com.ice.concurrent.framework.core.JobCenterPool;

/**
 * 测试任务管理框架
 * @ClassName JobTaskTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/31 7:30 PM
 **/
public class JobTaskTest {
    public static void main(String[] args) {
        System.out.println("开始测试并发任务执行框架");
        JobCenterPool jobCenterPool = JobCenterPool.getInstance();
        jobCenterPool.registerJob("测试job1",5,new RandomITask(),5*1000);
        jobCenterPool.registerJob("测试job2",5,new RandomITask(),5*1000);
        for(int i=1;i<=10;i++){
            String jobName = i%2==0?"测试job1":"测试job2";
            jobCenterPool.putTask(jobName,i);
        }
        for(int i=0;i<20;i++){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("main sleep interrupted");
            }
            JobInfo job1 = jobCenterPool.getJobInfo("测试job1");
            if(job1!=null){
                System.out.println("测试job1"+job1.getProgressDesc());
            }else{
                System.out.println("job1 completed");
            }
            JobInfo job2 = jobCenterPool.getJobInfo("测试job1");
            if(job2!=null){
                System.out.println("测试job2"+job2.getProgressDesc());
            }else{
                System.out.println("job2 completed");
            }
        }
    }
}
