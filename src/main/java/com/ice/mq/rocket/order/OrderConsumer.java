package com.ice.mq.rocket.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName OrderConsumer
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/30 10:33 AM
 **/
public class OrderConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("ice");
        pushConsumer.setNamesrvAddr("localhost:9876");
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        pushConsumer.subscribe("iceOrder","tag1");
        pushConsumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        pushConsumer.start();
        System.out.println("consumer start");
    }
}
