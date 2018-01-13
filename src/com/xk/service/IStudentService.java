package com.xk.service;

import java.util.List;

import com.xk.vo.Student;

public interface IStudentService {
	List<Student> queryAll(int row,int page);
   int 	Cout();

}
