package com.ice.concurrent.base;

/**
 *  wait，notify/notifyAll机制，完成线程间的协作
 * @ClassName Express
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/26 4:11 PM
 **/
public class Express {
    private static String district = "shanghai";
    private int distance;
    private String destination;

    public Express(int distance, String destination) {
        this.distance = distance;
        this.destination = destination;
    }

    /**
     * 更改配送距离/路线
     */
    public synchronized void changeDistance(){
        distance = distance+100;
        System.out.println(Thread.currentThread().getName()+" change distance:"+distance);
        notifyAll();
    }

    /**
     * 更改配送地址
     */
    public synchronized void changeDestination(){
        destination = "北京";
        System.out.println(Thread.currentThread().getName()+" change destination:"+destination);
        notifyAll();
    }
    //监听距离大于100的快递
    public synchronized void checkDistance(){
        while(distance<100){
            try {
                System.out.println(Thread.currentThread().getName()+": wait the distance"+distance);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":the distance change:"+distance);
    }
    //监听北京的快递
    public synchronized void checkDestination(){
        while(!"北京".equals(destination)){
            try {
                System.out.println(Thread.currentThread().getName()+": wait the destination"+destination);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":the destination change:"+destination);
    }
}
