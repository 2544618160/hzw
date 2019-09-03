package com.emp.entity;

/**
 * @author Administrator Ա����
 */
public class Emp {
	private String empno;// Ա�����
	private String ename;// ����
	private Integer eage;// ����
	private String esex;// �Ա�
	private Float esalary;// н��
	// private String deptno;// ���ű��
	private Dept dept; // ���� ��������

	// private String mgrno;// ������

	private Emp mgr;// ���� ��������

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
