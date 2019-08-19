package com.ice.pattern.strategy;

/**
 * 优惠券打折
 * @author: ice
 * @create: 2019/3/27
 **/
public class CouponDiscount implements Discount{
    @Override
    public int discount(int money) {
        return money>100?money-20:money;
    }
}
