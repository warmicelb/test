package com.ice.concurrent.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName HandleResult
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/24 2:26 PM
 **/
@Data
@AllArgsConstructor
public class JobResult<T> {
    /**
     * 执行结果
     */
    final private JobResultType handleResultType;
    /**
     * 业务结果数据
     */
    final private T data;
    /**
     * 原因
     */
    final private String reason;

    public JobResult(T data){
        this.data = data;
        handleResultType = JobResultType.SUCCESS;
        reason = "SUCCESS";
    }
}
