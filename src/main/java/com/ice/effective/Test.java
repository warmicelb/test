package com.ice.effective;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ice
 * @create: 2019/4/1
 **/
public class Test {
    public static void main(String[] args) {
        String s = "userId,user.userId,user.pwd,name,user.name";
        Map map = MapUtils.putAll(new HashMap(16), s.split(","));
        System.out.println("ok");
    }
}
