package com.ice.pattern.factory;

import com.ice.pattern.factory.pack.FruitPack;
import com.ice.pattern.factory.pack.OrangePack;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class OrangeProductFactory implements AbstractFactory {
    @Override
    public Fruit getFruit() {
        return new Orange();
    }

    @Override
    public FruitPack getFruitPack() {
        return new OrangePack();
    }
}
