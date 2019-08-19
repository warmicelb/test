package com.ice.pattern.factory;

import com.ice.pattern.factory.pack.FruitPack;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public interface AbstractFactory {

    Fruit getFruit();
    FruitPack getFruitPack();
}
