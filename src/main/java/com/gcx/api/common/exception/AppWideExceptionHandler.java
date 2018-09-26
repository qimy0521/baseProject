package com.gcx.api.common.exception;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gcx.api.common.util.MyResult;

/**
 * controller 异常控制
 */
@RestControllerAdvice
public class AppWideExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public MyResult exception(HttpServletRequest request,HttpServletResponse response, Exception ex){
		String data="异常名:"+ex.getClass()+"||方法名:"+ex.getStackTrace()[0].getMethodName()+"||类名:"+ex.getStackTrace()[0].getClassName()+"||行数:"+ex.getStackTrace()[0].getLineNumber();
		return MyResult.exception(data,ex.getMessage(),500);
	}
}
