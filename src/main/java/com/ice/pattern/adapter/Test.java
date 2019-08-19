package com.ice.pattern.adapter;

import com.ice.pattern.factory.Orange;
import com.ice.pattern.factory.pack.ApplePack;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class Test {
    public static void main(String[] args) {
        ApplePack applePack = new ApplePack();
        OrangePackAdapter orangePackAdapter = new OrangePackAdapter(applePack);
        orangePackAdapter.pack(new Orange());
    }
}
