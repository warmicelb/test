package com.ice.pattern.decorate;

import com.ice.pattern.factory.Fruit;
import com.ice.pattern.factory.pack.ApplePack;

/**
 * @author: ice
 * @create: 2019/3/20
 **/
public class StampAppleDecorator extends ApplePackDecorator {
    public StampAppleDecorator(ApplePack applePack) {
        this.applePack = applePack;
    }

    @Override
    public void pack(Fruit fruit) {
        System.out.println("stamp on it");
        applePack.pack(fruit);
    }
}
