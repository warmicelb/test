package com.ice.concurrent.framework.core;

import com.ice.concurrent.framework.JobInfo;

import java.util.concurrent.DelayQueue;

/**
 * @ClassName JobExpireCheck
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/31 5:26 PM
 **/
public class JobExpireCheck {

    private static DelayQueue<ItemVo<String>> jobExpireQueue = new DelayQueue();


    static{
        Thread checkWork = new Thread(new CheckWork());
        //设置守护线程，不独立存活
        checkWork.setDaemon(true);
        checkWork.start();
    }
    private JobExpireCheck() {
    }

    private static class JobExpireCheckHolder{
        public static JobExpireCheck jobExpireCheck = new JobExpireCheck();
    }

    public static JobExpireCheck getInstance(){
        return JobExpireCheckHolder.jobExpireCheck;
    }

    public void putJobInfo(JobInfo jobInfo){
        jobExpireQueue.offer(new ItemVo<>(jobInfo.getExpireTime(),jobInfo.getJobName()));
        System.out.println("将job:"+jobInfo.getJobName()+"过期检查任务放入回收检查队列");
    }

    private static class CheckWork implements Runnable{

        @Override
        public void run() {
            System.out.println("job缓存清理守护线程启动");
            while(true){
                ItemVo<String> item = null;
                try {
                    item = jobExpireQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JobCenterPool.getJobInfoMap().remove(item.getData());
                    System.out.println("任务完成，缓存过期，已移除job："+item.getData());
            }
        }
    }
}
