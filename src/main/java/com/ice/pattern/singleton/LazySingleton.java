package com.ice.pattern.singleton;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class LazySingleton {

    public static AccountBook accountBook = null;
    public static AccountBook getInstance(){
        //双重校验锁
        if(accountBook==null){
            synchronized (LazySingleton.class){
                if(accountBook==null){
                    accountBook = new AccountBook();
                }
            }
        }
        return accountBook;
    }
}
