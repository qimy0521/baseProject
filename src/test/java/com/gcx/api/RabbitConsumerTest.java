package com.gcx.api;

import com.gcx.api.common.rabbitMQ.EnumExChangeType;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: qimy
 * @Date: 2018/9/7 14:26
 * @Description:
 */
public class RabbitConsumerTest {
    private static Connection connection = null;

    private static Channel channel = null;

    private static String userName = "guest";
    private static String password = "guest";
    private static String virtualHost = "/";
    private static String hostName = "localhost";
    private static int portNumber = 5672;

    private static final String QUEUE_KEY = "rabbit";

    private static final String ROUT_KEY = "rout_rabbit";

    private static final String EXCHANGE_KEY = "exchange_rabbit";

    /**
     * 初始化
     */
    public static void init() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);
        connection = factory.newConnection();
    }
    public static void main(String args[]) throws IOException, TimeoutException, InterruptedException {
        init();//初始化链接
        channel = connection.createChannel();//打开链接
        channel.exchangeDeclare(EXCHANGE_KEY, EnumExChangeType.DIRECT.getType(), true);//消费者绑定交换机
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_KEY, "");//绑定队列到交换机
        AMQP.Queue.DeclareOk response = channel.queueDeclarePassive(queueName);
//        while (true){
//            GetResponse getResponse = channel.basicGet(queueName, false);
//            if(getResponse!=null){
//                byte[] body = getResponse.getBody();
//                String s = new String(body);
//                System.out.println(s);
//            }
//        }
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                // (process the message components here ...)
                String message = new String(body);
                System.out.println(" [x] Received ['" + message + "']");
//                channel.basicAck(deliveryTag, false);
            }
        };
        channel.basicConsume(queueName,true,defaultConsumer);

    }

}
