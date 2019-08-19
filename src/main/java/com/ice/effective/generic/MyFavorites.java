package com.ice.effective.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * 异构容器测试
 * @ClassName MyFavorites
 * @Description TODO
 * @Author liubin
 * @Date 2019/4/15 3:58 PM
 **/
public class MyFavorites {
    public static void main(String[] args) {
    }
    Map<Class<?>,Object> favorites = new HashMap<>();

    public <T> void addFavorite(Class<T> tClass,Object object){
        favorites.put(tClass,object);
    }

    public <T> T getFavorite(Class<T> tClass){
        //使用Class类的cast方法进行转化
        return tClass.cast(favorites.get(tClass));
    }
}
