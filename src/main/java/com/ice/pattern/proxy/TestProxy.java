package com.ice.pattern.proxy;

/**
 * @author: ice
 * @create: 2019/2/26
 **/
public class TestProxy {

    public static void main(String[] args) {
        /**
         * 静态代理
         */
        SManFactory sManFactory = new SManFactory();
        Ice ice = new Ice(sManFactory);
        ice.saleTool(1);
    }
}
