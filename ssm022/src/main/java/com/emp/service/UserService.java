package com.emp.service;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.emp.entity.User;

public interface UserService {
	/**
	 *  通过用户名查找用户
	 *  @param username
	 *  @return User
	 */
	public User queryByUserName(String username);
	
	/**
	 *  通过用户名查找该用户所有的角色并保存在Set集合中
	 *  @param username
	 *  @return Set<String>
	 */
	public Set<String> queryRoles(String username);
	
	/**
	 *  通过用户名查找该用户所有的权限并保存在Set集合中
	 *  @param username
	 *  @return Set<String>
	 */	
	public Set<String> queryPermissions(String username);
	
	//添加用户(注册使用)
	void addUser(User user);
}
