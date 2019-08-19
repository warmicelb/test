package com.ice.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ice
 * @create: 2019/4/2
 **/
public class Test {

    public static void main(String[] args) {
        //访问者
        Visitor visitor = new Visitor();
        //被访问者集合
        List<FruitVisit> list = new ArrayList<>();
        list.add(new Banana());
        list.add(new Straberry());
        list.forEach(fruit -> fruit.accept(visitor));
    }
}
