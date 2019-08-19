package com.ice.concurrent.framework;

import com.alibaba.fastjson.JSON;
import com.ice.concurrent.framework.core.JobExpireCheck;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 提交给框架执行的工作实例
 * @ClassName JobInfo
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/31 10:56 AM
 **/
public class JobInfo<R> {
    /**
     * 工作名称标识
     */
    private final String jobName;
    /**
     * 工作长度（用于进度标识）
     */
    private final int jobLength;
    /**
     * 任务处理器（业务处理逻辑封装）
     */
    private final ITask iTask;
    /**
     * 成功执行数
     */
    private AtomicInteger successCount;
    /**
     * 总任务数
     */
    private AtomicInteger iTaskCount;
    /**
     * 任务执行结果队列（拿从头拿，放从尾放）
     */
    private LinkedBlockingDeque<JobResult<R>> taskResultQueue;
    /**
     * 任务的过期时间
     */
    private long ExpireTime;

    public JobInfo(String jobName, int jobLength, ITask<?,?> iTask, long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.iTask = iTask;
        this.taskResultQueue = new LinkedBlockingDeque<>(jobLength);
        ExpireTime = expireTime;
        successCount = new AtomicInteger();
        iTaskCount = new AtomicInteger();
    }

    public ITask getiTask() {
        return iTask;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobLength() {
        return jobLength;
    }

    public long getExpireTime() {
        return ExpireTime;
    }

    /**
     * 获取执行成功任务次数
     * @return
     */
    public Integer getSuccessCount() {
        return successCount.get();
    }

    /**
     * 获取已执行任务次数
     * @return
     */
    public Integer getiTaskCount() {
        return iTaskCount.get();
    }

    /**
     * 返回结果，这里进行拷贝，保证线程安全（防止数据外部篡改）
     * @return
     */
    public List<JobResult<R>> getTaskResults(){
        List<JobResult<R>> returnResults = new LinkedList<>();
        JobResult<R> temp = null;
        while((temp = taskResultQueue.pollFirst())!=null){
            returnResults.add(temp);
        }
        return returnResults;
    }

    /**
     * 提交任务执行结果
     * @param jobResult
     */
    public void completeTask(JobResult<R> jobResult){
        System.out.println("完成任务，推送结果至结果队列并计数");
        if(jobResult.getHandleResultType()==JobResultType.SUCCESS){
             successCount.incrementAndGet();
        }
        iTaskCount.incrementAndGet();
        taskResultQueue.addLast(jobResult);
        if(iTaskCount.get()==jobLength){
            JobExpireCheck.getInstance().putJobInfo(this);
        }
    }

    public String getProgressDesc(){
        return new StringBuilder("已执行任务数:").append(getiTaskCount())
                .append(",成功执行数：").append(getSuccessCount())
                .append("期间执行成功结果（不包括上次查询出的结果）：").append(JSON.toJSONString(getTaskResults())).toString();
    }
}
