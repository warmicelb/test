package com.ice.enums;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class ResultEnumContext {

    private static ThreadLocal<ResultEnum> resultEnum = new ThreadLocal();

    public static ResultEnum getResultEnum(){
        return resultEnum.get();
    }

    public static void setResultEnum(ResultEnum itemEnum){
        resultEnum.set(itemEnum);
    }

    public static void remove(){
        resultEnum.set(null);
        resultEnum.remove();
    }
}
