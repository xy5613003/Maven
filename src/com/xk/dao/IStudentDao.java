package com.xk.dao;

import java.util.List;

import com.xk.vo.Student;

public interface IStudentDao {

	int getCount();
	List<Student> queryAll(int row,int page);
}
