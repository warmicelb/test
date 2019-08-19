package com.ice.pattern.strategy;

import com.ice.pattern.factory.Apple;

/**
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
        //这里通过设置不同的子类型，来达到不同的表现行为
        shoppingCart.setDiscount(new FirstPayDiscount());
        shoppingCart.submitOrder();
    }
}
