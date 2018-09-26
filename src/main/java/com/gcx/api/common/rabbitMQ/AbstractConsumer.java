package com.gcx.api.common.rabbitMQ;

import com.gcx.api.common.util.StringUtils;
import com.rabbitmq.client.Channel;

/**
 * @Date: 2018/9/7 14:07
 * @Description:
 */
public abstract class AbstractConsumer implements IConsumer {

    protected String name;//队列名字

    protected Channel channel;//队列通道

    protected IMessageListener iMessageListener;

    public AbstractConsumer(String name, Channel channel) {
        this.name = name;
        this.channel = channel;
        if(StringUtils.isEmpty(name)){
            throw new RuntimeException("名称不能为空");
        }
        this.name = name;
        this.channel = channel;
    }

    /**
     * @param listener 回调监听
     */
    @Override
    public void addListener(IMessageListener listener) {
        this.iMessageListener=listener;
    }

    @Override
    protected void finalize() throws Throwable {
        if (this.channel != null) {
            this.channel.close();
        }
    }
}
