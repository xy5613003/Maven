package com.xk.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.xk.dao.UserDaoimp;
import com.xk.util.Sessionfactoryutil;
import com.xk.vo.User;


public class UserService implements IUserservice {

	private UserDaoimp userDao;
	public UserDaoimp getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoimp userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean login(User user) {
		
		return userDao.login(user);
	}

	@Override
	public boolean register(User user) {
		
		return userDao.register(user);
	}

}
