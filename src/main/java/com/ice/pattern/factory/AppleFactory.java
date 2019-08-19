package com.ice.pattern.factory;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class AppleFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
