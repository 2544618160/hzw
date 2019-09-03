package com.emp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.User;
import com.emp.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/user/toRegister")
	public String toAdd() {
		return "Register";
	}
	
	//ע���û�
	@RequestMapping("/user/register")
	public String register(User user){
		userService.addUser(user);
		//�ض����¼ҳ��
		return "redirect:/uset/toLogin";
	}
	
	@RequestMapping("/user/toLogin")
	public String tologin(User user) {
		return "Login";
	}
	
	@RequestMapping("/user/login")
	public String login(User user,@RequestParam(value="rememberMe",required=false,defaultValue="0")Integer rememberMe,Model model){
		//��ȡ��ǰ�û� Subject ���� ����"/user/login"����Ķ���(�û�/����)
		Subject subject=SecurityUtils.getSubject();
		//����һ�����ƶ���
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try{
			//�ж��Ƿ�ʹ�ü�ס��
			if (rememberMe==1) {
				token.setRememberMe(true);
			}
			
			//Ϊ��ǰ�û�������֤����Ȩ
			subject.login(token);
			//subject.logout(); �ǳ�
			return "redirect:/emp/conditionList";
			
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("msg", "�û������������");
			return "Login";
		}
	}
	
	@RequestMapping("/teacher")
	public String index() {
		return "index";
	}
	
	//��ת��δ��Ȩҳ��
	@RequestMapping("/user/unauthorized")
	public String toUnauthorized(){
		return "Unauthorized";
	}
	
	
	
	
}
