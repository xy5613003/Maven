package com.xk.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Sessionfactoryutil {

	private static SessionFactory sf;
	private Sessionfactoryutil() {
		
	}
	public static SessionFactory getSessionfactory() {
		if(sf==null) {
			Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			sf=cfg.buildSessionFactory();
		}
	
		return sf;
		
	}
}
