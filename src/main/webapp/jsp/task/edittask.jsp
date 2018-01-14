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
<title>添加用户</title>
<script type="text/javascript">
	function formValidator(formId){
		var val = $('#'+formId).val();
		if(val==''||val==null||val==undefined){
			return false;
		}
		return true;
	}
	function tasksave(){
		if(!formValidator('name')){
			  $.messager.alert('提示信息',"任务名称不能为空",'error');
			  return;
		}
		if(!formValidator('starttime')){
			  $.messager.alert('提示信息',"计划开始时间不能为空",'error');
			  return;
		}
		if(!formValidator('endtime')){
			  $.messager.alert('提示信息',"计划结束时间不能为空",'error');
			  return;
		}
		jquerySubByFId('taskform',tasksave_callback,null,"json");
	}
	//ajax调用的回调函数，ajax请求完成调用此函数，传入的参数是action返回的结果
	function tasksave_callback(data){
	 if(data.success){
	  $.messager.alert('提示信息',data.msg,'success');
	  parent.closemodalwindow();
	  parent.querytask();
	 }else{
	  $.messager.alert('提示信息',data.msg,'error');
	 }
	 //message_alert(data);
	}
</script>
</head>
<body>


<form id="taskform" action="${baseurl}task/savetask.action" method="post">
<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" height="100%" bgColor=#c4d8ed>

   <TBODY>
			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>
							<TR>
								<TD width="15%" align=right >任务名称：</TD>
								<TD class=category width="35%" colspan="3">
									<div style="width:100%">
										<input class="form_input" type="text" name="name" id="name" value="${task.name}"/>
										<input type="hidden" name="id" value="${task.id}"/>
									</div>
								</TD>
							</TR>
							<TR>
								<TD width="15%" align=right >计划开始时间：</TD>
								<TD class=category width="35%">
									<INPUT id="starttime"
											name="starttime" 
											class="Wdate"  
											 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
											  style="width:98%"
											   value="${task.starttime}"/>
								</TD>
								<TD width="15%" align=right >计划结束时间：</TD>
								<TD class=category width="35%">
									<INPUT id="endtime"
											name="endtime" 
											class="Wdate"  
											 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
											  style="width:98%"
											   value="${task.endtime}"/>
								</TD>
							</TR>
							<TR>
								<TD width="15%" align=right >责任人：</TD>
								<TD class=category width="35%">
									<select id="user" name="userid" class="form_input">
										<c:forEach items="${userList}" var="user">
											<option value="${user.id}" <c:if test="${user.id==task.userid}">selected="selected"</c:if>>${user.name}
											</option>
										</c:forEach>
									</select>
								</TD>
								<TD width="15%" align=right >所属项目：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<input class="form_input" type="text" name="project"  value="${task.projectname}" readOnly="readonly"/>
										<input type="hidden" name="projectid"  value="${task.projectid}"/>
									</div>
								</TD>
							</TR>
							<TR height="140">
								<TD width="15%" align=right >任务描述：</TD>
								<TD class=category width="35%" colspan="3">
									<div style="width:100%">
										<textarea rows="9" cols="20" name="description" class="form_input"/>${task.description}</textarea>
									</div>
								</TD>
							</TR>
							<tr>
							  <td colspan=4 align=center class=category>
								<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="tasksave()">提交</a>
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