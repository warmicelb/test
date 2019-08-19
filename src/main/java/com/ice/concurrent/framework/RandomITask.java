package com.ice.concurrent.framework;

import java.util.Random;

/**
 * @ClassName RandomITask
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/1 10:08 AM
 **/
public class RandomITask implements ITask<Integer,Integer>{

    @Override
    public JobResult<Integer> execute(Integer integer) {
        int mills = new Random().nextInt(1000);
        try {
            Thread.sleep(mills+integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (mills%3){
            case 0:
                return new JobResult(JobResultType.SUCCESS,mills+integer,"成功执行任务"+integer+",执行时间"+mills);
            case 1:
                return new JobResult(JobResultType.FAIL,mills+integer,"执行任务结果出错"+integer+",执行时间"+mills);
                default:
                    throw new RuntimeException("发生了错误");
        }
    }
}
