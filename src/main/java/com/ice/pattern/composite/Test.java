package com.ice.pattern.composite;

/**
 * 适用于属性结构的对象（特征比较相似，多级）
 * @author: ice
 * @create: 2019/3/26
 **/
public class Test {
    public static void main(String[] args) {
        DistrictNode districtNode = new DistrictNode("root");
        DistrictNode districtNode1 = new DistrictNode("湖北");
        districtNode1.addChild(new DistrictNode("武汉"));
        DistrictNode districtNode2 = new DistrictNode("浙江");
        districtNode.addChild(districtNode1);
        districtNode.addChild(districtNode2);
    }
}
