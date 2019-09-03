package com.emp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//登录拦截器
public class LoginInterceptor implements HandlerInterceptor{
	/**
	 * 1.preHandle在业务处理器处理请求之前被调用
	 * 2.postHandle在业务处理器处理请求执行完成后,生成视图之前执行
	 * 3.afterCompletion在DispatcherServlet完全处理完请求后被调用,可用于清理资源等 
	 *   afterCompletion()执行完成后开始渲染页面
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		
	}
	
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * return true 不拦截 放行,调用业务组件 比如/emp/conditionList
	 * return false 拦截,不调用业务组件
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		Object user = arg0.getSession().getAttribute("user");
		if (user!=null) {
			return true;
		}
		/*
		 * 转发到Login.jsp
		 * 绑定错误消息
		 */
		arg0.setAttribute("msg", "请重新登录!");
		arg0.getRequestDispatcher("/user/toLogin").forward(arg0, arg1);
			return false;
	}

}
