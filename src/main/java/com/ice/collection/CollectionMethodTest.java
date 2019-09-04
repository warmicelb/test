package com.ice.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName CollectionMethodTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/8 2:40 PM
 **/
public class CollectionMethodTest {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(3);
        System.out.println(list1.size()+":"+list2.size());
        //retainAll 将当前集合list1和目标集合list2做交集，移除不属于交集中的元素，返回该集合是否有变化。
        boolean b = list1.retainAll(list2);
        System.out.println(b+"list1:"+list1+",list2:"+list2);
        list1.iterator();
        Set set = new HashSet();

    }
}
