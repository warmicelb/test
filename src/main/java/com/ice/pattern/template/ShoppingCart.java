package com.ice.pattern.template;

import com.ice.pattern.factory.Apple;
import com.ice.pattern.factory.Fruit;

/**
 * @author: ice
 * @create: 2019/3/26
 **/
public abstract class ShoppingCart {
   private Fruit fruit;

    public ShoppingCart(Fruit fruit) {
        this.fruit = fruit;
    }

    public void submitOrder(){
        //结算
        int sum = sum();
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
    abstract void pay(int sum);

    private int sum() {
        if(fruit instanceof Apple){
            return 10;
        }else{
            return 0;
        }
    }
}
