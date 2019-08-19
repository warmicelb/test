package com.ice.enums;

/**
 * 结论：枚举是全局唯一的，但是使用ThreadLocal时，是对拷贝了枚举的一个副本，所以对ThreadLocal中的操作不会影响全局的enum对象
 * @author: ice
 * @create: 2019/3/20
 **/
public class TestEnum {
    public static void main(String[] args) {
        Thread t1 = new Thread(new A());
        Thread t2 = new Thread(new A());
        Thread t3 = new Thread(new A());
        Thread t4 = new Thread(new A());
        Thread t5 = new Thread(new A());
        t1.start();
        System.out.println(ResultEnum.TRIPARTITE_FAIL.getMsg());
        t2.start();
        System.out.println(ResultEnum.TRIPARTITE_FAIL.getMsg());
        t3.start();
        System.out.println(ResultEnum.TRIPARTITE_FAIL.getMsg());
        t4.start();
        System.out.println(ResultEnum.TRIPARTITE_FAIL.getMsg());
        t5.start();
        System.out.println(ResultEnum.TRIPARTITE_FAIL.getMsg());

    }
}

class A implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ResultEnumContext.setResultEnum(ResultEnum.TRIPARTITE_FAIL);
        System.out.println("修改前"+ResultEnumContext.getResultEnum().getMsg());
        ResultEnumContext.getResultEnum().setMsg(Thread.currentThread().getName());
        System.out.println("修改后"+ResultEnumContext.getResultEnum().getMsg());
    }
}
