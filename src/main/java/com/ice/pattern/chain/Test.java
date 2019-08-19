package com.ice.pattern.chain;

import com.ice.pattern.factory.Apple;

/**
 * 责任链模式
 * @author: ice
 * @create: 2019/3/27
 **/
public class Test {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart(new Apple()) {
            @Override
            public void pay(int sum) {
                System.out.println("使用微信支付:"+sum);
            }
        };
        //构建链式调用对象
        Discount discount = new CouponDiscount(new FirstPayDiscount(null));
        //传递调用链入口
        shoppingCart.setDiscount(discount);
        shoppingCart.submitOrder();
    }
}
