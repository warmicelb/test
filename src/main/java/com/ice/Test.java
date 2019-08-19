package com.ice;

import java.util.List;

/**
 * @author: ice
 * @create: 2019/3/18
 **/
public class Test {

    public static void main(String[] args) {
        String s = "351-359-356-365-370-282-315-367-357-240-345-286-75-281-368-369-323-366-23-296-320-180-55-137-109-196-238-325-186-264-382-176-541";
        System.out.println(s.split("-").length);
    }

    /**
     *  若方法声明中类型参数只出现一次，考虑使用通配符来代替
     * @param list
     * @param i
     * @param j
     */
    public static void swap(List<?> list,int i,int j){
        //对通配符类型的list操作是不允许的，出了插入null之外
        //解决办法：编写一个辅助方法，来捕捉通配符类型
//        list.set(i,list.set(j,list.get(i)));
        swapHelper(list,i,j);
    }

    /**
     * swap方法的帮助类，为了捕捉类型，辅助方法必须是泛型方法。
     * @param list
     * @param i
     * @param j
     * @param <E>
     */
    public static <E> void swapHelper(List<E> list,int i,int j){
        list.set(i,list.set(j,list.get(i)));
    }



}
