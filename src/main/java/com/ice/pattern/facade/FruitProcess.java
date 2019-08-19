package com.ice.pattern.facade;

import com.ice.pattern.decorate.ApplePackDecorator;
import com.ice.pattern.decorate.StampAppleDecorator;
import com.ice.pattern.decorate.UrgeApplePackDecorator;
import com.ice.pattern.factory.Apple;
import com.ice.pattern.factory.AppleFactory;
import com.ice.pattern.factory.Fruit;
import com.ice.pattern.factory.pack.ApplePack;
import com.ice.pattern.factory.pack.ApplePackFactory;
import com.ice.pattern.factory.pack.FruitPack;

/**
 * 将多个操作封闭起来，对外界只提供一个入口供访问
 * @author: ice
 * @create: 2019/3/26
 **/
public class FruitProcess {

    public void process(){
        Fruit fruit = new AppleFactory().getFruit();
        FruitPack fruitPack = new ApplePackFactory().getFruitPack();
        fruitPack = new UrgeApplePackDecorator(new StampAppleDecorator((ApplePack) fruitPack));
        fruitPack.pack(fruit);
    }
}
