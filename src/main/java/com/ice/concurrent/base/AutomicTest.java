package com.ice.concurrent.base;

import lombok.Data;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 测试atomicStampedReference(CAS的基础上增加版本号解决ABA问题)
 * @ClassName AutomicTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/8 11:28 AM
 **/
public class AutomicTest {
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        user.setUserName("ice");
        user.setPwd("123456");
        AtomicStampedReference<User> stampedReference = new AtomicStampedReference(user,1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "当前user信息，name：" + stampedReference.getReference().getUserName() + ",pwd:" + stampedReference.getReference().getPwd()+",当前版本号:"+stampedReference.getStamp());
                User newUser = new User();
                newUser.setUserName("iceaaa");
                newUser.setPwd("iceaaa");
                System.out.println(Thread.currentThread().getName() + "更改user信息，更改是否成功：" + stampedReference.compareAndSet(user, newUser, 1, 2) + ",更改后，name：" + stampedReference.getReference().getUserName() + ",pwd:" + stampedReference.getReference().getPwd()+",当前版本号："+stampedReference.getStamp());
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "当前user信息，name：" + stampedReference.getReference().getUserName() + ",pwd:" + stampedReference.getReference().getPwd()+",当前版本号:"+stampedReference.getStamp());
                User newUser = new User();
                newUser.setUserName("nawuaaa");
                newUser.setPwd("nawuaaa");
                //这里使用因为是预期和要修改的user对象，必须是不同的两个对象，做替换
                System.out.println(Thread.currentThread().getName() + "更改user信息，更改是否成功：" + stampedReference.compareAndSet(user, newUser, 1, 2) + ",更改后，name：" + stampedReference.getReference().getUserName() + ",pwd:" + stampedReference.getReference().getPwd()+",当前版本号："+stampedReference.getStamp());
            }
        });
        thread.start();
        thread.join();
        System.out.println("thread end");
        thread1.start();
        thread1.join();
        System.out.println("thread1 end");
    }

}
@Data
class User{
    private String userName;
    private String pwd;

}
