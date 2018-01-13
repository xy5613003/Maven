package com.xk.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Teacher {
	private int t_id;
	private String t_name;
	private String t_age;
	private Date t_hiretime;
	private int t_salary;
	private Set<Student> stus=new HashSet<>();
	
	public String getT_age() {
		return t_age;
	}
	public void setT_age(String t_age) {
		this.t_age = t_age;
	}
	public Set<Student> getStus() {
		return stus;
	}
	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
	public Teacher() {
	
	}
	public Teacher(int t_id, String t_name, String t_age, Date t_hiretime, int t_salary) {
		super();
		this.t_id = t_id;
		this.t_name = t_name;
		this.t_age =t_age;
		this.t_hiretime = t_hiretime;
		this.t_salary = t_salary;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	
	public Date getT_hiretime() {
		return t_hiretime;
	}
	public void setT_hiretime(Date t_hiretime) {
		this.t_hiretime = t_hiretime;
	}
	public int getT_salary() {
		return t_salary;
	}
	public void setT_salary(int t_salary) {
		this.t_salary = t_salary;
	}

}
