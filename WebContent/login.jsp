<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="jquery.easyui.min.js"></script>

<script type="text/javascript">
$(function(){
 	$("#username").focus(function(){
 		$("#error1_msg").text("");
 		$("#error2_msg").text("");
 	})
	$("#password").focus(function(){
 	
 		$("#error2_msg").text("");
 	})
 	$("#registerbn").click(function(){
 		window.location.href="register.jsp"
 		
 	})
})
function limit(){
	var username=$("#username").val();
	var password=$("#password").val();
	if(username==""){
		$("#error1_msg").text("用户名不能为空");
		return false;
	}
	if(password==""){
	 
		$("#error2_msg").text("密码不能为空");
		return false;
	}
}
       
</script>
</head>
<body>

	
<div class="easyui-dialog" data-options="iconCls:'icon-user'"
		style="text-align:center;width: 500px; height: 400px; padding: 20px" title="用户登录">
	<form action="userAction_login.action" method="post" onsubmit="return limit()">
	<div style="margin-top:100px">
	  
		<span style="color:red">${message }</span><br>
		账号:<input type="text" value="${username}" id="username" name="user.username" style="margin-right:5px"><font style="position:absolute;" color="red">*<span  id="error1_msg"></span></font><br><br>
		密码:<input type="password" value="${password}" id="password" name="user.password" style="margin-right:5px"><font style="position:absolute;" color="red">*<span   id="error2_msg" ></span></font><br>
		<input style="vertical-align: -3px" type="checkbox"  name="rem" id="rem">记住密码(30天)<br><br>
		<input type="submit" id="loginbn" value="登录">&nbsp;
		<input type="button" id="registerbn" value="注册">
		
	</div>
	</form>
</div>
</body>
</html>