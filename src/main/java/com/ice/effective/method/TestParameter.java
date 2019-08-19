package com.ice.effective.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @ClassName TestParameter
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/14 10:26 AM
 **/
public class TestParameter {
    private static Random random = new Random();
    public static void main(String[] args) {
        test4();
    }

    public static void test3(){
        String s = "(([01]?[0-9])|(2[0-3])):[0-5][0-9]";
        boolean matches = "22:12".matches(s);
        System.out.println(matches);
    }
    public static void test4(){
        String s = "{\n" +
                "    \"code\": 0,\n" +
                "    \"message\": \"\",\n" +
                "    \"data\": {\n" +
                "        \"access_token\": \"228bd56b7ee039540953352f766b40d31651487e\",\n" +
                "        \"refresh_token\": \"854e744a1f4c6fc20f498e366b9aabd2c4b971fd\",\n" +
                "        \"access_token_expires_in\": 86400,\n" +
                "        \"refresh_token_expires_in\": 2592000 \n" +
                "    }\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(s);
        JSONObject data = (JSONObject) jsonObject.get("data");
        System.out.println(data.get("access_token"));

    }
    /**
     * 防止特殊符号转义x<y{@literal}
     * 代码片段(会以特殊的字体展示代码块){@code}
     * @param number
     * @param args
     */
    public static void test(int number,int... args){
        int result = number;
        if(args.length!=0){
            for (int n:args
                 ) {
                result +=n;
            }
        }
        System.out.println(result);
    }

    public static void test1(){
        int[] a = {1,2,3,4};
        String[] s = {"a"};
        List<String> list = Arrays.asList(s);
        String[] objects = (String[]) list.toArray();
        System.out.println(a);
        System.out.println(Arrays.toString(a));
    }

    public static void testRd(){
        int n = Integer.MAX_VALUE/3*2;
        int low =0;
        for(int i=0;i<1000000;i++){
            if(get(n)<n/2)
                low++;
        }
        System.out.println(low);
    }

    public static int get(int n){
        return Math.abs(random.nextInt())%n;
    }
}
interface A{
    /**
     * 返回指定位置的元素
     *
     * @param position 指定位置下标
     * @param <T> 返回元素类型（泛型）
     * @return 返回元素
     * @throws IndexOutOfBoundsException if{@code position<this.size}
     */
    <T> T get(int position);
}