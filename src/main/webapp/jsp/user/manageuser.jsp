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
<!-- 引用jquery easy ui的js库及css -->
<title>用户管理</title>
<style type="text/css">
	select{
		width:100%;
		height:25px;
	}
</style>
<script type="text/javascript">
	//datagrid列定义
	var columns_v = [ [{ field:'ck',checkbox:true }, {
		field : 'username',//对应json中的key
		title : '用户名',
		align : 'center',
		width : 180
	}, {
		field : 'name',//对应json中的key
		title : '真实姓名',
		align : 'center',
		width : 180
	}, {
		field : 'memo',//对应json中的key
		title : '备注',
		align : 'center',
		width : 500,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			return value;
		}
	} ] ];

	//定义 datagird工具
	var toolbar_v = [ {//工具栏
		id : 'btnadd',
		text : '添加',
		iconCls : 'icon-tianjia',
		handler : function() {
			//打开一个窗口，用户添加页面
			//参数：窗口的title、宽、高、url地址
			createmodalwindow("添加用户信息", 360, 240, '${baseurl}user/toadduser.action');
		}
	},{//工具栏
		id : 'btnedit',
		text : '修改',
		iconCls : 'icon-bianji',
		handler : function() {
			var records = $("#pmuserlist").datagrid("getSelections");
			if(records.length!=1){
				$.messager.alert('提示信息','请选择一条记录！','warning');
			}else{
				var id = records[0].id;
				createmodalwindow("修改用户信息", 360, 240, '${baseurl}user/toedituser.action?id='+id);
			}
		}
	},{//工具栏
		id : 'btnremove',
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var records = $("#pmuserlist").datagrid("getSelections");
			if(records.length==0){
				$.messager.alert('提示信息','请选择要删除的记录！','warning');
				return;
			}else{
			   $.messager.confirm("确认", "确认要删除所有选中的记录吗？", function (r) {
			        if (r) {
			        	var ids='';
						for(var i=0;i<records.length;i++){
							if(ids.length>0){
								ids += ',';
							}
							ids += records[i].id;
						}
						if(ids==''){
							$.messager.alert('提示信息','请选择要删除的记录！','warning');
							return;
						}
						$.ajax({
							type:'get',
							url:'${baseurl}user/deleteuser.action?ids='+ids,
							dataType:'json',
							success:function(res){
								if(res.success){
									$.messager.alert('提示信息',res.msg,'info');
									queryuser();
								}else{
									$.messager.alert('提示信息',res.msg,'error');
								}
							}
						})
			        }
			    });
			}
		}
	} ];

	//加载datagrid

	$(function() {
		$('#pmuserlist').datagrid({
			title : '用户列表',//数据列表标题
			nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
			striped : true,//条纹显示效果
			url : '${baseurl}user/queryuser.action',//加载数据的连接，引连接请求过来是json数据
			idField : 'id',//此字段很重要，数据结果集的唯一约束(重要)，如果写错影响 获取当前选中行的方法执行
			loadMsg : '加载中...',
			columns : columns_v,
			height:600,
			pagination : true,//是否显示分页
			rownumbers : true,//是否显示行号
			pageList:[15,30,50],
			pageSize:15,
			toolbar : toolbar_v
		});
	});
	
	//查询方法
	function queryuser(){
		$('#pmuserlist').datagrid('clearSelections');
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#pmuserqueryForm").serializeJson();
		$('#pmuserlist').datagrid('load',formdata);
	}
</script>

</head>
<body>

	<!-- html的静态布局 -->
  <form id="pmuserqueryForm">
	<!-- 查询条件 -->
	<TABLE class="table_search" width="99%">
		<TBODY>
			<TR>
				<TD class="left" width="100">姓名：</td>
				<TD width="100"><INPUT type="text" name="name" /></TD>
				<TD width="100" style="padding-left:10px;">
					<a id="btn" href="#" onclick="queryuser()"
						class="easyui-linkbutton" iconCls='icon-search'>查询</a>
				</TD>
				<TD/>
			</TR>
		</TBODY>
	</TABLE>

	<!-- 查询列表 -->
	<table id="pmuserlist"></table>
</form>
</body>
</html>