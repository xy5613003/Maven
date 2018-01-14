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
<script type="text/javascript">
	var columns_v = [ [{
		field : 'id',//对应json中的key
		title : 'id',
		align : 'center',
		hidden:true
	},{
		field : 'name',//对应json中的key
		title : '任务名',
		width : 300
	},{
		field : 'description',//对应json中的key
		title : '任务描述',
		align : 'center',
		width : 100,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			return "<a href='javascript:;' style='font-size:14px;' onclick='showDes(\""+row.id+"\")'>"+
					"<img src='${baseurl}common/images/view.png' title='任务描述' />"+
					"</a>";
		}
	},{
		field : 'user',//对应json中的key
		title : '责任人',
		align : 'center',
		width : 100
	}, {
		field : 'caozuo',//对应json中的key
		title : '操作',
		width : 250,
		align : 'center',
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			return "<a href='javascript:;' style='font-size:14px;' onclick='proposeBug(\""+row.id+"\")'>【提出bug】</a>"+
					"<a href='javascript:;' style='font-size:14px;' onclick='finishTask(\""+row.id+"\")'>【完成】</a>";
		}
	}] ];
	function showDes(id){
		createmodalwindow("任务描述", 600, 320, '${baseurl}task/showdes.action?id='+id);
	}
	function proposeBug(id){
		createmodalwindow("问题描述", 600, 320, '${baseurl}task/toproposebug.action?id='+id);
	}
	function finishTask(taskid){
		var url = '${baseurl}task/finishtask.action?id='+taskid;
		var	msg = "确认要完成任务吗？";
		$.messager.confirm("确认", msg, function (r) {
	        if (r) {
				$.ajax({
					type:'get',
					url:url,
					dataType:'json',
					success:function(res){
						if(res.success){
							$.messager.alert('提示信息',res.msg,'success');
							querytask();
						}else{
							$.messager.alert('提示信息',res.msg,'error');
						}
					}
				})
	        }
	    });
	}
	//查询方法
	function querytask(){
		$('#taskGrid').datagrid('clearSelections');
		var formdata = $("#taskqueryForm").serializeJson();
		$('#taskGrid').datagrid('load',formdata);
	}
	function cleanform(){
		$("#taskqueryForm")[0].reset();
	}
	//加载datagrid
	$(function() {
		var winHei = $(window).height();
		$('#taskGrid').datagrid({
			title : '任务列表',//数据列表标题
			nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
			striped : true,//条纹显示效果
			url : '${baseurl}task/querytestingtask.action',//加载数据的连接，引连接请求过来是json数据
			idField : 'id',//此字段很重要，数据结果集的唯一约束(重要)，如果写错影响 获取当前选中行的方法执行
			loadMsg : '加载中...',
			columns : columns_v,
			height:winHei-40,
			pagination : true,//是否显示分页
			rownumbers : true,//是否显示行号
			pageList:[15,30,50],
			pageSize:15
		});
		$("#taskGrid").datagrid({  
	        //双击事件  
	        onDblClickRow: function (index, row) {  
	        	var id = row.id;
				createmodalwindow("查看任务", 800, 325, '${baseurl}task/loadtask.action?action=view&id='+id);     
	        }  
	    }); 
	});

</script>

</head>
<body>
	<form id="taskqueryForm">
		<!-- 查询条件 -->
		<TABLE width="99%">
			<TBODY>
				<TR>
					<TD class="left" width="80">开始时间：</td>
					<TD width="250">
						<INPUT id="starttime"
								name="starttime" 
								class="Wdate" 
								 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
								 style="width:90%"/>
					</TD>
					<TD width="10"></td>
					
					<TD class="left" width="80">结束时间：</td>
					<TD width="250">
						<INPUT id="endtime"
								name="endtime" 
								class="Wdate"  
								 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
								 style="width:90%"/>
					</TD>
					<TD width="10"></td>
					
					<TD width="200" style="padding-left:10px;">
						<a id="btn" href="#" onclick="querytask()"
							class="easyui-linkbutton" iconCls='icon-search' plain="true">查询</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="btn1" href="#" onclick="cleanform()"
							class="easyui-linkbutton" iconCls='icon-no' plain="true">清空</a>
					</TD>
					<td></td>
				</TR>
			</TBODY>
		</TABLE>
	
		<!-- 查询列表 -->
		<table id="taskGrid"></table>
	</form>
</body>
</html>