package com.ice.pattern.facade;

/**
 * 将整个苹果采集，打包封闭起来，fruitProcess.process()为唯一访问入口
 * @author: ice
 * @create: 2019/3/26
 **/
public class Test {
    public static void main(String[] args) {
        FruitProcess fruitProcess = new FruitProcess();
        fruitProcess.process();
    }
}
