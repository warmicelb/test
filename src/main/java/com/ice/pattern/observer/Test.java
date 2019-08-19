package com.ice.pattern.observer;

import java.util.Observable;

/**
 * @author: ice
 * @create: 2019/3/28
 **/
public class Test {
    public static void main(String[] args) {
        Observable observable = new AppleObservable("apple厂商");
        observable.addObserver(new TomObserver());
        observable.addObserver(new JerryObserver());
        ((AppleObservable) observable).perform();
    }
}
