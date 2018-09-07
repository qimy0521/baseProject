package com.gcx.api.common.rabbitMQ.queue;

import com.gcx.api.common.exception.ParameterException;
import com.gcx.api.common.rabbitMQ.AbstractConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @Auther: root
 * @Date: 2018/9/7 14:12
 * @Description:
 */
public class RabbitConsumerService extends AbstractConsumer {

    private String queueName;

    /**
     * @param name 队列的名字
     * @param channel 队列的通道
     */
    public RabbitConsumerService(String name, Channel channel) {
        super(name, channel);
        this.queueName=name;
        try {
            init();
        } catch (IOException e) {
           throw new ParameterException("初始话失败");
        }
    }

    private void init() throws IOException {
        this.channel.queueDeclare(queueName,false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(this.channel) {//实现监听方法
            @Override
            public void handleDelivery(String consumerTag,Envelope envelope, AMQP.BasicProperties properties,byte[] body)throws IOException
            {
                if(iMessageListener!=null){
                    iMessageListener.onMessage(body);
                }
            }
        };
        this.channel.basicConsume(this.queueName, true, consumer);
    }
}
