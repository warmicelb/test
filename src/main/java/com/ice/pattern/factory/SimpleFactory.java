package com.ice.pattern.factory;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class SimpleFactory {

    enum FruitType{
        APPLE,ORANGE;
    }
    public static Fruit getFruit(FruitType fruitType){
        if(fruitType==FruitType.APPLE){
            return new Apple();
        }else if(fruitType==FruitType.ORANGE){
            return new Orange();
        }
        throw new RuntimeException("无对应的匹配类型");
    }

    public static Orange getOrange(){
        return new Orange();
    }

    public static Apple getApple(){
        return new Apple();
    }
}
