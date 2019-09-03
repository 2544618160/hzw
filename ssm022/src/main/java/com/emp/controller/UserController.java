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
	
	//注册用户
	@RequestMapping("/user/register")
	public String register(User user){
		userService.addUser(user);
		//重定向登录页面
		return "redirect:/uset/toLogin";
	}
	
	@RequestMapping("/user/toLogin")
	public String tologin(User user) {
		return "Login";
	}
	
	@RequestMapping("/user/login")
	public String login(User user,@RequestParam(value="rememberMe",required=false,defaultValue="0")Integer rememberMe,Model model){
		//获取当前用户 Subject 主体 调用"/user/login"请求的东西(用户/程序)
		Subject subject=SecurityUtils.getSubject();
		//创建一个令牌对象
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try{
			//判断是否使用记住我
			if (rememberMe==1) {
				token.setRememberMe(true);
			}
			
			//为当前用户进行认证，授权
			subject.login(token);
			//subject.logout(); 登出
			return "redirect:/emp/conditionList";
			
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("msg", "用户名或密码错误！");
			return "Login";
		}
	}
	
	@RequestMapping("/teacher")
	public String index() {
		return "index";
	}
	
	//跳转到未授权页面
	@RequestMapping("/user/unauthorized")
	public String toUnauthorized(){
		return "Unauthorized";
	}
	
	
	
	
}
