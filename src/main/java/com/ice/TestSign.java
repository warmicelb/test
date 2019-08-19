package com.ice;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName TestSign
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/27 3:43 PM
 **/
public class TestSign {

    public static void main(String[] args) {
        System.out.println("fjsdfdslf\njfdsfs".replace("\n",""));
//        Map<String,Object> map = new HashMap<>();
//        map.put("vendor","anniu");
//        map.put("sign","dfsdf");
//        map.put("a","rew");
//        map.put("c","hgg");
//        map.put("b","lo");
//        verifySignForxyd(map);
    }
    public static  String verifySignForxyd(Map<String,Object> response){
        String result = null;
        Map<String,Object> param = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        response.entrySet().stream().filter(e->!"sign".equals(e.getKey()))
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e->sb.append(e.getKey()+"="+e.getValue()+"&"));
        String plainText = sb.toString().endsWith("&")?sb.substring(0,sb.length()-1):sb.toString();
        param.put("plainText",plainText);
        param.put("sign",response.get("sign"));
        param.put("vendor",response.get("vendor"));
        return result;
    }
}
