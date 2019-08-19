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
public class ActivemqConsumerTest {

    public static final String USER = ActiveMQConnection.DEFAULT_USER;
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final int SESSIONNUM = 3;

    /**
     * 使用java jms集成activemq--消费者
     * 这里要先启动消费者的程序，不然消息就会丢失
     * 若重新启动activemq，消息也会丢失（数据存在内存）
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
//        MessageProducer messageProducer;
        //消息的消费者
        MessageConsumer messageConsumer = null;

        connectionFactory = new ActiveMQConnectionFactory(USER,PASSWORD,URL);
        try{
            connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //发布订阅模式（广播），如果发送时未开启consumer，则消息就会丢失。
//            destination = session.createTopic("activeTopic");
            //点对点模式，一条消息只会被一个消费者接受，若关闭activemq，在开启，消息仍然存在。
            destination = session.createQueue("activeQueue");
            messageConsumer = session.createConsumer(destination);
            //1.接收消息，这里是阻塞的方式进行接收
//            Message message;
//            while((message = messageConsumer.receive())!=null) {
//                System.out.println(((TextMessage)message).getText());
//            }
            //2.异步接收消息
            messageConsumer.setMessageListener(mesg-> {
                try {
                    System.out.println(((TextMessage)mesg).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
            //下面finally关掉了，这里先写个死循环防止回收链接。。
            while(true){

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
            if(messageConsumer!=null){
                try {
                    messageConsumer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
