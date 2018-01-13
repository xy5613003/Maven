package com.xk.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.xk.util.Sessionfactoryutil;
import com.xk.vo.Student;
import com.xk.vo.User;

public class UserDaoimp implements UserDao {

	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean login(User user) {
		System.out.println(hibernateTemplate + "hibernateTemplate本体login");
		String hql = "from User u where u.username=? and u.password=?";
		List<User> user1 = hibernateTemplate.find(hql, new String[] { user.getUsername(), user.getPassword() });
		if (user1 != null && user1.size() > 0) {
			System.out.println(user1.get(0).getUsername() + "dao层");
			return true;
		} else {
			return false;
		}
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean register(User user) {
		String hql = "from User u where u.username=?";

		List<User> user1 = hibernateTemplate.find(hql, user.getUsername());

		if (user1 != null && user1.size() > 0) {
			System.out.println("用户已存在注册失败" + "dao层");
			return false;
		} else {
			this.hibernateTemplate.save(user);
			return true;
		}

	}

	public boolean findByusername(String username) {
		String hql = "from User u where u.username=?";

		List<User> user1 = hibernateTemplate.find(hql, username);
		if (user1 != null && user1.size() > 0) {

			return false;
		} else {
			return true;
		}
	}

}
