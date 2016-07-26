package com.qbsm.web.action.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 该拦截器可以用于用户登录拦截
 * @author xiaoyiming
 * @since 2016.3.18
 */
public class LoginInterceptor implements HandlerInterceptor {
	private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	//返回值表示是否要将当前的请求拦截下来
	//如果返回false,则请求终止,如果返回true,则继续放行
	//请求之前调用
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//如果是跳转到登陆界面,则不拦截
        if (request.getRequestURL().toString().contains("login")) {
        	return true;
        }
		Object object = request.getSession().getAttribute("userSession");
		if(null == object){
			log.info("没用登陆,开启拦截 :object = {}",object);
			request.getRequestDispatcher("/WEB-INF/assets/login.jsp").forward(request, response);
			//如果这里没有session,则可以直接拦截请求	
			return false;
		}
		log.info("已经登陆 :object = {}",object);
		return true;
	}
	//请求时调用
	//通过modelAndView可以改变方式
	//可以改变视图转发路径
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	//请求之后
	//资源销毁
	//流的关闭等等
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
