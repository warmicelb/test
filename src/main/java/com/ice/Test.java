package com.ice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author: ice
 * @create: 2019/3/18
 **/
public class Test {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String s = "351-359-356-365-370-282-315-367-357-240-345-286-75-281-368-369-323-366-23-296-320-180-55-137-109-196-238-325-186-264-382-176-541";
//        System.out.println(s.split("-").length);
        System.out.println(getNonce(1245L));

        @SuppressWarnings("unused")
        List<Object> objects = Arrays.asList(String.class, Integer.class, Long.class,new Object());
        @SuppressWarnings("rawtypes")
        List a = new ArrayList();
        System.out.println(a.toArray());
    }

    public static String getNonce(Long userDeviceAdId) {
        StringBuilder sb = new StringBuilder(userDeviceAdId.toString());
        for (int i = 32 - sb.length(); i > 0; i--) {
            Random random = new Random();
            char r = (char) (random.nextInt(26) + 65);
            int index = random.nextInt(sb.length());
            sb.insert(index>0?index-1:index, new String(new char[]{r}));
        }
        return sb.toString();
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
