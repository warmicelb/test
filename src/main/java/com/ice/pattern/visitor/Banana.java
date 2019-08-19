package com.ice.pattern.visitor;

/**
 * 实现accept接口
 * @author: ice
 * @create: 2019/4/2
 **/
public class Banana implements FruitVisit {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void eat() {

    }
}
