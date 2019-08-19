package com.ice.pattern.template;

import com.ice.pattern.factory.Apple;

/**
 * 将某个扩展的方法抽象出来，由子类去实现
 * @author: ice
 * @create: 2019/3/26
 **/
public class Test {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new AliPayShoppingCart(new Apple());
        shoppingCart.submitOrder();
    }
}
