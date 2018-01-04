package com.xk.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.xk.util.ResourcesUtil;
import com.xk.vo.User;


/**
 * 
 * @author YQ-YGQ
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		// 校验用户身份是否合法
		HttpSession session = request.getSession();
		User activeUser = (User) session.getAttribute("user");
		if (activeUser != null) {
			// 用户已经登陆，放行
			return true;
		}
		
		// 校验用户访问是否是公开资源 地址
		List<String> open_urls = ResourcesUtil.gekeyList("anonymousActions");

		// 用户访问的url
		String url = request.getRequestURI();
		System.out.println(url);
		for (String open_url : open_urls) {
			if (url.indexOf(open_url) >= 0) {
				// 如果访问的是公开 地址则放行
				return true;
			}
		}
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		response.sendRedirect(basePath+"jsp/login.action");

		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
