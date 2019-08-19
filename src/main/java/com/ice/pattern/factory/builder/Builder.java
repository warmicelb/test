package com.ice.pattern.factory.builder;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public interface Builder {
    Builder buildCpu();
    Builder buildKeyBoard();
    Builder buildDisk();
    Object build();
}
