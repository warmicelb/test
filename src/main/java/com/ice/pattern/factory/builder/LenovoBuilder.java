package com.ice.pattern.factory.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ice
 * @create: 2019/3/13
 **/
public class LenovoBuilder implements Builder {
    List<String> result = new ArrayList<>();
    @Override
    public Builder buildCpu() {
        result.add("cpu");
        return this;
    }

    @Override
    public Builder buildKeyBoard() {
        result.add("keyBoard");
        return this;
    }

    @Override
    public Builder buildDisk() {
        result.add("disk");
        return this;
    }

    @Override
    public Object build() {
        return result;
    }
}
