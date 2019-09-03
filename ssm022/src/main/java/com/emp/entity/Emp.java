package com.emp.entity;

/**
 * @author Administrator 员工类
 */
public class Emp {
	private String empno;// 员工编号
	private String ename;// 姓名
	private Integer eage;// 年龄
	private String esex;// 性别
	private Float esalary;// 薪资
	// private String deptno;// 部门编号
	private Dept dept; // 部门 关联属性

	// private String mgrno;// 经理编号

	private Emp mgr;// 经理 关联属性

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getEage() {
		return eage;
	}

	public void setEage(Integer eage) {
		this.eage = eage;
	}

	public String getEsex() {
		return esex;
	}

	public void setEsex(String esex) {
		this.esex = esex;
	}

	public Float getEsalary() {
		return esalary;
	}

	public void setEsalary(Float esalary) {
		this.esalary = esalary;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Emp getMgr() {
		return mgr;
	}

	public void setMgr(Emp mgr) {
		this.mgr = mgr;
	}

	public Emp(String empno, String ename, Integer eage, String esex, Float esalary, Dept dept, Emp mgr) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.eage = eage;
		this.esex = esex;
		this.esalary = esalary;
		this.dept = dept;
		this.mgr = mgr;
	}

	public Emp() {
		super();
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", eage=" + eage + ", esex=" + esex + ", esalary=" + esalary
				+ ", dept=" + dept + ", mgr=" + mgr + "]";
	}

}
