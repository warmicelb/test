package com.ice.pattern.composite;

import java.util.List;

/**
 * 抽象接口，对多种类型进行抽象成一中类型
 * @author: ice
 * @create: 2019/3/26
 **/
public interface Node {

    String getName();

    List<Node> getChildren();
}
