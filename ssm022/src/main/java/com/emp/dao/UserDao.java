package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emp.entity.User;

public interface UserDao {
	/**
	 *  通过用户名查找用户
	 *  @param username
	 *  @return User
	 */
	@Select("select id,username,password from sh_user where username=#{username}")
	public User queryByUserName(@Param("username")String username);
	
	/**
	 *  通过用户名查找该用户所有的角色并保存在Set集合中
	 *  返回值类型是Shiro框架定义的
	 *  @param username
	 *  @return Set<String>
	 */	
	@Select("select r.rolename from sh_role r inner join sh_user_role sr "
			+ "on r.id=sr.role_id inner join sh_user u "
			+ "on u.id=sr.user_id where u.username=#{username}")
	public Set<String> queryRoles(@Param("username")String username);
	
	/**
	 *  通过用户名查找该用户所有的权限并保存在Set集合中
	 *  @param username
	 *  @return Set<String>
	 */	 
	@Select("select DISTINCT p.permission_name from sh_user u inner join sh_user_role sr "
			+ "on sr.user_id=u.id inner join sh_role r "
			+ "on r.id=sr.role_id inner join sh_role_permission sp "
			+ "on sp.role_id=r.id inner join sh_permission p "
			+ "on p.id=sp.permission_id where u.username=#{username}")
	public Set<String> queryPermissions(@Param("username")String username);
	
	//注册  添加用户
	@Insert("insert into sh_user(username,password) values(#{username},#{password})")
	void save(User user);
	
}
