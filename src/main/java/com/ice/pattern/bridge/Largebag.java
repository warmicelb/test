package com.ice.pattern.bridge;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class Largebag implements Bag {

    //组合第二个维度的属性
    private Material material;
    @Override
    public void pack() {
        material.produce();
        System.out.println(" a large bag");
    }

    public Largebag(Material material) {
        this.material = material;
    }

    public Largebag() {
    }
}
