package com.ice.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 订阅者，订阅变化
 * @author: ice
 * @create: 2019/3/28
 **/
public class JerryObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("jerry 知道了");
    }
}
