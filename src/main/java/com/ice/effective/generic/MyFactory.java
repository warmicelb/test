package com.ice.effective.generic;

/**
 * @author: ice
 * @create: 2019/4/11
 **/

import java.util.*;

/**
 *@ClassName MyFactory
 *@Description TODO
 *@Author liubin
 *@Date 2019/4/11 10:48 AM
 **/
public class MyFactory<T> {
    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
//        new Comparable<String>() {
//            @Override
//            public int compareTo(String o) {
//                return 0;
//            }
//        }
//        List<ArrayList> list = new ArrayList<>();
//        List<ArrayList> list2 = list;
//        List<ArrayList> union = union(list2, list2);
        //测试恒等函数
        List<String> list = MyFactory.getList();
        List<Integer> list1 = MyFactory.getList();

        Set<String> objects = Collections.emptySet();
    }
    public static <T> List<T> getList(){
        return (List<T>) list;
    }
    //泛型方法，合并同种类型的list集合
    public static <T extends List> List<T> union(List list1,List list2){
        List<T> list = new ArrayList<T>(list1);
        list.addAll(list2);
        return list;
    }

    /**
     * 这里限制了T类型，是必须实现了comparable<T>的接口
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> T max(List<T> list){
        return Collections.max(list);
    }
}
