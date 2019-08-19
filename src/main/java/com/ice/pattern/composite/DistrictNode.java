package com.ice.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 *  具体类型，地区（多级地域）
 * @author: ice
 * @create: 2019/3/26
 **/
public class DistrictNode implements Node {

    private String name;
    private List<Node> chidlren = new ArrayList<>();

    public DistrictNode(String name) {
        this.name = name;
    }

    public void addChild(Node node){
        chidlren.add(node);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Node> getChildren() {
        return chidlren;
    }
}
