package com.ice.pattern.factory.pack;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class OrangePackFactory implements FruitPackFactory {
    @Override
    public FruitPack getFruitPack() {
        return new OrangePack();
    }
}
