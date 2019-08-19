package com.ice.pattern.factory;

import com.ice.pattern.factory.builder.Builder;
import com.ice.pattern.factory.builder.Director;
import com.ice.pattern.factory.builder.LenovoBuilder;
import com.ice.pattern.factory.pack.FruitPack;

import java.util.zip.GZIPOutputStream;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class Test {
    public static void main(String[] args) {
        //简单工厂模式（将各个对象的创建划分为独立的静态方法获取）
        Fruit fruit = SimpleFactory.getFruit(SimpleFactory.FruitType.APPLE);
        Orange orange = SimpleFactory.getOrange();
        fruit.eat();
        //工厂方法模式，将静态工厂打散(符合单一职责)
        FruitFactory fruitFactory = new AppleFactory();
        Fruit apple1 = fruitFactory.getFruit();
        apple1.eat();
        fruitFactory = new OrangeFactory();
        Fruit orange1 = fruitFactory.getFruit();
        orange1.eat();
        //抽象工厂模式（当生产产品有多个系列的产品需要区分，需要按照系列生产。则考虑用抽象工厂）
        AbstractFactory factory = new AppleProductFactory();
        Fruit fruit1 = factory.getFruit();
        FruitPack fruitPack = factory.getFruitPack();
        fruitPack.pack(fruit1);
        Builder builder = new LenovoBuilder();
        Object result = new Director(builder).construct();
        System.out.println(result);
    }
}
