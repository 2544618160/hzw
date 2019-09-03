<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		width:250px;
		border:3px solid #ccc;
		border-collapse: collapse;
		margin:auto;
		margin-top:20px;
	}

</style>
</head>
<body>
<h2 align="center">添加员工</h2>
<!-- 使用spring表单标签,表单中必须要有modelAttribute这个属性 -->
<fm:form modelAttribute="emp" action="${pageContext.request.contextPath}/emp" method="POST">
	<p align="center">
		<!-- 所有错误信息都显示在这里 -->
		<fm:errors path="*" cssStyle="color:red"></fm:errors>
	</p>
	<fm:hidden path="empno"/>
	<table>
		<tr>
			<td>姓名</td>
			<td><fm:input path="ename"/></td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
			 <!-- <label>
				<input name="esex" type="radio" value="男" checked/>男
			</label>
			<label>
				<input name="esex" type="radio" value="女" />女
			</label> --> 
			<fm:radiobuttons path="esex" items="${map}"/>
			</td>
		</tr>
		<tr>
			<td>年龄</td>
			<td><fm:input path="eage"/>
			</td>
		</tr>
		<tr>
			<td>薪资</td>
			<td><fm:input path="esalary"/></td>
		</tr>
		<tr>
			<td>部门</td>
			<td>
				 <fm:select path="dept.deptno" items="${selecytDept}" itemLabel="dname"  itemValue="deptno"></fm:select>
				<!-- itemLabel:给用户看的 应该是dname
					 itemValue:提交给后台服务器的数据 应该是deptno
				 -->
			</td>
		</tr>
		<tr>
			<td>经理</td>
			<td>
			   <fm:select path="mgr.empno" items="${queryMgrs}" itemLabel="ename" itemValue="empno"></fm:select>
			</td> 
		</tr>
		<tr align="center">
               <td colspan="2">
                  <input type="submit"  value="保存"/>
                  <input type="button" value="重置" onclick="doReset"/>
               </td>
        </tr>
	<table>
</fm:form>
<script type="text/javascript">
	//性别默认选中男
	var ck=document.getElementById("esex2");
		ck.checked=true;
		function doReset(){
    	    //document.addForm.reset();
    	    document.addForm.ename.value="";
    	     var ck = document.getElementById("esex2");
             ck.checked = true;
    	    document.addForm.eage.value="";
    	    document.addForm.esalary.value="";
    	    document.addForm.deptno.value="d001";
    	    document.addForm.mgrno.value="e001";        	    
      }
</script>
</body>
</html>