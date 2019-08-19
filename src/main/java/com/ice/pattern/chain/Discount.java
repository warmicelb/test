package com.ice.pattern.chain;

/**
 * //通过对下一个相同类型对象的引用，来进行链式调用
 * @author: ice
 * @create: 2019/3/27
 **/
public abstract class Discount {
    private Discount discount;

    public Discount(Discount discount) {
        this.discount = discount;
    }

    public int discount(int sum){
        if(discount!= null){
            return discount.discount(sum);
        }else{
            return sum;
        }
    }
}
