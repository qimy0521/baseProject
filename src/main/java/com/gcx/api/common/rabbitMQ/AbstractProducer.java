package com.gcx.api.common.rabbitMQ;


import com.gcx.api.common.exception.ParameterException;
import com.gcx.api.common.util.SerializeUtil;
import com.gcx.api.common.util.StringUtils;
import com.rabbitmq.client.Channel;

import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 生产者抽象类
 * 主要定义了链接通道
 * 队列的KEY
 */
public abstract class AbstractProducer implements IProducer {

    protected String name;//队列的名字

    protected Channel channel; //队列的连接通道

    public AbstractProducer(String name, Channel channel) {
        if(StringUtils.isEmpty(name)){
            throw new ParameterException("名称不能为空");
        }
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void send(@Null Serializable obj) {
        byte[] serialize = SerializeUtil.serialize(obj);
        send(serialize);
    }

    @Override
    public void send(@Null String str) {
        byte[] bytes = str.getBytes();
        send(bytes);
    }

    @Override
    protected void finalize() throws Throwable {
        if (this.channel != null) {
            this.channel.close();
        }
    }

}
