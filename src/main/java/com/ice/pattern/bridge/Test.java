package com.ice.pattern.bridge;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class Test {
    public static void main(String[] args) {
        Material material = new PlasticMaterial();
        Largebag largebag = new Largebag(material);
        largebag.pack();
        SmallBag smallBag = new SmallBag(material);
        smallBag.pack();
    }
}
