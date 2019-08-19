package com.ice.pattern.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class AccountBook {
    //总消费人数
    private AtomicInteger count = new AtomicInteger(0);

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
    public int addCustomer(){
        return count.addAndGet(1);
    }
}
