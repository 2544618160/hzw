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
		 //���������㷨
		//�㷨  ��Ҫ���ܵ�����  �� ���ܵĴ���
        String password = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 1024).toString();
        //password���Ǽ��ܺ������
        //�ü��ܺ�������û�ԭ����ҳ�洫��������
        user.setPassword(password);
        //��user���浽���ݿ���
        userDao.save(user);
        
	}

	
	
	

}
