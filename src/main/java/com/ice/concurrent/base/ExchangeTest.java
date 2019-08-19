package com.ice.concurrent.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @ClassName ExchangeTest
 * @Description TODO 测试exchange，两个线程间进行数据的交换
 * @Author liubin
 * @Date 2019/7/5 11:33 AM
 **/
public class ExchangeTest {
    public static Exchanger<List<Integer>> exchanger = new Exchanger<>();
    public static void main(String[] args) {
        new Thread(new ExchangeWork1()).start();
        new Thread(new ExchangeWork2()).start();
    }
    static public class ExchangeWork1 implements Runnable{

        @Override
        public void run() {
            List<Integer> nums = new ArrayList<>();
            for(int i=0;i<10;i++){
                nums.add(i);
            }
            System.out.println(Thread.currentThread().getName()+"交换前:"+nums.toString());
            try {
                List<Integer> exchange = exchanger.exchange(nums);
                System.out.println(Thread.currentThread().getName()+"交换后:"+exchange.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public class ExchangeWork2 implements Runnable{

        @Override
        public void run() {
            List<Integer> nums = new ArrayList<>();
            for(int i=0;i<10;i++){
                nums.add(i*10);
            }
            System.out.println(Thread.currentThread().getName()+"交换前:"+nums.toString());
            try {
                List<Integer> exchange = exchanger.exchange(nums);
                System.out.println(Thread.currentThread().getName()+"交换后:"+exchange.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
