package com.ice.concurrent.safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Account
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/18 5:55 PM
 **/
public class Account {
    private Integer id;
    private String name;
    public Lock lock = new ReentrantLock();

    public Account(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
