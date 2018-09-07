package com.gcx.api.common.rabbitMQ;

/**
 * @Auther: root
 * @Date: 2018/9/7 14:05
 * @Description:
 */
public interface IConsumer {

    void addListener(IMessageListener listener);
}
