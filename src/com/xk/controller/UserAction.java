package com.xk.controller;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.xk.service.UserService;
import com.xk.vo.User;

public class UserAction extends ActionSupport{
private User user;
private UserService userService;
private String message;
private File userpic;
private String userpicFileName;
private Map<String, String> map;

public Map<String, String> getMap() {
	return map;
}
public void setMap(Map<String, String> map) {
	this.map = map;
}
public String login() {
	
	boolean is=userService.login(user);
	if (is) {
		message="登录成功";
		return "success";
	}else {
		message="*用户名或密码错误";
		return "login";
	}
}
public String register() {
	
	System.out.println("注册开始"+user.getUserpic()+" "+user.getUsername()+" "+user.getAuthority());
	boolean is=userService.register(user);
	System.out.println(is+"注到底有没有成功");
	if(!is) {
		map.put("message1", "用户名已存在,注册失败");
		 System.out.println(map.toString());
		 return "success";
	}
	map.put("message1", "注册成功");
	 System.out.println(map.toString());
	return "register";
}


public String fileUpload() {
	String savepath = "F:/javaworkspace/" + userpicFileName;
	
	System.out.println(userpicFileName);
	try {
		FileInputStream fis = new FileInputStream(userpic);
		FileOutputStream fos = new FileOutputStream(savepath);
		int n = 0;
		while ((n = fis.read()) != -1) {
			fos.write(n);
		}
		fis.close();
		fos.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    
	map.put("userpicFileName", userpicFileName);
     System.out.println(map.toString());
     user.setUserpic(userpicFileName);
	return "success";
}

public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public UserService getUserService() {
	return userService;
}
public void setUserService(UserService userService) {
	this.userService = userService;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
public File getUserpic() {
	return userpic;
}
public void setUserpic(File userpic) {
	this.userpic = userpic;
}
public String getUserpicFileName() {
	return userpicFileName;
}
public void setUserpicFileName(String userpicFileName) {
	this.userpicFileName = userpicFileName;
}

}
