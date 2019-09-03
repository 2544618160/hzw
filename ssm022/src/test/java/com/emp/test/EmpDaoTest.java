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
		List<Emp> es=empDao.queryLikeName("��");
		for(Emp e:es){
			System.out.println(e);
		}
	}
	@Test//���Ա��
	public void testAddEmp(){
		//����һ��Ա������
		Emp e=new Emp();
		e.setEmpno("e668");
		e.setEname("allen");
		e.setEsex("��");
		e.setEage(26);
		e.setEsalary(30000F);
		//�������Ŷ���
		Dept dept=new Dept();
		dept.setDeptno("d001");
		e.setDept(dept);
		//����һ���������
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		e.setMgr(mgr);
		//��e���󱣴浽���ݿ���
		empDao.addEmp(e);
		System.out.println("����ɹ�!");
	}
	
	@Test//ɾ��Ա��
	public void testDelete(){
		empDao.deleteEmp("e668");
		System.out.println("ɾ���ɹ�!");
	}
	
	@Test//�޸�Ա��
	public void testUpdate(){
		Emp e = empDao.queryById("e002");
		System.out.println(e);
		//e.setEname("lisi");
		//e.setEsex("��");
		//e.setEage(28);
		e.setEsalary(150000f);
		e.getDept().setDeptno("d002");
		e.getMgr().setEmpno("e004");
		
		empDao.updateEmp(e);
		System.out.println("�޸ĳɹ�!");
		empDao.queryById("e002");
	}
	
	@Test//����
	public void testSelectAll(){
		List<Emp> list = empDao.selectAll("d001");
		for(Emp e:list){
			System.out.println(e);
		}
	}
	
	@Test//��ѯ�����еľ���
	public void testQueryMgr(){
		List<Emp> queryMgrs = empDao.queryMgrs();
		for(Emp e:queryMgrs){
			System.out.println(e);
		}
	}
	
	@Resource
	private EmpLazyDao empLazyDao;
	
	@Test//����������
	public void testLazy(){
		Emp e = empLazyDao.queryById("e002");
		System.out.println(e.getEname());
		System.out.println("------------------");
		//System.out.println(e.getDept().getDname());
	}
	
	@Test// ��ѯ����Ա��
	public void testLazy2(){
		List<Emp> es = empLazyDao.queryAll();
		for(Emp e:es){
			if (e.getDept()!=null) {
				System.out.println(e.getEname()+e.getDept().getDname());
			}
		}
	}
	
	// ��������ģ����ѯ
	@Test
	public void testLazy3(){
		List<Emp> queryLikeName = empLazyDao.queryLikeName("��");
		for(Emp e:queryLikeName){
				System.out.println(e.getEname());
		}
	}
	
	// ��ѯ�����еľ���
	@Test
	public void testLazy4(){
		List<Emp> queryMgrs = empLazyDao.queryMgrs();
		for(Emp e:queryMgrs){
			System.out.println(e.getEname());
		}
	}
	
	// ���ݲ��ű�Ų�ѯ���������е�Ա��
	@Test
	public void testLazy5(){
		List<Emp> selectAll = empLazyDao.selectAll("d002");
		for(Emp e:selectAll){
			System.out.println(e.getEname());
		}
	}
	
	
	
	
	

}
