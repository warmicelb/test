package com.ice.pattern.proxy;

/**
 * @author: ice
 * @create: 2019/2/26
 **/
public class Ice implements SFactory {
    private SManFactory sManFactory;

    public Ice(SManFactory sManFactory) {
        this.sManFactory = sManFactory;
    }

    @Override
    public void saleTool(int size) {
        System.out.println("前置增强");
        sManFactory.saleTool(size);
        System.out.println("后置增强");
    }
}
