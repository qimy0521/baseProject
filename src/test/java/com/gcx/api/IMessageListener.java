package com.gcx.api;

/**
 * 消费者监听回调
 * IMessageListener
 * @author	Cai
 * @time	2016年9月27日-下午3:01:34
 */
public interface IMessageListener {
	
	void onMessage(byte[] bytes);
	
}
