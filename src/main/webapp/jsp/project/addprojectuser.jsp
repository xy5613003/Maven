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
	.btnr{
		text-align:center;
		vertical-align:middle;
	}
</style>
<script type="text/javascript">
	var projectId = '${projectId}';
	//datagrid列定义
	var columns_v = [ [{ field:'ck',checkbox:true }, {
		field : 'username',//对应json中的key
		title : '用户名',
		width : 120
	}, {
		field : 'name',//对应json中的key
		title : '真实姓名',
		width : 180
	}, {
		field : 'memo',//对应json中的key
		title : '备注',
		width : 120,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			return value;
		}
	} ] ];

	//加载datagrid
	$(function() {
		$('#projectuserlist').datagrid({
			title : '用户查询',//数据列表标题
			nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
			striped : true,//条纹显示效果
			url : '${baseurl}user/queryuser.action',//加载数据的连接，引连接请求过来是json数据
			idField : 'id',//此字段很重要，数据结果集的唯一约束(重要)，如果写错影响 获取当前选中行的方法执行
			loadMsg : '加载中...',
			columns : columns_v,
			height:300,
			pagination : true,//是否显示分页
			rownumbers : true,//是否显示行号
			pageList:[8,30,50],
			pageSize:8
		});
	});
	//查询方法
	function queryuser(){
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#projectuserqueryForm").serializeJson();
		$('#projectuserlist').datagrid('load',formdata);
	}
	function addprojectuser(){
		var records = $("#projectuserlist").datagrid("getSelections");
		if(records.length==0){
			$.messager.alert('提示信息','请选择人员！','warning');
			return;
		}
		var ids='';
		for(var i=0;i<records.length;i++){
			if(ids.length>0){
				ids += ',';
			}
			ids += records[i].id;
		}
		$.ajax({
			type:'get',
			url:'${baseurl}project/addprojectuser.action?userId='+ids+'&projectId='+projectId,
			dataType:'json',
			success:function(res){
				if(res.success){
					$.messager.alert('提示信息',res.msg,'success');
					parent.closemodalwindow();
					parent.queryproject();
				}else{
					$.messager.alert('提示信息',res.msg,'error');
				}
			}
		})
	}
</script>

</head>
<body>

	<!-- html的静态布局 -->
  <form id="projectuserqueryForm">
  
  <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
  	<TBODY>
		<TR>
			<TD class="left" width="60">姓名：</td>
			<TD width="100"><INPUT type="text" name="name" /></TD>
			<TD width="100" style="padding-left:10px;">
				<a id="btn" href="#" onclick="queryuser()"
					class="easyui-linkbutton" iconCls='icon-search'>查询</a>
			</TD>
			<td></td>
		</TR>
  		<tr>
  			<td colspan="4">
  				<table id="projectuserlist"></table>
  			</td>
  		</tr>
  		<tr>
  			<td colspan=4 class="category" style="text-align:center;padding-top:5px;">
				<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="addprojectuser()">添加</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a id="closebtn"  class="easyui-linkbutton" iconCls="icon-cancel" href="#" onclick="parent.closemodalwindow()">取消</a>
			 </td>
  		</tr>
  	</TBODY>
  </TABLE>
</form>
</body>
</html>