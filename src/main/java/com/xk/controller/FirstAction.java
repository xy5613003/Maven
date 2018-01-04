package com.xk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FirstAction {
	
	//首页
	@RequestMapping("/first")
	public ModelAndView first(Model model)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/first");
		return view;
	}
	
	//欢迎页面
	@RequestMapping("/welcome")
	public String welcome(){
		return "/jsp/welcome";
	}
	
}
