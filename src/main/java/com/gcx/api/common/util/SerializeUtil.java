package com.gcx.api.common.util;

import com.gcx.api.common.exception.ParameterException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化工具类
 * SerializeUtil
 * @author	Cai
 * @time	2016年9月26日-下午2:56:00
 */
public class SerializeUtil {
	
	/**
	 * 可序列化对象，序列化成二进制
	 * serialize
	 * @param obj
	 * @return
	 * @throws ParameterException
	 * @author	Cai
	 * @time	2016年9月26日-下午2:56:20
	 */
	public static byte[] serialize(Serializable obj) throws ParameterException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);  
			oos.writeObject(obj);
			oos.flush();  
			oos.close();
			return baos.toByteArray();
		} catch (IOException e) {
			throw new ParameterException("序列化失败");
		}
	}
	
	/**
	 * 二进制反序列化成实体类
	 * deserialize
	 * @param bytes
	 * @return
	 * @throws ParameterException
	 * @author	Cai
	 * @time	2016年9月26日-下午2:57:02
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(byte[] bytes) throws ParameterException {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);  
			T t = (T) ois.readObject();
			ois.close();  
			bais.close(); 
			return t;
		} catch (ClassNotFoundException e) {
			throw new ParameterException("序列化失败，类型不存在");
		} catch (IOException e) {
			throw new ParameterException("反序列化失败");
		}
	}

}
