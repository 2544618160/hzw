package com.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.Dept;
import com.emp.entity.Emp;
import com.emp.service.DeptService;
import com.emp.service.EmpService;
import com.emp.utils.PageBean;

@Controller
public class EmpController {
	@Resource
	private EmpService empService;
	
	@Resource
	private DeptService deptService;
	
	public void loadData(HttpSession session){
		Map<String,String> map=new HashMap<String,String>();
		map.put("��", "��");
		map.put("Ů", "Ů");
		session.setAttribute("map", map);
		// ${depts} ���в�������
		List<Dept> depts = deptService.queryAllDepts();
		session.setAttribute("depts", depts);
		//${mgrs} ���о���
		List<Emp> mgrs = empService.queryMgrs();
		session.setAttribute("mgrs", mgrs);
	}
	// ��ҳ��ѯ
	@RequestMapping("/emp/list")
	public String queryByPage(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize, Model model) {
		PageBean<Emp> pageBean = empService.queryByPage(pageNo, pageSize);
		model.addAttribute("pageBean", pageBean);
		return "ListEmp";
	}

	//������ҳ��ѯ
		@RequestMapping("/emp/conditionList")
		@RequiresPermissions(value={"emp:query","emp:del"},logical=Logical.OR)
		public String queryCondition(
			@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
		    @RequestParam(value="pageSize",required=false,defaultValue="3")Integer pageSize,
			@RequestParam(value="cd",required=false,defaultValue="")String cd,
			HttpSession session
				){
			   PageBean<Emp> pageBean = empService.queryByCondition(pageNo, pageSize, cd);
			   //��pageBean��cd ������������
			   session.setAttribute("pageBean", pageBean);
			   session.setAttribute("cd", cd);
			return "ListEmp";
		}
	
	//��ת�����Ա��ҳ��
		@RequestMapping("/emp/toAdd")
		//spring���Զ�����ģ������emp
		public String toAdd(@ModelAttribute("emp")Emp emp,Model model,HttpSession session){
			List<Dept> selecytDept = empService.selecytDept();
			List<Emp> queryMgrs = empService.queryMgrs();
			model.addAttribute("selecytDept", selecytDept);
			model.addAttribute("queryMgrs", queryMgrs);
			loadData(session);
			return "add";
		}
		
		//���Ա��
		@RequestMapping(value="/emp",method=RequestMethod.POST)
		public String adds1(@Valid Emp emp){
			//��֤ͨ�������Ա��
			String empno=UUID.randomUUID().toString();
			emp.setEmpno(empno);
			empService.addEmp(emp);
			return "redirect:/emp/conditionList";
		}
		
		/*ɾ��Ա��*/
		@RequestMapping(value="/emp/{empno}",method=RequestMethod.DELETE)
		public String deleteEmp(@PathVariable("empno")String empno){
			empService.deleteEmp(empno);
			return "redirect:/emp/conditionList";
		}
		
		//��ת���޸�ҳ��
		@RequestMapping("/emp/toUpdate")
		public String toUpdate(@ModelAttribute("emp")Emp emp,
				 @RequestParam("empno")String empno ,Model model,
				  HttpSession session){
			    loadData(session);//��������
			    emp = empService.queryEmpById(empno);
			    //��ģ�����Է��뵽��������
			    model.addAttribute("emp", emp);
			    return "updateEmp";
		}
		
		//�޸�Ա��
		@RequestMapping(value="/emp",method=RequestMethod.PUT)
		public String updateEmp(Emp emp,HttpSession session){
			  //�����޸���Ϣ�����ݿ���
			  empService.updateEmp(emp);
			  //��session��ȡ��pageBean�Ͳ�ѯ����cd 
			  PageBean<Emp> pageBean
			    = (PageBean<Emp>)session.getAttribute("pageBean");
			  String cd =(String)session.getAttribute("cd");
			  //����һ��pageBean��list
			  pageBean
			        = empService.queryByCondition(pageBean.getPageNo(),
					  pageBean.getPageSize(), cd);
			   //��pageBean�������·Ż�session��
			   session.setAttribute("pageBean", pageBean);
			  return "redirect:/emp/reList";
		}
		
		@RequestMapping("/emp/reList")
		public String toEmpList(){
			 return "ListEmp";
		}
		
		
}

