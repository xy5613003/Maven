package com.xk.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class Student {
	private int s_id;
	private String s_name;
	private String s_class;

	private Teacher s_teacherid;
	private Date s_date;
	private String s_education;
	private String teachername;

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public Date getS_date() {
		return s_date;
	}

	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_class() {
		return s_class;
	}

	public void setS_class(String s_class) {
		this.s_class = s_class;
	}

	// @JSON(serialize=false)
	/*
	 * 方法一：设置lazy="false"，即对user不采用懒加载。如
	 * 
	 * lazy="false">
	 * 
	 * 不过这时要注意在User对象中有没有其他对象的引用，用过有，也要设置为非懒加载模式。
	 * 
	 * 方法二：忽略set users 属性，（推荐使用）
	 * 
	 * 如果在前台页面不需要使用该属性的话，就不要把他传到前台去，设置方法是在其getter方法上加一注解： @JSON(serialize=false)
	 * 
	 * @JSON(serialize=false) public Set getUsers() { return users; }
	 * 
	 * 这样json插件在转换数据时就会忽略该属性。
	 * 
	 * 现在问题应该已经解决了！！
	 * 
	 * 下面关于struts2和ajax的结合还有几点建议：
	 * 
	 * 1、在页面用不到的数据最好不要传到前台（这也是之所以推荐第二种方法的原因，传的数据越大，效率越低不是吗！）
	 * 
	 * 2、不是向前台传数据的方法最好不要以get开头，json插件会把所有get开头的方法当做属性，转为json格式数据
	 * 
	 * 3、如果方法必须以get开头，然而又不是为了转为json格式，那么可以在该方法上加注解：@JSON(serialize=false)
	 * 
	 * 4、需要传到前台的数据，一定要在dao中加载完毕，不能使用懒加载模式。
	 */
	public Teacher getS_teacherid() {
		return s_teacherid;
	}

	public void setS_teacherid(Teacher s_teacherid) {
		this.s_teacherid = s_teacherid;
	}

	public String getS_education() {
		return s_education;
	}

	public void setS_education(String s_education) {
		this.s_education = s_education;
	}

}
