package com.emp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//��¼������
public class LoginInterceptor implements HandlerInterceptor{
	/**
	 * 1.preHandle��ҵ��������������֮ǰ������
	 * 2.postHandle��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ��
	 * 3.afterCompletion��DispatcherServlet��ȫ����������󱻵���,������������Դ�� 
	 *   afterCompletion()ִ����ɺ�ʼ��Ⱦҳ��
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		
	}
	
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * return true ������ ����,����ҵ����� ����/emp/conditionList
	 * return false ����,������ҵ�����
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		Object user = arg0.getSession().getAttribute("user");
		if (user!=null) {
			return true;
		}
		/*
		 * ת����Login.jsp
		 * �󶨴�����Ϣ
		 */
		arg0.setAttribute("msg", "�����µ�¼!");
		arg0.getRequestDispatcher("/user/toLogin").forward(arg0, arg1);
			return false;
	}

}
