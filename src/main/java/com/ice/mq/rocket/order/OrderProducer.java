package com.ice.mq.rocket.order;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @ClassName OrderProducer
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/30 10:19 AM
 **/
public class OrderProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("ice");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for(int i=0;i<20;i++){
            Message message = new Message("iceOrder","tag1","key1",("order"+i+" pay").getBytes());
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Integer index = (Integer) o%list.size();
                    return list.get(index);
                }
            }, i);
            System.out.println(JSON.toJSONString(sendResult));
        }
        producer.shutdown();
    }
}
