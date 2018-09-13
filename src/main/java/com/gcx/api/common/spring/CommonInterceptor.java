package com.gcx.api.common.spring;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	    private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);  
	    	    
		/**  
	     * 在业务处理器处理请求之前被调用  
	     * 如果返回false  
	     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
	     * 如果返回true  
	     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
	     *    再执行被拦截的Controller  
	     *    然后进入拦截器链,  
	     *    从最后一个拦截器往回执行所有的postHandle()  
	     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
	     */
        @Override
        public boolean preHandle(HttpServletRequest request,
        		HttpServletResponse response, Object handler) throws Exception {
        	  
              return true;
        }
}
