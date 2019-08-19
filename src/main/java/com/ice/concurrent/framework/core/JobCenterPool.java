package com.ice.concurrent.framework.core;

import com.alibaba.fastjson.JSON;
import com.ice.concurrent.framework.ITask;
import com.ice.concurrent.framework.JobInfo;
import com.ice.concurrent.framework.JobResult;
import com.ice.concurrent.framework.JobResultType;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 任务执行中心
 * @ClassName JobCenterPool
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/31 2:35 PM
 **/
public class JobCenterPool {

    /**
     * 任务并发线程数
     */
    private static int threadSize = Runtime.getRuntime().availableProcessors();
    /**
     * 任务队列，这里用有界队列
     */
    private static BlockingQueue taskQueue = new ArrayBlockingQueue(5000);
    /**
     * 线程池，用于执行任务
     */
    private ExecutorService executorService = new ThreadPoolExecutor(0,threadSize,60, TimeUnit.SECONDS,taskQueue);

    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

    private JobCenterPool() {
    }

    public static JobCenterPool getInstance(){
        return JobCenterPoolHolder.jobCenterPool;
    }
    private static class JobCenterPoolHolder{
        public static JobCenterPool jobCenterPool = new JobCenterPool();
    }

    private static class TaskWork<T> implements Runnable{
        private JobInfo jobInfo;
        private T data;

        public TaskWork(JobInfo jobInfo, T data) {
            this.jobInfo = jobInfo;
            this.data = data;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始执行任务: jobName+"+jobInfo.getJobName()+",次数:"+data);
            JobResult result = null;
            try {
                result = jobInfo.getiTask().execute(data);
            }catch (Exception e){
                e.printStackTrace();
                result = new JobResult(JobResultType.ERROR,null,"任务执行异常");
            }finally {
                if(result == null){
                    result = new JobResult(JobResultType.FAIL,null,"任务执行结果为空");
                }
            }

            jobInfo.completeTask(result);
        }
    }

    public void registerJob(String jobName, int jobLength, ITask<?,?> iTask, long expireTime){
        JobInfo<?> jobInfo = new JobInfo<>(jobName, jobLength, iTask, expireTime);
        if(jobInfoMap.putIfAbsent(jobName,jobInfo) != null){
            throw new IllegalArgumentException("已存在相同的jobName："+jobName);
        }
        System.out.println("注册job成功："+ JSON.toJSONString(jobInfo));
    }

    public <T> void putTask(String jobName,T data){
        JobInfo jobInfo = getJobInfo(jobName);
        executorService.execute(new TaskWork(jobInfo,data));
    }

    public JobInfo getJobInfo(String jobName){
        JobInfo jobInfo = jobInfoMap.get(jobName);
        if(jobInfo == null){
            throw new IllegalArgumentException("不存在的jobName，请先注册job:"+jobName);
        }
        return jobInfo;
    }

    public static Map<String, JobInfo<?>> getJobInfoMap() {
        return jobInfoMap;
    }
}
