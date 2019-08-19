package com.ice.pattern.strategy;

/**
 * 首次购买打折
 * @author: ice
 * @create: 2019/3/27
 **/
public class FirstPayDiscount implements Discount {
    @Override
    public int discount(int money) {
        return money-20;
    }
}
