package com.ice.pattern.visitor;

import com.ice.pattern.factory.Apple;
import com.ice.pattern.factory.Fruit;
import com.ice.pattern.factory.Orange;

/**
 * 访问者
 * @author: ice
 * @create: 2019/4/2
 **/
public class Visitor {
    public void visit(Straberry fruit){
        System.out.println("草莓 ￥20");
    }
    public void visit(Banana fruit){
        System.out.println("香蕉 ￥20");
    }

    public void visit(Fruit fruit){
        System.out.println("其他水果 ￥10");
    }
}
