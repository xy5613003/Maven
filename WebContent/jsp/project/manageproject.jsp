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
	//treegrid列定义
	function updateRole(id,projectid,role){
		var tipMsg = '';
		if(role==2){ //表示要设置为组长，需要做验证，是否存在组长
			$.ajax({
				type:'get',
				url:'${baseurl}project/existsLeader.action?projectid='+projectid,
				dataType:'json',
				async: false,
				success:function(res){
					if(res.success){
						tipMsg = "该项目已经存在组长，需要替换吗？";
					}else{
						tipMsg = "确认要设置为组长吗？";
					}
				}
			})
		}
		if(role==0)tipMsg = '确认要设置为组员吗？';
		if(role==1)tipMsg = '确认要设置为测试吗？';
		$.messager.confirm("确认", tipMsg, function (r) {
	        if (r) {
	        	sendUpdateAjax(id,projectid,role);
	        }
	    });
	}
	function sendUpdateAjax(id,projectid,role){
		$.ajax({
			type:'get',
			url:'${baseurl}project/updateRole.action?id='+id+'&projectid='+projectid+'&role='+role,
			dataType:'json',
			success:function(res){
				if(res.success){
					$.messager.alert('提示信息',res.msg,'success');
					queryproject();
				}else{
					$.messager.alert('提示信息',res.msg,'error');
				}
			}
		})
	}
	var columns_v = [ [{ field:'ck',checkbox:true },{
		field : 'id',//对应json中的key
		title : 'id',
		hidden:true
	},{
		field : 'parent',//对应json中的key
		title : 'parent',
		hidden:true
	},{
		field : 'name',//对应json中的key
		title : '项目名',
		width : 180
	}, {
		field : 'memo',//对应json中的key
		title : '描述',
		width : 500,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			return value;
		}
	}, {
		field : 'role',//对应json中的key
		title : '角色',
		width : 250,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			if(value=="0")
				return "<img src='${baseurl}common/images/member.png' title='组员' />"+
						"<a href='javascript:;' style='font-size:14px;' onclick='updateRole(\""+row.id+"\",\""+row.parent+"\",2)'>【设置为组长】</a>"+
						"<a href='javascript:;' style='font-size:14px;' onclick='updateRole(\""+row.id+"\",\""+row.parent+"\",1)'>【设置为测试】</a>";
			if(value=="1")
				return "<img src='${baseurl}common/images/tester.png' title='测试' />"+
						"<a href='javascript:;' style='font-size:14px;' onclick='updateRole(\""+row.id+"\",\""+row.parent+"\",0)'>【设置为组员】</a>"+
						"<a href='javascript:;' style='font-size:14px;' onclick='updateRole(\""+row.id+"\",\""+row.parent+"\",2)'>【设置为组长】</a>";
			if(value=="2")
				return "<img src='${baseurl}common/images/leader.png' title='组长' />"+
						"<a href='javascript:;' style='font-size:14px;' onclick='updateRole(\""+row.id+"\",\""+row.parent+"\",0)'>【设置为组员】</a>"+
						"<a href='javascript:;' style='font-size:14px;' onclick='updateRole(\""+row.id+"\",\""+row.parent+"\",1)'>【设置为测试】</a>";
			return "";
		}
	} ] ];

	//定义 datagird工具
	var toolbar_v = [ {//工具栏
		id : 'btnadd',
		text : '添加',
		iconCls : 'icon-tianjia',
		handler : function() {
			createmodalwindow("添加项目", 400, 240, '${baseurl}project/toaddproject.action');
		}
	},{//工具栏
		id : 'btnedit',
		text : '修改',
		iconCls : 'icon-bianji',
		handler : function() {
			var records = $("#projectTreeGrid").treegrid("getSelections");
			if(records.length!=1){
				$.messager.alert('提示信息','请选择一条记录！','warning');
			}else{
				if(records[0].parent!=null){
					$.messager.alert('提示信息','请选择项目！','warning');
					return;
				}
				var id = records[0].id;
				createmodalwindow("修改项目", 400, 240, '${baseurl}project/toeditproject.action?id='+id);
			}
		}
	},{//工具栏
		id : 'btnremove',
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var records = $("#projectTreeGrid").treegrid("getSelections");
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
						$.ajax({
							type:'get',
							url:'${baseurl}project/deleteproject.action?ids='+ids,
							dataType:'json',
							success:function(res){
								if(res.success){
									$.messager.alert('提示信息',res.msg,'success');
									queryproject();
								}else{
									$.messager.alert('提示信息',res.msg,'error');
								}
							}
						})
			        }
			    });
			}
		}
	},{//工具栏
		text : '||'
	},{//工具栏
		id : 'btnedituser',
		text : '添加组员',
		iconCls : 'icon-bianji',
		handler : function() {
			var records = $("#projectTreeGrid").treegrid("getSelections");
			if(records.length!=1){
				$.messager.alert('提示信息','请选择一条记录！','warning');
				return;
			}
			if(records[0].role!=''&&records[0].role!=undefined&&records[0].role!=null){
				$.messager.alert('提示信息','请选择项目！','warning');
				return;
			}
			var projectId = records[0].id;
			createmodalwindow("添加组员", 600, 410, '${baseurl}project/toaddprojectuser.action?projectId='+projectId);
		}
	},{//工具栏
		text : '||'
	},{//工具栏
		id : 'refresh',
		text : '刷新',
		iconCls : 'icon-reload',
		handler : function() {
			queryproject();
		}
	} ];

	//加载treegrid

	$(function() {
		$('#projectTreeGrid').treegrid({
			title:'项目人员树',
			iconCls:'icon-save',
			height:600,
			singleSelect:false,
			nowrap: false,
			rownumbers: true,
			animate:true,
			collapsible:true,
			url : '${baseurl}project/queryprojecttree.action',//加载数据的连接，引连接请求过来是json数据
			idField:'id',
			treeField:'name',
		    columns:columns_v,
			toolbar : toolbar_v
		});
	});
	
	//查询方法
	function queryproject(){
		$('#projectTreeGrid').treegrid('clearSelections');
		var formdata = $("#projectqueryForm").serializeJson();
		$('#projectTreeGrid').treegrid('load',formdata);
	}
</script>

</head>
<body>
	  <form id="projectqueryForm">
			<!-- 查询条件 -->
			<TABLE class="table_search" width="99%">
				<TBODY>
					<TR>
						<TD class="left" width="100">项目名称：</td>
						<TD width="100"><INPUT type="text" name="name" /></TD>
						<TD width="100" style="padding-left:10px;">
							<a id="btn" href="#" onclick="queryproject()"
								class="easyui-linkbutton" iconCls='icon-search' plain="true">查询</a>
						</TD>
						<TD/>
					</TR>
				</TBODY>
			</TABLE>
		</form>
	<table id="projectTreeGrid"></table>
</body>
</html>