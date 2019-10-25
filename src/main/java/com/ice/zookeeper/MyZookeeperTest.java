package com.ice.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;

/**
 * 测试原生zookeeper连接及简单操作
 * @ClassName MyZookeeperTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/10/21 7:17 PM
 **/
public class MyZookeeperTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                }
                if(watchedEvent.getType()==Event.EventType.NodeDataChanged){
                    System.out.println(watchedEvent.getPath()+"发生变化");
                }
            }
        });
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
        //zk操作
        //1.创建节点
        zooKeeper.create("/ice11","javaConnection".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //2.获取节点
        Stat state = new Stat();
        //这里watch设置为true，则节点下次被修改是会通知(通知后监听就失效了，只能下次取时再设置watch为true)
        byte[] data = zooKeeper.getData("/ice11", true, state);
        System.out.println(new String(data));
        //3.修改节点
        zooKeeper.setData("/ice11","javaModify".getBytes(),-1);
        zooKeeper.setData("/ice11","javaModify1".getBytes(),-1);
        //4.删除节点
        zooKeeper.delete("/ice11",-1);
    }

    /**
     * zk digest认证密码加密
     * @param origin
     * @return
     */
    public static String digestPwd(String origin){
        String digest = null;
        try {
            digest = DigestAuthenticationProvider.generateDigest(origin);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return digest;
    }
}
