package com.xk.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xk.service.Projectservice;
import com.xk.service.UserService;
import com.xk.util.PropertyManager;
import com.xk.vo.Project;
import com.xk.vo.ReturnObj;
import com.xk.vo.User;

@Controller
@RequestMapping("/jsp")
public class LoginContrller {
	@Resource
	private UserService userservice;
	@Resource
	private Projectservice projectservice;
	
	
	
	@RequestMapping("/login")
	public ModelAndView login() throws Exception {
		ModelAndView mv = new ModelAndView("/jsp/login");
		
		List<Project> list= projectservice.queryAllpro();
		mv.addObject("projectList", list);
		return mv;
	}
	@RequestMapping(value="/loginsubmit",produces="text/html;charset=utf-8;")
	@ResponseBody
	public ReturnObj login(HttpSession session, @ModelAttribute("user") User user) {
		PropertyManager p = new PropertyManager("admin.properties");
		String username=p.getPropertyStr("adminusername");
		String password=p.getPropertyStr("adminpassword");
		String projectid=user.getProjectid();
		User user1=null;
		ReturnObj ret=new ReturnObj();
		if(user.getUsername().equals(username)&&user.getPwd().equals(password)){  //先匹配是否是管理员，管理员账号和密码配置在admin.properties文件里
			user1 = new User();
			user1.setUsername("系统管理员");
			user1.setName("系统管理员");
			user1.setRole("3"); //角色为3的话表示管理员
		}else{  //非管理员的登录判断
			if(projectid==null||"".equals(projectid)){  //非管理员需要选择项目
				ret.setMsg("非管理员账号请选择项目！");
				ret.setSuccess(false);
				return ret;
			}
			// service，用户认证
			 user1 = userservice.login(user);  //通过username和projectid查询出用户
			if(user1==null){
				ret.setMsg("没有此账户！");
				ret.setSuccess(false);
				return ret;
			}else{
				if(!user.getPwd().equals(user1.getPwd())){
					ret.setMsg("密码错误！");
					ret.setSuccess(false);
					return ret;
				}
			}
			Project pro = projectservice.getByid(projectid);
			user1.setProjectname(pro.getName());
		}
		
		
		// 将用户身份信息写入session
		session.setAttribute("user", user1);
		
		ret.setMsg("登录成功!");
		ret.setSuccess(true);

		return ret;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		//session过期
		session.invalidate();
		return "redirect:login.action";
	}
}
