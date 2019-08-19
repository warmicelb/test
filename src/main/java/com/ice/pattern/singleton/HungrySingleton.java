package com.ice.pattern.singleton;

/**
 * @author: ice
 * @create: 2019/3/19
 **/
public class HungrySingleton {

    private static AccountBook accountBook = new AccountBook();

    public static AccountBook getInstance(){
        return accountBook;
    }

}
