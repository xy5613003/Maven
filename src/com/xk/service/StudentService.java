package com.xk.service;

import java.util.List;

import com.xk.dao.StudentDaoimp;
import com.xk.vo.Student;

public class StudentService implements IStudentService{
	private StudentDaoimp studentDao;
	@Override
	public List<Student> queryAll(int rows,int page) {
		return studentDao.queryAll(rows, page);
	}

	@Override
	public int Cout() {
		int count=studentDao.getCount();
		
		return count;
	}

	public StudentDaoimp getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDaoimp studentDao) {
		this.studentDao = studentDao;
	}

	
	
}
