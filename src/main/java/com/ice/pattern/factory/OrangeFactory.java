package com.ice.pattern.factory;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class OrangeFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Orange();
    }
}
