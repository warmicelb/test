package com.ice.pattern.decorate;

import com.ice.pattern.factory.Apple;
import com.ice.pattern.factory.pack.ApplePack;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class Test {
    public static void main(String[] args) {
        //装饰（做增强:盖章）
        ApplePack stampApplePack = new StampAppleDecorator(new ApplePack());
        //装饰（做增强：加急）
        ApplePack urge = new UrgeApplePackDecorator(stampApplePack);
        urge.pack(new Apple());
    }
}
