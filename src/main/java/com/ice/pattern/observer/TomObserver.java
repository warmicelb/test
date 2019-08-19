package com.ice.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: ice
 * @create: 2019/3/28
 **/
public class TomObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("tom 知道了");
    }
}
