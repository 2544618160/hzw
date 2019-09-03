package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.DeptDao;
import com.emp.dao.DeptLaztDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DeptDaoTest {
	@Resource
	private DeptDao deptDao;
	
	@Resource
	private DeptLaztDao deptLazyDao;
	
	@Test
	public void testSelectAll(){
		List<Dept> selectAll = deptDao.selectAll();
		System.out.println(selectAll);
	}
	
	@Test
	public void testSelectById(){
		List<Dept> selectAll = deptDao.selectById("d001");
		System.out.println(selectAll);
	}
	
	@Test
	public void testInsetr(){
		Dept d=new Dept();
		d.setDeptno("d005");
		d.setDname("公关部");
		d.setLocation("长沙");
		deptDao.insert(d);
		System.out.println("保存成功!");
	}
	
	@Test
	public void testDetele(){
		deptDao.delete("d009");
		System.out.println("删除成功!");
	}
	
	@Test
	public void testUpdate(){
		Dept d=new Dept();
		d.setDname("吴用");
		d.setLocation("梁山");
		d.setDeptno("d008");
		deptDao.Update(d);
		System.out.println("修改成功!");
	}
	
	@Test
	public void testQuertAll2(){
		List<Dept> depts = deptLazyDao.queryAll();
		for(Dept d:depts){
			System.out.println(d.getDname());
			List<Emp> list = d.getEmps();
			for(Emp s:list){
				System.out.println(d.getDeptno());
			}
		}
	}
	
	@Test
	public void testQuertAll3(){
		Dept dept = deptLazyDao.queryById("d001");
		System.out.println(dept.getDname());
	}
}
