<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/tag.jsp"%>
<%@ include file="/jsp/common/common_js.jsp"%>
<%@ include file="/jsp/common/common_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/favicon.ico" rel="icon" type="image/x-icon" />
<TITLE><fmt:message key="title" bundle="${messagesBundle}" /></TITLE>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<LINK rel="stylesheet" type="text/css"
	href="${baseurl}common/styles/style.css">
<LINK rel="stylesheet" type="text/css"
	href="${baseurl}common/styles/login.css">

<STYLE type="text/css">
.btnalink {
	cursor: hand;
	display: block;
	width: 80px;
	height: 29px;
	float: left;
	margin: 12px 28px 12px auto;
	line-height: 30px;
	background: url('${baseurl}common/images/login/btnbg.jpg') no-repeat;
	font-size: 14px;
	color: #fff;
	font-weight: bold;
	text-decoration: none;
}
</STYLE>

<script type="text/javascript">
	$(function() {
		//点击登录按钮绑定
		$("#loginBtn").on("click", function() {
			var data = {
				username : $("#username").val(),
				pwd : $("#password").val(),
				projectid:$("#project option:selected").val()
			}
			$.post("loginsubmit.action", data, function(msg) {
				var json = (new Function("return " + msg))()
				console.log(json);
				if (json.msg == "登录成功!") {
					tofirst();
				} else {
					$.messager.alert('提示',json.msg);    
				}
			})
		})
		//回车事件绑定
		$('#loginBody').bind(
				'keyup',
				function(event) {
					if (event.keyCode == "13" && $("#username").val() != ""
							&& $("#password").val() != "......") {
						//回车执行查询
						$('#submit_btn').click();
					}
				});
	})

	//回首页
	function tofirst(){
		//window.location='${baseurl}first.action';
		 if(parent.parent.parent){
			 //让最外层页面执行跳转
			parent.parent.parent.location='${baseurl}first.action';
		}else if(parent.parent){
			parent.parent.location='${baseurl}first.action';
		}else if(parent){
			parent.location='${baseurl}first.action';
		}else{
			window.location='${baseurl}first.action';
		}  
	  
	}
</SCRIPT>
</HEAD>
<BODY
	style="background: #f6fdff url(${baseurl}common/images/login/bg1.jpg) repeat-x;"
	id="loginBody">
	<FORM id="loginform" name="loginform"
		action="${baseurl}loginsubmit.action" method="post">
		<DIV class="logincon">

			<DIV class="title">
				<IMG alt="" src="${baseurl}common/images/login/logo.png">
			</DIV>

			<DIV class="cen_con">
				<IMG alt="" src="${baseurl}common/images/login/bg2.png">
			</DIV>

			<DIV class="tab_con">

				<input type="password" style="display: none;" />
				<TABLE class="tab" border="0" cellSpacing="6" cellPadding="8">
					<TBODY>
						<TR>
							<TD>用户名：</TD>
							<TD colSpan="2"><input type="text" id="username"
								name="username" style="WIDTH: 130px;" /></TD>
						</TR>
						<TR>
							<TD>密 码：</TD>
							<TD><input type="password" id="password" name="pwd"
								style="WIDTH: 130px" /></TD>
						</TR>
						<TR>
							<TD>项目：</TD>
							<TD><select id="project" name="projectid"
								style="WIDTH: 130px">
									<option value=""></option>
									<c:forEach items="${projectList}" var="pro">
										<option value="${pro.id}">${pro.name}</option>
									</c:forEach>
							</select></TD>
						</TR>
						<TR>
							<TD colSpan="2" align="center"><input type="button"
								id="loginBtn" class="btnalink" 
								value="登&nbsp;&nbsp;录" /> <input type="reset" class="btnalink"
								value="重&nbsp;&nbsp;置" /></TD>
						</TR>
					</TBODY>
				</TABLE>

			</DIV>
		</DIV>
	</FORM>
</BODY>
</HTML>
