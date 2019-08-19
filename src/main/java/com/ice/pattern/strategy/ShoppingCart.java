package com.ice.pattern.strategy;

import com.ice.pattern.factory.Apple;
import com.ice.pattern.factory.Fruit;

/**
 * 注入打折对象，根据注入不同的实现类型（选择不同的策略），达到不同的行为
 * @author: ice
 * @create: 2019/3/26
 **/
public abstract class ShoppingCart {
   private Fruit fruit;
   private Discount discount;

    public ShoppingCart(Fruit fruit) {
        this.fruit = fruit;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void submitOrder(){
        //结算
        int sum = sum();
        //打折
        sum = discount.discount(sum);
        //支付
        pay(sum);

        sendHome();
    }

    private void sendHome() {
        System.out.println("免费配送到家");
    }

    /**
     * 抽象方法：支付（子类实现）
     * @param sum
     */
    public abstract void pay(int sum);

    private int sum() {
        if(fruit instanceof Apple){
            return 150;
        }else{
            return 100;
        }
    }
}
