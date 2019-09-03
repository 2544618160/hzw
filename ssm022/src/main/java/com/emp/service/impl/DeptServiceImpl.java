package com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.DeptDao;
import com.emp.entity.Dept;
import com.emp.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService {
	@Resource
	private DeptDao deptDao;
	
	// 查询所有部门
	public List<Dept> queryAllDepts() {
		List<Dept> depts = deptDao.selectAll();
		return depts;
	}

}
