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
<title>查看任务</title>
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
										<input style="width:99%" type="text" name="name" id="name" value="${task.name}" readOnly="readonly"/>
										<input type="hidden" name="id" value="${task.id}"/>
									</div>
								</TD>
							</TR>
							<TR>
								<TD width="15%" align=right >计划开始时间：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<INPUT id="starttime" name="starttime" style="width:98%" value="${task.starttime}" readOnly="readonly"/>
									</div>
								</TD>
								<TD width="15%" align=right >计划结束时间：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<INPUT id="endtime" name="endtime" style="width:98%" value="${task.endtime}" readOnly="readonly"/>
									</div>
								</TD>
							</TR>
							<TR>
								<TD width="15%" align=right >责任人：</TD>
								<TD class=category width="35%">
									<select id="user" name="userid" style="width:100%" disabled="true">
										<c:forEach items="${userList}" var="user">
											<option value="${user.id}" <c:if test="${user.id==task.userid}">selected="selected"</c:if>>${user.name}
											</option>
										</c:forEach>
									</select>
								</TD>
								<TD width="15%" align=right >所属项目：</TD>
								<TD class=category width="35%">
									<div style="width:100%">
										<input  style="width:98%" type="text" name="project"  value="${task.projectname}" readOnly="readonly"/>
										<input type="hidden" name="projectid"  value="${task.projectid}"/>
									</div>
								</TD>
							</TR>
							<TR height="140">
								<TD width="15%" align=right >任务描述：</TD>
								<TD class=category width="35%" colspan="3">
									<div style="width:100%">
										<textarea rows="9" cols="20" name="description"  style="width:99%" readOnly="readonly"/>${task.description}</textarea>
									</div>
								</TD>
							</TR>
							<tr>
							  <td colspan=4 align=center class=category>
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