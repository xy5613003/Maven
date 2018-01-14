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
	.form_input{
		width:99%
	}
</style>
<script type="text/javascript">
  function proposebug(){
	  var data=$("#bugform").serializeJson();
	  console.log(data)
	  $.ajax({
		  url:"${baseurl}task/proposebug.action",
		  data:data,
		  type:"post",
		  dataType:"json",
		  success:function(msg){
			  console.log(msg)
			  if(msg.success){
				  $.messager.alert("提示信息",msg.msg,"error")
				  parent.closemodalwindow();
				  parent.querytask();
				  
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
<title>bug查看</title>
</head>
<body>


<form id="bugform" >
<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" height="100%" bgColor=#c4d8ed>

   <TBODY>
			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>
							<TR height="220">
								<TD class=category width="35%" colspan="3">
									<div style="width:100%">
										<textarea rows="14" cols="20" name="bug" class="form_input">${bug}</textarea>
										<input type="hidden" name="id" value="${id}"/>
									</div>
								</TD>
							</TR>
							<tr>
							  <td colspan=2 align=center class=category>
								<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="proposebug()">提交</a>
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