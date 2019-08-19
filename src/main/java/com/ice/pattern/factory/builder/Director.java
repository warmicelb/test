package com.ice.pattern.factory.builder;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class Director {
    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }
    public Object construct(){
        builder.buildCpu();
        builder.buildDisk();
        builder.buildKeyBoard();
        return builder.build();
    }
}
