package com.xk.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class Registerinterceptor extends MethodFilterInterceptor{
private Map<String, String> map=new HashMap<String, String>();
private HttpServletResponse resp;
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		
		Map params=arg0.getInvocationContext().getParameters();
		String[] username= (String[]) params.get("user.username");
		
		
//if("".equals(username[0])) {
//	map.put("message", "�û�������Ϊ������������");
//	System.out.println(map.toString());
//	return "input";
//}
		System.out.println("����������");
	ValueStack vs=	ActionContext.getContext().getValueStack();
		vs.getContext().put("message", "vscontext��ֵ");
		vs.getRoot().add("���ǵ���");
		
		return Action.INPUT;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	

}
