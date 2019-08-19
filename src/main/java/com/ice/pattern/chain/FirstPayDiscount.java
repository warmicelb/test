package com.ice.pattern.chain;

/**
 * 通过引用下个调用对象，来达到链式调用
 * @author: ice
 * @create: 2019/3/27
 **/
public class FirstPayDiscount extends Discount {

    public FirstPayDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public int discount(int sum) {
        System.out.println("首次购买优惠规则判定");
        sum = sum-20;
        return super.discount(sum);
    }
}
