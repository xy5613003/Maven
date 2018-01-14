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
		width:95%
	}
</style>
<title>添加用户</title>
<script type="text/javascript">
  function sysusersave(){
	  if($("#pm_username").val()==""){
		  $.messager.alert("提示","用户名不能为空");
		  return;
	  }
	 
	  if($("#pm_name").val()==""){
		  $.messager.alert("提示","姓名不能为空");
		  return;
	  }
	  var data=$("#userform").serializeJson();
	  console.log(data)
	  $.ajax({
		  url:"${baseurl}user/saveuser.action",
		  data:data,
		  type:"post",
		  dataType:"json", 
		  success:function(msg){
			  if(msg.success){
			  parent.closemodalwindow(msg.msg);
				  parent.queryuser(); 
				  
			  }else{
				  $.messager.alert("提示信息","添加失败,用户已存在,请直接登录","error")
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
										<input class="imput_text" type="text" id="pm_username" name="username"  />
									</div>
									<div id="pm_usernameTip"></div>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >真实姓名：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<input class="imput_text" type="text" id="pm_name" name="name"  />
									</div>
									<div id="pm_nameTip"></div>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >备注：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<textarea rows="3" cols="20" name="memo" class="imput_text"></textarea>
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