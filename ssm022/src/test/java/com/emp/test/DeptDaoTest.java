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
		d.setDname("���ز�");
		d.setLocation("��ɳ");
		deptDao.insert(d);
		System.out.println("����ɹ�!");
	}
	
	@Test
	public void testDetele(){
		deptDao.delete("d009");
		System.out.println("ɾ���ɹ�!");
	}
	
	@Test
	public void testUpdate(){
		Dept d=new Dept();
		d.setDname("����");
		d.setLocation("��ɽ");
		d.setDeptno("d008");
		deptDao.Update(d);
		System.out.println("�޸ĳɹ�!");
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
