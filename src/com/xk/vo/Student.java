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
	 * ����һ������lazy="false"������user�����������ء���
	 * 
	 * lazy="false">
	 * 
	 * ������ʱҪע����User��������û��������������ã��ù��У�ҲҪ����Ϊ��������ģʽ��
	 * 
	 * ������������set users ���ԣ����Ƽ�ʹ�ã�
	 * 
	 * �����ǰ̨ҳ�治��Ҫʹ�ø����ԵĻ����Ͳ�Ҫ��������ǰ̨ȥ�����÷���������getter�����ϼ�һע�⣺ @JSON(serialize=false)
	 * 
	 * @JSON(serialize=false) public Set getUsers() { return users; }
	 * 
	 * ����json�����ת������ʱ�ͻ���Ը����ԡ�
	 * 
	 * ��������Ӧ���Ѿ�����ˣ���
	 * 
	 * �������struts2��ajax�Ľ�ϻ��м��㽨�飺
	 * 
	 * 1����ҳ���ò�����������ò�Ҫ����ǰ̨����Ҳ��֮�����Ƽ��ڶ��ַ�����ԭ�򣬴�������Խ��Ч��Խ�Ͳ����𣡣�
	 * 
	 * 2��������ǰ̨�����ݵķ�����ò�Ҫ��get��ͷ��json����������get��ͷ�ķ����������ԣ�תΪjson��ʽ����
	 * 
	 * 3���������������get��ͷ��Ȼ���ֲ���Ϊ��תΪjson��ʽ����ô�����ڸ÷����ϼ�ע�⣺@JSON(serialize=false)
	 * 
	 * 4����Ҫ����ǰ̨�����ݣ�һ��Ҫ��dao�м�����ϣ�����ʹ��������ģʽ��
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
