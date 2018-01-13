package com.xk.controller;

import java.util.Locale;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeLanguage extends ActionSupport{
	//获取默认语言环境
	
	public String excute() {
		Locale locale=Locale.getDefault();
		//将本地语言环境存入session中 ww-为固定写法.
		ActionContext.getContext().getSession().put("WW-TRANS-I18N-LOCALE", locale);
		return SUCCESS;
	}
	

}
