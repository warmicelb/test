package com.ice.test;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName LockTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/25 5:09 PM
 **/
@Data
public class LockTest {
    Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        String s = "http://longniufin.com/bdyrzxzf/index.html?channel=yrbdzx-594-zxcx&utm_source=yrbdzx&utm_medium=yrbdzx-594-zxcx&bd_vid=22221";
        Pattern compile = Pattern.compile("utm_source=(.*?)&");
        Matcher matcher = compile.matcher(s);
        if(matcher.find()){
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }
        LockTest lockTest = new LockTest();
        lockTest.lock.lock();
        System.out.println("ok");
        try{
            new Thread(()->{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep end");
                throw new RuntimeException();
            }).start();
            System.out.println("end");
        }finally {
            lockTest.lock.unlock();
        }
        Thread.sleep(4000);
        System.out.println("main end");
    }
}
