package com.ice.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * curator测试用例
 * 1.链式调用风格。2事物支持
 * 参考资料：http://blog.itpub.net/29254281/viewspace-2097459/
 * @ClassName CuratorTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/10/22 5:30 PM
 **/
public class CuratorTest {
    public static void main(String[] args) throws Exception {
        //建立连接
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3));
        CuratorFramework curatorFramework = builder.build();
        curatorFramework.start();
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/asynTest");
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/transactionTest");


        //创建节点
        String result = curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/curator", "testCurator".getBytes());
        System.out.println("创建节点/curator完成");
        //获取节点的值
        byte[] bytes = curatorFramework.getData().forPath("/curator");
        System.out.println(new String(bytes));
        //异步操作
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        curatorFramework.create().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("asyn task finished:creating zNode /asynTest");
                countDownLatch.countDown();
            }
        },threadPool).forPath("/asynTest");
        countDownLatch.await();
        threadPool.shutdown();
        //事物操作
        curatorFramework.inTransaction().create().forPath("/transactionTest").and().setData().forPath("/transactionTest","1111".getBytes()).and().commit();
        //事件监听(PathCache,NodeCache,TreeCache)
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,"/curator",true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("curator监听到事件变化，子节点变化");
            }
        });
        curatorFramework.create().forPath("/curator/curator1");
        //这里让主线程休眠，不然监听的线程会随主线程而死亡
        Thread.sleep(50000);
    }
}
