package com.ice.pattern.chain;

/**
 * @author: ice
 * @create: 2019/3/27
 **/
public class CouponDiscount extends Discount {
    public CouponDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public int discount(int sum) {
        System.out.println("优惠券优惠规则判定");
        sum = sum>100?100-20:sum;
        return super.discount(sum);
    }
}
