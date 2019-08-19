package com.ice.pattern;

import com.ice.effective.inner.MyA;

import java.util.*;

/**
 * @author: ice
 * @create: 2019/4/4
 **/
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.forEach(e-> System.out.println(e));
        //list<String>是List的子类型，但不是List<Object>的子类型
        List a = list;
        a.add(null);
        a.add("aa");
        a.add(1);
        list = a;
//        List<Object> c = list;
        for(Iterator iterator = list.iterator();iterator.hasNext();){
            Object next = iterator.next();
            System.out.println(next);
        }
        tes();
    }
    public static void te(List<?> list){
        //这种情况下，只允许添加null对象
        list.add(null);
    }
    public static void tes(){
        //无法消除警告，同时可以证明引起警告的代码是安全的，可以加上注解来禁止警告
        @SuppressWarnings("unchecked")
        Set<String> o = new HashSet();
        if(o instanceof Set){
            Set<?> a =  o;
        }
    }
    public static void testA(){
//        List<Object> a = new ArrayList<String>();编译不报错
        //定义long数组，用object数组接收，放入string，
        Object[] objects = new Long[2];
        objects[0]="aa";
    }
}
