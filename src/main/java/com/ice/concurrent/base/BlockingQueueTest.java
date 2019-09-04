package com.ice.concurrent.base;

import com.ice.concurrent.framework.core.ItemVo;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName BlockingQueueTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/27 10:29 AM
 **/
public class BlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayQueue = new ArrayBlockingQueue<Integer>(1000);
        LinkedBlockingQueue<Integer> linkedQueue = new LinkedBlockingQueue<>();
        DelayQueue<ItemVo> delayQueue = new DelayQueue<ItemVo>();
        PriorityQueue<Object> objects = new PriorityQueue<>();
        SynchronousQueue<Object> objects1 = new SynchronousQueue<>();
    }
}
