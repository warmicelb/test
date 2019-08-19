package com.ice.pattern.visitor;

/**
 * 接收访问者的接口
 * @author: ice
 * @create: 2019/4/2
 **/
public interface Accept {
    void accept(Visitor visitor);
}
