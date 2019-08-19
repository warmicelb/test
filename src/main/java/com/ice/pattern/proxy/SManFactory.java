package com.ice.pattern.proxy;

/**
 * @author: ice
 * @create: 2019/2/26
 **/
public class SManFactory implements SFactory {
    @Override
    public void saleTool(int size) {
        System.out.println("buy"+size);
    }
}
