package com.emp.utils;

import java.util.List;

/**
 * @author MI
 * ��ҳ�������
 */
public class PageBean<E> {//����E�����������������
	private Integer pageNo=1;//��ǰҳ��
	private Integer pageSize=3;//ҳ����
	private List<E> list;//��ǰҳ������
	private Integer totalCount;//���з�ҳ���ܼ�¼��
	
	//��һҳ 
	public Integer getPrevious(){
		return this.pageNo==1?1:this.pageNo-1;
	}
	//��һҳ 
	public Integer getNext(){
		return this.pageNo==getLast()?getLast():this.pageNo+1;
	}
	
	//��ҳ  jspҳ��ȡֵ ${first}
	public Integer getFirst(){
		return 1;
	}
	//βҳ
	public Integer getLast(){
		return getTotalPages();
	}
	//��ҳ��
	public Integer getTotalPages(){
		return totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	
	//��ȡ��ǰҳ�� ${pageNo}
	//��jspҳ���ȡ���ֵ 
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}