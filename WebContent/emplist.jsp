<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="jquery.easyui.min.js"></script>
<script type="text/javascript" src="locale/easyui-lang-zh_CN.js"></script>

<style type="text/css">
ul {
	list-style-type: none;
	float: left;
	display: inline-block;
	padding: 12px;
}

li {
	margin: 20px 5px;
	text-align: left;
}
a {
	text-decoration: none
}
</style>

<script type="text/javascript">
	$(function() {
		$("#bg").datagrid({
							url : "studentAction_queryAll.action",
							fitColumns : true,
							pagination : true,
							rownumbers : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 100 ],
							title : "学生信息",
							toolbar : [{
								text:"刪除",
								iconCls:"icon-cut",
								handler:function(){
									var rows=$("#bg").datagrid("getSelections");
									if(rows.length>0){
										$.messager.confirm("警告","您確定要刪除所選条目嗎?",function(r){
											if(r){
												var obj = [];
												for (var i = 0; i < rows.length; i++) {
													obj[i] = rows[i].id;
												}
												var str = obj.join(); 
												$.post("MusicAction_delMusic.action", 
														{"delid":str}
													, function(flag) {
														if (flag) {
															$.messager.show({
																title : '删除',
																msg : '删除成功',
																timeout : 5000,
																showType : 'slide'
															});
														} else {
															$.messager.alert('删除', '删除信息失败', 'error');
														}
													}, "json")
											$("#bg").datagrid("reload");
											
											}
										})
									}else{
										$.messager.alert("提示","您未選擇,請選擇刪除一個或多個要刪除的歌曲","error");
									}
									
								}
								
								
							},{

								}],
							
							columns :[ [ {
								field : "s_id",
								title : "编号",
								width : 12,
								align : "center",
								checkbox : "true"
							}, {
								field : "s_name",
								title : "姓名",
								width : 12,
								align : "center"
							}, {
								field : "s_class",
								title : "班级",
								width : 12,
								align : "center"
							}, {
								field : "s_teacherid",
								title : "班主任",
								formatter:function(value,row,index){
									return	value.t_name
									},
								width : 12,
								align : "center"
							}, {
								field : "s_date",
								title : "出生日期",
								width : 12,
								align : "center",

							}, {
								field : "s_education",
								title : "学历",
								width : 12,
								align : "center"
							} ] ]
						});

	})
</script>
<script type="text/javascript">
	$(function() {
		$("#add_btn").click(function() {
			$("#add_window").dialog({
				closed : false
			})
			$("#s_teacherid").combobox("setValue", "请选择");

		})
		//点击确认添加按钮
		$("#submit_btn").unbind("click").click(
				function() {
					if ($("#s_name").val() == "" || $("#s_class").val() == ""
							|| $("#s_teacherid").combobox('getValue') == "请选择"
							|| $("#s_date").datebox('getValue') == ""
							|| $("#s_education").val() == "") {

						$.messager.alert('提示', '请填写所有项目', 'error');
						return;
					}
					var data = {
						s_name : $("#s_name").val(),
						s_class : $("#s_class").val(),
						s_teacherid : $("#s_teacherid").combobox('getValue'),
						s_date : $("#s_date").datebox('getValue'),
						s_education : $("#s_education").val()

					}

					$.post("studentservlet.action?m=addStu", data, function(
							flag) {

						if (flag) {
							$("#add_window").dialog({//添加窗口关闭
								closed : true
							})
							//清空form表单

							$("#form1").form("clear");

							//刷新当前数据
							$('#bg').datagrid('reload');
							//用提示
							$.messager.show({
								title : '添加',
								msg : '添加成功',
								timeout : 5000,
								showType : 'slide'
							});
						} else {
							$.messager.alert('添加', '添加信息失败', 'error');
						}
					}, "json")

				})
		//点击修改按钮 弹出修改的框
		$("#modi_btn").unbind("click").click(function() {
			//要 选择修改的行记录
			if ($("#bg").datagrid("getSelected") == null) {
				$.messager.alert('修改信息', '请选择要修改的学生', 'error');
			} else if ($("#bg").datagrid("getSelections").length > 1) {
				$.messager.alert('修改信息', '请选择一条记录进行修改', 'error');
				//取消选择所有页面的行。
				$("#bg").datagrid("unselectAll");
			} else {
				$("#modify_window").dialog({//修改窗口打开
					closed : false
				})
				//将选择的数据 填充到 修改的表单中
				var obj = $("#bg").datagrid("getSelected");
				console.log(obj)
				$("#s_id1").val(obj.s_id);
				$("#s_name1").val(obj.s_name);
				$("#s_class1").val(obj.s_class);
				$("#s_date1").datebox("setValue", obj.s_date);
				$("#s_education1").val(obj.s_education);
				$("#s_teacherid1").combobox("setValue", obj.s_teacherid);
			}
		})
		$("#modify_btn").unbind("click").click(function() {//修改事件

			var data = {//少了id
				s_id : $("#s_id1").val(),
				s_name : $("#s_name1").val(),
				s_class : $("#s_class1").val(),
				s_date : $("#s_date1").datebox('getValue'),
				s_teacherid : $("#s_teacherid1").combobox('getValue'),
				s_education : $("#s_education1").val()
			}

			$.post("studentservlet.action?m=editStu", data, function(flag) {
				if (flag) {
					$("#modify_window").dialog({//窗口关闭
						closed : true
					})
					//清空form表单
					$("#form2").form("clear");

					//刷新当前数据
					$('#bg').datagrid('reload');
					//用提示
					$.messager.show({
						title : '修改',
						msg : '修改成功',
						timeout : 5000,
						showType : 'slide'
					});
				} else {
					$.messager.alert('修改', '修改信息失败', 'error');
				}
			}, "json")
		})
		

	})
</script>
</head>
<body>

	<div id="bg"></div>
	<div id="toolbar">
		<a id="add_btn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add'" plain="true">添加</a> <a
			id="modi_btn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" plain="true">修改</a> <a
			id="del_btn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove'" plain="true">删除</a>
	</div>
	<!-- 添加窗口 -->
	<div id="add_window" closed="true" class="easyui-dialog" title="添加学生信息"
		style="width: 600px; height: 300px; padding: 10px"
		data-options="iconCls:'icon-user',resizable:true,modal:true">
		<form action="#" id="form1">
			<div style="height: 197px">
				<ul>
					<input type="hidden" id="s_id" />
					<li><lable>学生姓名:</lable><input name="s_name" id="s_name"
						type="text"></li>
					<li><lable>代课老师:</lable><input name="s_teacherid"
						id="s_teacherid" value="请选择"
						data-options="valueField:'t_id',textField:'t_name',url:''"
						panelHeight="auto" class="easyui-combobox" editable="false"></li>
					<li style="margin-left: 2em"><lable>学历：</lable><input
						name="s_education" id="s_education"
						data-options="iconCls:icon-pencil" /></li>
				</ul>
				<ul>
					<li>所属班级:<input id="s_class" name="s_class" type="text"></li>
					<li>入学日期:<input type="text" name="s_date" id="s_date"
						editable="false" class="easyui-datebox" panelHeight="auto"></li>
				</ul>
			</div>
		</form>
		<div style="text-align: center">
			<input id="submit_btn" type="button" data-options="iconCls:icon-ok"
				value="提交"> <input id="clear_btn" type="button"
				data-options="iconCls:icon-redo" value="清除">
		</div>

	</div>
	<!--修改窗口  -->
	<div id="modify_window" closed="true" class="easyui-dialog"
		title="修改学生信息" style="width: 600px; height: 300px; padding: 10px"
		data-options="iconCls:'icon-user',resizable:true,modal:true">
		<form action="#" id="form2">
			<div style="height: 197px">
				<ul>
					<input type="hidden" id="s_id1" />
					<li><lable>学生姓名:</lable><input name="s_name1" id="s_name1"
						type="text"></li>
					<li><lable>代课老师:</lable><input name="s_teacherid1"
						id="s_teacherid1" value="请选择"
						data-options="valueField:'t_id',textField:'t_name',url:''"
						panelHeight="auto" class="easyui-combobox" editable="false"></li>
					<li style="margin-left: 2em"><lable>学历：</lable><input
						name="s_education1" id="s_education1"
						data-options="iconCls:icon-pencil" /></li>
				</ul>
				<ul>
					<li>所属班级:<input id="s_class1" name="s_class1" type="text"></li>
					<li>入学日期:<input type="text" name="s_date1" id="s_date1"
						editable="false" class="easyui-datebox" panelHeight="auto"></li>
				</ul>
			</div>
		</form>
		<div style="text-align: center">
			<input id="modify_btn" type="button" data-options="iconCls:icon-ok"
				value="确认修改"> <input id="modifyclear_btn" type="button"
				data-options="iconCls:icon-redo" value="清除">
		</div>

	</div>


</body>
</html>