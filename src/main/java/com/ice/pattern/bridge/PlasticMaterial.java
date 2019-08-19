package com.ice.pattern.bridge;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class PlasticMaterial implements Material {
    @Override
    public void produce() {
        System.out.println("use plastic product");
    }
}
