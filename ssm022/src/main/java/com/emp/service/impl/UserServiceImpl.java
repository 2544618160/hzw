package com.emp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.emp.dao.UserDao;
import com.emp.entity.User;
import com.emp.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	
	public User queryByUserName(String username) {
		return userDao.queryByUserName(username);
	}

	public Set<String> queryRoles(String username) {
		return userDao.queryRoles(username);
	}

	public Set<String> queryPermissions(String username) {
		return userDao.queryPermissions(username);
	}

	public void addUser(User user) {
		 //加密密码算法
		//算法  需要加密的密码  盐 加密的次数
        String password = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 1024).toString();
        //password就是加密后的密码
        //用加密后的密码置换原来从页面传来的密码
        user.setPassword(password);
        //将user保存到数据库中
        userDao.save(user);
        
	}

	
	
	

}
