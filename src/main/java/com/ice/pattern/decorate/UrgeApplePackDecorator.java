package com.ice.pattern.decorate;

import com.ice.pattern.factory.Fruit;
import com.ice.pattern.factory.pack.ApplePack;

import java.io.FilterInputStream;

/**
 * 具体装饰角色，进行增强
 * @author: ice
 * @create: 2019/3/19
 **/
public class UrgeApplePackDecorator extends ApplePackDecorator {

    public UrgeApplePackDecorator(ApplePack applePack) {
        this.applePack = applePack;
    }

    @Override
    public void pack(Fruit fruit) {
        System.out.println("urge:use SF express");
        applePack.pack(fruit);
    }
}
