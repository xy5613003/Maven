package com.xk.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.xk.service.StudentService;
import com.xk.vo.Student;

public class ServiceAction extends ActionSupport{
private StudentService studentService;
private String rows;
private String page;
private Map<String, Object> map;

public String queryAll() {
	map.clear();//json�е����ݽ��ᱻStruts2ת����JSON�ַ�������������Ҫ��������е�����
	
	int rows1=Integer.parseInt((rows==null||rows=="")?"5":rows);
	int page1=Integer.parseInt((page==null||page=="")?"1":page);
	System.out.println(rows1+"|"+page1);
		List<Student> list=new ArrayList<>();
		int total=studentService.Cout();
		
	list=studentService.queryAll(rows1, page1);
	//����ѧ����Ӧ����ʦ(2��������ѯ)��2�ַ���.1.��vo��ѧ�������м�����ʦ�����ֶ�,Ȼ��������ص�ѧ��listһ��һ������ʦ���ּ���
	//����һ��list ���ش�����ʦ���ֵ�ѧ��list. 2.��ǰ��ҳ��(easyuiΪ��)ֱ�ӻ�ȡѧ�����������ʦ����.��ǰ����formatter
	//�����õ���ʦ���������.������ڶ��ֿ찡..����ȴ�ǵ�һ�ֿ�һЩ...������̫���޷�˵������.
	
				map.put("rows",list);
				map.put("total",total);
    System.out.println(map);
    
	
	return "queryAll";
	
}


public String getRows() {
	return rows;
}



public String getPage() {
	return page;
}



public Map<String, Object> getMap() {
	return map;
}



public void setStudentService(StudentService studentService) {
	this.studentService = studentService;
}


public void setRows(String rows) {
	this.rows = rows;
}


public void setPage(String page) {
	this.page = page;
}


public void setMap(Map<String, Object> map) {
	this.map = map;
}


public StudentService getStudentService() {
	return studentService;
}


}
