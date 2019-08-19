package com.ice.pattern.observer;

import java.util.Observable;

/**
 * 监控变化，通知所有订阅者
 * @author: ice
 * @create: 2019/3/28
 **/
public class AppleObservable extends Observable {
    String name;

    public AppleObservable(String name) {
        this.name = name;
    }

    public void perform(){
        this.setChanged();
        this.notifyObservers();
    }
}
