package com.ice.pattern.adapter;

import com.ice.pattern.factory.Fruit;
import com.ice.pattern.factory.pack.ApplePack;
import com.ice.pattern.factory.pack.OrangePack;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class OrangePackAdapter extends OrangePack {
    private ApplePack applePack;

    @Override
    public void pack(Fruit fruit) {
        applePack.pack(fruit);
    }

    public OrangePackAdapter(ApplePack applePack) {
        this.applePack = applePack;
    }

    public OrangePackAdapter() {
    }
}
