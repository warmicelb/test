package com.ice.effective.annotations;

import java.util.Date;
import java.util.Set;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/9 11:04 AM
 **/
public class Test {
    public static void main(String[] args) {
        Integer a = 10000;
        System.out.println(a.hashCode());
        teset(a);
        System.out.println(a.hashCode());
    }

    /**
     *
     * @param s
     * @throws NullPointerException when s is null
     */
    public static void testMethod(String s) {
        if(s==null){
            throw new NullPointerException();
        }
    }

    private static void testAssert(String s) {
        assert s!=null;
    }

    public static void teset(Integer a){
        a=66666;
        System.out.println(a.hashCode());
    }
}
