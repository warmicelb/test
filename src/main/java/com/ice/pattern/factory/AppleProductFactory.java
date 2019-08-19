package com.ice.pattern.factory;

import com.ice.pattern.factory.pack.ApplePack;
import com.ice.pattern.factory.pack.FruitPack;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class AppleProductFactory implements AbstractFactory {
    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public FruitPack getFruitPack() {
        return new ApplePack();
    }
}
