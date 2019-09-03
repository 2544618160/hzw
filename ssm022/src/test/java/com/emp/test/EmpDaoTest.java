package com.emp.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpDao;
import com.emp.dao.EmpLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class EmpDaoTest {
	@Autowired
	private EmpDao empDao;
	@Test
	public void testQueryAll() {
		List<Emp> emps=empDao.queryAll();
		for(Emp e:emps){
			System.out.println(e);
		}
	}
	
	@Test
	public void testQuleryById(){
		Emp e = empDao.queryById("e001");
		System.out.println(e);
	}
	
	@Test
	public void testQuleryLike(){
		List<Emp> es=empDao.queryLikeName("李");
		for(Emp e:es){
			System.out.println(e);
		}
	}
	@Test//添加员工
	public void testAddEmp(){
		//创建一个员工对象
		Emp e=new Emp();
		e.setEmpno("e668");
		e.setEname("allen");
		e.setEsex("男");
		e.setEage(26);
		e.setEsalary(30000F);
		//创建部门对象
		Dept dept=new Dept();
		dept.setDeptno("d001");
		e.setDept(dept);
		//创建一个经理对象
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		e.setMgr(mgr);
		//将e对象保存到数据库中
		empDao.addEmp(e);
		System.out.println("保存成功!");
	}
	
	@Test//删除员工
	public void testDelete(){
		empDao.deleteEmp("e668");
		System.out.println("删除成功!");
	}
	
	@Test//修改员工
	public void testUpdate(){
		Emp e = empDao.queryById("e002");
		System.out.println(e);
		//e.setEname("lisi");
		//e.setEsex("公");
		//e.setEage(28);
		e.setEsalary(150000f);
		e.getDept().setDeptno("d002");
		e.getMgr().setEmpno("e004");
		
		empDao.updateEmp(e);
		System.out.println("修改成功!");
		empDao.queryById("e002");
	}
	
	@Test//增加
	public void testSelectAll(){
		List<Emp> list = empDao.selectAll("d001");
		for(Emp e:list){
			System.out.println(e);
		}
	}
	
	@Test//查询出所有的经理
	public void testQueryMgr(){
		List<Emp> queryMgrs = empDao.queryMgrs();
		for(Emp e:queryMgrs){
			System.out.println(e);
		}
	}
	
	@Resource
	private EmpLazyDao empLazyDao;
	
	@Test//测试懒加载
	public void testLazy(){
		Emp e = empLazyDao.queryById("e002");
		System.out.println(e.getEname());
		System.out.println("------------------");
		//System.out.println(e.getDept().getDname());
	}
	
	@Test// 查询所有员工
	public void testLazy2(){
		List<Emp> es = empLazyDao.queryAll();
		for(Emp e:es){
			if (e.getDept()!=null) {
				System.out.println(e.getEname()+e.getDept().getDname());
			}
		}
	}
	
	// 依据姓名模糊查询
	@Test
	public void testLazy3(){
		List<Emp> queryLikeName = empLazyDao.queryLikeName("熊");
		for(Emp e:queryLikeName){
				System.out.println(e.getEname());
		}
	}
	
	// 查询出所有的经理
	@Test
	public void testLazy4(){
		List<Emp> queryMgrs = empLazyDao.queryMgrs();
		for(Emp e:queryMgrs){
			System.out.println(e.getEname());
		}
	}
	
	// 依据部门编号查询部门下所有的员工
	@Test
	public void testLazy5(){
		List<Emp> selectAll = empLazyDao.selectAll("d002");
		for(Emp e:selectAll){
			System.out.println(e.getEname());
		}
	}
	
	
	
	
	

}
