package com.emp.test;

import static org.junit.Assert.*;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.entity.User;
import com.emp.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserServiceTest {
	@Resource
	private UserService userService;
	
	@Test
	public void testQueryByUserName() {
		User queryByUserName = userService.queryByUserName("zs");
		System.out.println(queryByUserName);
	}

	@Test
	public void testQueryRoles() {
		Set<String> queryRoles = userService.queryRoles("zs");
		System.out.println(queryRoles);
	}

	@Test
	public void testQueryPermissions() {
		Set<String> queryPermissions = userService.queryPermissions("ls");
		System.out.println(queryPermissions);
	}

}
