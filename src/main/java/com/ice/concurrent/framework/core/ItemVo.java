package com.ice.concurrent.framework.core;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列实现对象封装
 * @ClassName ItemVo
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/31 5:29 PM
 **/
public class ItemVo<T> implements Delayed {

    private long expireTime;
    private T data;

    public ItemVo(long expireTime, T data) {
        this.expireTime = TimeUnit.MILLISECONDS.convert(expireTime,TimeUnit.NANOSECONDS)+System.nanoTime();
        this.data = data;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expireTime-System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS));
    }
}
