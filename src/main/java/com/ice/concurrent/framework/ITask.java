package com.ice.concurrent.framework;

/**
 * 任务提交接口
 * @ClassName ITaskExecutor
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/24 2:14 PM
 **/
public interface ITask<T,R> {
    JobResult<R> execute(T t);
}
