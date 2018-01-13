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
	map.clear();//json中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据
	
	int rows1=Integer.parseInt((rows==null||rows=="")?"5":rows);
	int page1=Integer.parseInt((page==null||page=="")?"1":page);
	System.out.println(rows1+"|"+page1);
		List<Student> list=new ArrayList<>();
		int total=studentService.Cout();
		
	list=studentService.queryAll(rows1, page1);
	//返回学生对应的老师(2表内联查询)有2种方案.1.在vo的学生对象中加入老师名称字段,然后遍历返回的学生list一个一个将老师名字加入
	//另外一个list 返回带有老师名字的学生list. 2.在前端页面(easyui为例)直接获取学生对象里的老师对象.从前端用formatter
	//方法拿到老师对象的属性.按道理第二种快啊..测试却是第一种快一些...数据量太少无法说明问题.
	
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
