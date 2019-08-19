package com.ice.concurrent.base;

/**
 * @ClassName TestWaitNotify
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/26 5:23 PM
 **/
public class TestWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        Express express = new Express(50,"nanjing");
        CourierDistance runnable = new CourierDistance(express);
        for(int i=0;i<3;i++) {
            Thread courierDistance = new Thread(runnable);
            courierDistance.start();
        }
        CourierDestination runnable2 = new CourierDestination(express);
        for(int i=0;i<3;i++) {
            Thread courierDestination = new Thread(runnable2);
            courierDestination.start();
        }
        Thread.sleep(2000);
        express.changeDestination();
        express.changeDistance();
    }
}
class CourierDistance implements Runnable{

    private Express express;

    public CourierDistance(Express express) {
        this.express = express;
    }

    @Override
    public void run() {
        express.checkDistance();
    }
}
class CourierDestination implements Runnable{

    private Express express;

    public CourierDestination(Express express) {
        this.express = express;
    }

    @Override
    public void run() {
        express.checkDestination();
    }
}
