package com.gcx.api;

import com.gcx.api.common.rabbitMQ.EnumExChangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: qimy
 * @Date: 2018/9/7 14:26
 * @Description:
 */
public class RabbitProducerTest {

    private Connection connection=null;

    private Channel channel=null;

    private static String userName="guest";
    private static String password="guest";
    private static String virtualHost="/";
    private static String hostName="localhost";
    private static int portNumber=5672;

    private static final String QUEUE_KEY="rabbit";

    private static final String ROUT_KEY="rout_rabbit";

    private static final String EXCHANGE_KEY= "exchange_rabbit";
    /**
     * 初始化
     */
    public void init() throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);
        connection = factory.newConnection();
    }

    @Test
    public void send() throws IOException, TimeoutException, InterruptedException {
            init();
            channel = connection.createChannel();
            /**
             * 方法的三个参数，第一个为交换机的KEY,第二个参数为交换机类型，第三个参数，是否持久化
             */
            channel.exchangeDeclare(EXCHANGE_KEY, EnumExChangeType.DIRECT.getType(),true);
            while (true){
                String str="hello word  "+Thread.currentThread().getName()+new Date();
                channel.basicPublish(EXCHANGE_KEY,"",null,str.getBytes());
                System.out.println(str);
                Thread.sleep(3000L);

            }
    }

    @Override
    protected void finalize() throws Throwable {
        if (this.channel != null) {
            this.channel.close();
        }
    }

}
