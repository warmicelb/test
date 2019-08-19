package com.ice.mq.active;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName ActivemqTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/22 10:45 AM
 **/
public class ActivemqProducerTest {

    public static final String USER = ActiveMQConnection.DEFAULT_USER;
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final int SESSIONNUM = 10;

    /**
     * 使用java jms集成activemq
     * @param args
     */
    public static void main(String[] args) {
        //工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话
        Session session = null;
        //消息目的地(一般☞topic)
        Destination destination;
        //消息的生产者
        MessageProducer messageProducer = null;
        //消息的消费者
//        MessageConsumer messageConsumer;

        connectionFactory = new ActiveMQConnectionFactory(USER,PASSWORD,URL);
        try{
            connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//            destination = session.createTopic("activeTopic");
            destination = session.createQueue("activeQueue");
            messageProducer = session.createProducer(destination);
            for(int i =0 ;i<SESSIONNUM;i++){
                String msg = "生产者发送消息啦！这是第"+i+"次发送。";
                TextMessage textMessage = session.createTextMessage(msg);
                messageProducer.send(textMessage);
            }
        }catch (JMSException e) {
            e.printStackTrace();
        } finally {

            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(session!=null){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(messageProducer!=null){
                try {
                    messageProducer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
