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

/**
 * 完成部门Dao DeptDao的编写
 * 查询所有,依据编号查询,增删改
 */
public interface DeptDao {
	//查询所有
	@Select("select deptno,dname,location from t_dept")
	@Results(id="deptMapper",value={
			@Result(id=true,column="deptno",property="deptno"),
			@Result(column="dname",property="dname"),
			@Result(column="location",property="location")
	})
	List<Dept> selectAll();
	
	//依据编号查询
	@Select("select deptno,dname,location from t_dept "
			+ "where deptno=#{deptno}")
	@ResultMap("deptMapper")
	List<Dept> selectById(@Param("deptno")String deptno);
	
	@Insert("insert into t_dept values(#{deptno},#{dname},#{location})")
	void insert(Dept dept);
	
	@Delete("delete from t_dept where deptno=#{deptno}")
	void delete(String deptno);
	
	@Update("update t_dept set dname=#{dname},location=#{location} where deptno=#{deptno}")
	void Update(Dept dept);
	
	@Select("select distinct deptno,dname from t_dept")
	@ResultMap("deptMapper")
	List<Dept> selectDept();
}
