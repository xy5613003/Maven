package com.xk.controller;

import java.util.Locale;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeLanguage extends ActionSupport{
	//��ȡĬ�����Ի���
	
	public String excute() {
		Locale locale=Locale.getDefault();
		//���������Ի�������session�� ww-Ϊ�̶�д��.
		ActionContext.getContext().getSession().put("WW-TRANS-I18N-LOCALE", locale);
		return SUCCESS;
	}
	

}
