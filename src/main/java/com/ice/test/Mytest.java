package com.ice.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Mytest
 * @Description TODO
 * @Author liubin
 * @Date 2019/11/4 4:06 PM
 **/
public class Mytest {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(199L);
        List<Long> list1 = new ArrayList<>();
        list1.add(199L);
        list.removeAll(list1);
        System.out.println(list);
    }
}
