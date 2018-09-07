package com.gcx.api.common.rabbitMQ;


import java.io.Serializable;

/**
 * 生产者
 *
 */
public interface IProducer {
    /**
     * 发送字节流
     * @param bytes 字节流
     */
    void send(byte[] bytes);
    /**
     * 发送序列化对象
     * @param serializable
     */
    void send(Serializable serializable);
    /**
     * 发送字符串
     * @param str
     */
    void send(String str);

}
