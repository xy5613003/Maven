<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="title"></s:text></title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="jquery.easyui.min.js"></script>
<style type="text/css">
ul {
	list-style-type: none;
	float: left;
	display: inline-block;
	padding: 8px;
	margin: 5px;
}

li {
	margin: 0px 5px;
	text-align: left;
}

.msg_span {
	color: red;
	word-wrap: break-word;
	overflow: hidden;
	height: 20px;
	width: 180px;
	display: block;
	word-wrap: break-word;
}

#pic-input {
	position: absolute;
	left: 522px;
	top: 263px;
}

#pic-input>input {
	width: 180px;
}

#picshow {
	position: absolute;
	left: 546px;
	top: 100px;
	width: 150px;
	height: 150px
}
</style>
<script type="text/javascript">

	$(function() {
		$("#userpic").change(function() {

			$("#msg_file").text("");
			var formdata = new FormData();
			
			
			formdata.append("userpic", $("#userpic")[0].files[0]);
			$.ajax({
				url : "userAction1_fileUpload.action",
				type : "post",
				data : formdata,
				cache : false,
				processData : false,
				contentType : false,
				async : true,
				success : function(data) {
					console.log(data);
					if (data.fieldErrors != null) {
						$("#msg_file").text(data.fieldErrors.userpic);
					} else {
						/*  var json=(new Function("return " + data))(); */
						$("#picshow").attr("src", "/" + data.userpicFileName);
						$("#userpic1").attr("value", data.userpicFileName);
					}
				},
				error : function(data, status, e) {

					console.log(date);
					$("#msg_file").text("上传失败,请检查网络");
				}
			});

		})
		$("#submit_btn").click(function() {

			var data1 = {
				'user.username' : $("#username").val(),
				'user.password' : $("#password").val(),
				'repassword' : $("#repassword").val(),
				'user.authority' : $("#authority").combobox("getValue"),
				'user.userpic' : $("#userpic1").val(),
				'user.phone' : $("#phone").val(),
				'user.email' : $("#email").val()
			}
			console.log(data1);
			$.ajax({
				url : "userAction_register.action",
				type : "post",
				data : data1,
				success : function(data) {
					console.log(data);
					if (data!=null&&"注册成功" == data.message1) {
						window.location.href = "index.jsp";
					} else {
						$("#result").text("注册失败");
					}

				},
				error : function(data, status, e) {
					alert("ajax提交错误网络错误");
				}

			})
			
			$("#test1").text("${message}");
			$("#test2").text("<s\:property value=\"message\"");
		})

	})
</script>
</head>
<body>
vs:<span id="test1"></span>
el:<span id="test1"></span>
<s:fielderror cssStyle="FONT-WEIGHT: bold;color:yellow;font-size:14px;"></s:fielderror>
	<div class="easyui-dialog" data-options="iconCls:'icon-user'"
		style="margin-left: 10px; text-align: center; width: 768px; height: 400px; padding: 20px"
		title="用戶註冊">

		<form id="form_register" action="#" method="post">
			<s:debug />
			<div style="height: 197px">
				<ul>
					<li><s:text name="form.username"></s:text><input
						name="user.username" id="username" type="text"></li>
					<span class="msg_span"> <s:property
							value="fieldErrors['user.username'][0]" /></span>
					<li><lable>
						<s:text name="form.password"></s:text></lable><input name="user.password"
						id="password" type="password"></li>
					<span class="msg_span"> <s:property
							value="fieldErrors['user.password'][0]" /></span>
					<li style="margin-left: -1.5em"><lable> <s:text
							name="form.repassword"></s:text></lable><input name="repassword"
						id="repassword" type="password" /></li>
					<span class="msg_span"> <s:property
							value="fieldErrors['repassword'][0]" /></span>
					<li style="margin-left: -2em;"><label><s:text
								name="form.email"></s:text></label><input name="user.email" id="email" /></li>
					<span class="msg_span"> <s:property
							value="fieldErrors['user.email'][0]" /></span>
				</ul>

				<ul>
					<li><s:text name="form.phone"></s:text><input type="text"
						name="user.phone" id="phone"></li>
					<span class="msg_span"><s:property
							value="fieldErrors['user.phone'][0]" /></span>
					<li><s:text name="form.authority"></s:text><input type="text"
						name="user.authority" id="authority" class="easyui-combobox"
						editable="false" panelHeight="auto"
						data-options="
							valueField: 'authority',
							textField: 'value',
							data: [{
								authority: '1',
								value: '管理員'
							},{
								authority: '2',
								value: '操作員'
							},{
								authority: '3',
								value: '超級管理員'
							}]"></li>
					<span class="msg_span"> <s:property
							value="fieldErrors['user.authority'][0]" /></span>
				</ul>
			</div>
			<input id="userpic1" type="hidden" name="user.userpic" value="">
			<div style="clear: both;"></div>
			<div id="result" style="height:20px;color:red;"></div>
			<div style="text-align: center; margin: 20px 0px 0px -20px">
				<input id="submit_btn" type="button" value="确认注册"> <input
					id="clear_btn" type="reset" value="清除">
			</div>
		</form>




		<div id="pic-input">
			<s:text name="form.userpic"></s:text>
			<input id="userpic" accept="image/*" name="userpic" type="file">
			<span class="msg_span" id="msg_file"></span>
		</div>
		<img id="picshow" name="picshow">

	</div>

	<a href="changelanguage.action?request_locale=zh_CN">简体中文</a>&nbsp;
	<a href="changelanguage.action?request_locale=en_US">英文</a>

</body>
</html>