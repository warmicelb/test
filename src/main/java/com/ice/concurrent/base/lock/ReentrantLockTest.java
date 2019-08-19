package com.ice.concurrent.base.lock;

/**
 * @ClassName ReentrantLockTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/11 7:52 PM
 **/
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        LockExpress lockExpress = new LockExpress(10,"南京");
        for(int i=0;i<3;i++){
            new DestinationWork(lockExpress).start();
            new DistanceWork(lockExpress).start();
        }
        Thread.sleep(1000);
        lockExpress.changeDistance();
        lockExpress.changeDestination();
    }
    static class DestinationWork extends Thread{
        private LockExpress express;

        public DestinationWork(LockExpress express) {
            this.express = express;
        }

        @Override
        public void run() {
            express.checkDestination();
        }
    }

    static class DistanceWork extends Thread{
        private LockExpress express;

        public DistanceWork(LockExpress express) {
            this.express = express;
        }

        @Override
        public void run() {
            express.checkDistance();
        }
    }
}
