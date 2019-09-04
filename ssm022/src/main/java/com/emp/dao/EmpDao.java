package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.Dept;
import com.emp.entity.Emp;

public interface EmpDao {
	//��ѯ����Ա��
	@Select("select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+ " d.deptno,d.dname,d.location, "
			+ " e.mgrno mno,m.ename mname "
			+ " from t_emp e left join t_dept d "
			+ " on e.deptno = d.deptno "
			+ " left join t_emp m "
			+ " on e.mgrno = m.empno")
	
	@Results(id="empMapper",value={
			@Result(id=true,column="empno",property="empno"),
			@Result(column="ename",property="ename"),
			@Result(column="esex",property="esex"),
			@Result(column="eage",property="eage"),
			@Result(column="esalary",property="esalary"),
			@Result(column="deptno",property="dept.deptno"),
			@Result(column="dname",property="dept.dname"),
			@Result(column="location",property="dept.location"),
			@Result(column="mno",property="mgr.empno"),
			@Result(column="mname",property="mgr.ename")
	})
	List<Emp> queryAll();
	//����Ա����Ų�ѯԱ��
	@Select(" select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+" d.deptno,d.dname,d.location, "
			+" e.mgrno mno,m.ename mname "
			+" from t_emp e left join t_dept d "
			+" on e.deptno = d.deptno "
			+" left join t_emp m "
			+" on e.mgrno = m.empno "
			+ "where e.empno=#{empno}")
	@ResultMap(value="empMapper")
	Emp queryById(@Param("empno") String empne);
	
	//��������ģ����ѯ
	@Select(" select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+" d.deptno,d.dname,d.location, "
			+" e.mgrno mno,m.ename mname "
			+" from t_emp e left join t_dept d "
			+" on e.deptno = d.deptno "
			+" left join t_emp m "
			+" on e.mgrno = m.empno "
			//+ "where e.ename like CONCAT('%',#{ename},'%')")
			+ "where instr(e.ename,#{ename})>0")
	
	@ResultMap(value="empMapper")
	List<Emp> queryLikeName(@Param("ename")String ename);
	
	
	//����Ա��
	@Insert("insert into t_emp values(#{empno},#{ename},#{esex},#{eage},#{esalary}, "
			+ "#{dept.deptno},#{mgr.empno})")
	void addEmp(Emp emp);
	
	//�޸�Ա��
	@Update("update t_emp set ename=#{ename},esex=#{esex},eage=#{eage},esalary=#{esalary}, "
			+ "deptno=#{dept.deptno},mgrno=#{mgr.empno} "
			+ "where empno=#{empno}")
	void updateEmp(Emp emp);
	
	//���ݱ��ɾ��Ա��
	@Delete("delete from t_emp where empno=#{empno}")
	void deleteEmp(@Param("empno")String empno);
	
	//���ݲ��ű�Ų�ѯ���������е�Ա��
	@Select(" select e.empno,e.ename,e.esex,e.eage,e.esalary, "
			+" d.deptno,d.dname,d.location, "
			+" e.mgrno mno,m.ename mname "
			+" from t_emp e left join t_dept d "
			+" on e.deptno = d.deptno "
			+" left join t_emp m "
			+" on e.mgrno = m.empno "
			+ "where e.deptno=#{deptno}")
	List<Emp> selectAll(@Param("deptno")String deptno);
	
	//��ѯ�����еľ���
	@Select("select distinct m.empno,m.ename "
			+ "from t_emp m inner join t_emp e "
			+ "on m.empno=e.mgrno "
			+ "where m.deptno is not null")
	List<Emp> queryMgrs();
	
	
}