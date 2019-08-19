package com.ice.mq.active;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/22 2:11 PM
 **/
public class Test {
    public static void main(String[] args) {
//        Integer a = 1000;
//        System.out.println(a--+a--);
//        System.out.println(a);
//        System.out.println(System.identityHashCode(a));
//        Integer c= a;
//        System.out.println(System.identityHashCode(c));
//        Integer b= a-1;
//        System.out.println(System.identityHashCode(b));
//        te(15);
//        String s ="笔画数：8；\",\n" +
//                "      \"部首：口；\",";
//        Matcher matcher = Pattern.compile("\\d+").matcher(s);
//        if(matcher.find()){
//            System.out.println(matcher.group());
//        }
//        System.out.println(s.replaceAll("($.*(\\d+).*$)","$2"));
        StringBuilder s = new StringBuilder("fsdfdsfsdfsd\nfsdfs\n");
        System.out.println(s.charAt(s.length()-1));
        if(s.charAt(s.length()-1)=='\n'){
            System.out.println("fsdfds");
        }
        System.out.println(s.toString());
    }
    public static void te(Integer i){
        System.out.println("loop"+i);
        if(i>10){
            te(i--);
        }
    }
}
