package com.ice.pattern.template;

import com.ice.pattern.factory.Fruit;

/**
 * 构造方法调用：（子类默认调用父类无参构造器，若父类无参构造器会编译失败（子类可以在构造器中调用父类有参构造器也行））
 * 支付宝支付
 * @author: ice
 * @create: 2019/3/26
 **/
public class AliPayShoppingCart extends ShoppingCart{

    public AliPayShoppingCart(Fruit fruit) {
        super(fruit);
    }

    @Override
    void pay(int sum) {
        System.out.println("使用支付宝支付,支付金额为："+sum);
    }
}
