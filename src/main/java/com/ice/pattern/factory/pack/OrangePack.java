package com.ice.pattern.factory.pack;

import com.ice.pattern.factory.Fruit;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class OrangePack implements FruitPack {
    @Override
    public void pack(Fruit fruit) {
        System.out.println("pack orange");
    }
}
