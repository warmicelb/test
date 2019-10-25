package com.ice.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 测试zkClientapi
 * 1.可以方便监听节点的变化（原生只能监听一次变化）2可以进行级联操作
 * @ClassName ZkClientTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/10/22 4:24 PM
 **/
public class ZkClientTest {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181",5000);
        //创建级联节点
        zkClient.createPersistent("/zkClient/zkClient1",true);
        List<String> children = zkClient.getChildren("/zkClient");
        //订阅数据变化通知
        zkClient.subscribeDataChanges("/zkClient", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println(s+"has been modified,changed value is "+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println(s+"has been deleted");
            }
        });
        //订阅子节点数据变化通知
        zkClient.subscribeChildChanges("/zkClient", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("children has been modified");
            }
        });
        zkClient.writeData("/zkClient","test11");
        zkClient.createPersistent("/zkClient/zkClient3","333");
        //级联删除
        zkClient.delete("/zkClient/zkClient1",-1);
        zkClient.deleteRecursive("/zkClient");
    }
}
