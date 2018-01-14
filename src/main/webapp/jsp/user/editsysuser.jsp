<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/tag.jsp"%>
<%@ include file="/jsp/common/common_js.jsp"%>
<%@ include file="/jsp/common/common_css.jsp"%>
<html>
<head>
<LINK rel="stylesheet" type="text/css" href="${baseurl}common/jquery-easyui-1.5.3/themes/styles/default.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	select{
		width:93%;
		height:25px;
	}
	.imput_text{
		width:90%
	}
</style>
<title>添加用户</title>
<script type="text/javascript">
	function sysusersave() {
		//准备使用jquery 提供的ajax Form提交方式
		//将form的id传入，方法自动将form中的数据组成成key/value数据，通过ajax提交，提交方法类型为form中定义的method，
		//使用ajax form提交时，不用指定url，url就是form中定义的action
		//此种方式和原始的post方式差不多，只不过使用了ajax方式
 	var data=$("#userform").serializeJson();
	  console.log(data)
	  $.ajax({
		  url:"${baseurl}user/saveuser.action",
		  data:data,
		  type:"post",
		  dataType:"json",
		  success:function(msg){
			  if(msg.success){
				  $.messager.alert("提示信息",msg.msg,"info")
				  parent.closemodalwindow();
				  parent.queryuser();
				  
			  }else{
				  $.messager.alert("提示信息",msg.msg,"error")
			  }
			  
		  },
		  error:function(data,status,e){
			  $.messager.alert("警告","网络错误")
		  }
		  
	  })
	

	}
	
</script>
</head>
<body>


<form id="userform" >
<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed>

   <TBODY>
			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>
							<TR>
								<TD height=30 width="15%" align=right >登录名：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<input class="imput_text" type="text" 
											id="pm_username" name="username" value="${user.username}"/>
										<input type="hidden" value="${user.id}" name="id">
										<input type="hidden" value="${user.pwd}" name="pwd">
									</div>
									<div id="pm_usernameTip"></div>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >真实姓名：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<input class="imput_text" type="text" 
											id="pm_name" name="name" value="${user.name}"/>
									</div>
									<div id="pm_nameTip"></div>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >备注：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<textarea rows="3" cols="20" name="memo" class="imput_text">${user.memo}</textarea>
									</div>
								</TD>
							</TR>
							<tr>
							  <td colspan=2 align=center class=category>
								<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="sysusersave()">提交</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a id="closebtn"  class="easyui-linkbutton" iconCls="icon-cancel" href="#" onclick="parent.closemodalwindow()">关闭</a>
							  </td>
							</tr>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
   </TBODY>
</TABLE>
</form>
</body>
</html>