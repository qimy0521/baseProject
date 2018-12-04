package com.gcx.api.common.rabbitMQ.queue;

import com.gcx.api.common.rabbitMQ.AbstractProducer;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @Date: 2018/9/7 11:09
 */
public class RabbitProducerService extends AbstractProducer {

    private String queueName;

    /**
     * @param name 队列queue名字
     * @param channel 通道
     */
    public RabbitProducerService(String name, Channel channel) {
        super(name, channel);
        this.queueName=name;
        try {
            init();
        } catch (IOException e) {
        }
    }


    /**
     * 初始化创建queueDeclare
     */
    public void init() throws IOException {
        this.channel.queueDeclare(queueName,false,false,false,null);
    }
    /**
     * 发送字节流
     * @param bytes 字节流
     */
    @Override
    public void send(byte[] bytes) {
        try {
            if(channel==null){
            }
            channel.basicPublish("",this.queueName,null,bytes);
        } catch (IOException e) {
        }
    }
}
