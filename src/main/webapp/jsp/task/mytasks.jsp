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
		field : 'jhst',//对应json中的key
		title : '计划开始时间',
		align : 'center',
		width : 140
	},{
		field : 'jhet',//对应json中的key
		title : '计划结束时间',
		align : 'center',
		width : 140
	},{
		field : 'sjst',//对应json中的key
		title : '实际开始时间',
		align : 'center',
		width : 140
	},{
		field : 'sjet',//对应json中的key
		title : '实际结束时间',
		align : 'center',
		width : 140
	},{
		field : 'state',//对应json中的key
		title : '状态',
		align : 'center',
		width : 120,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			if(value==0){
				return '初始中任务';
			}else if(value==1){
				return '进行中任务';
			}else if(value==2){
				return '测试中任务';
			}else if(value==3){
				return '修改中任务';
			}else if(value==4){
				return '已完成任务';
			}else{
				return '';
			}
		}
	}, {
		field : 'caozuo',//对应json中的key
		title : '操作',
		align : 'center',
		width : 250,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			if(row.state==0)
				return "<a href='javascript:;' style='font-size:14px;' onclick='doTask(\""+row.id+"\",0)'>【开始任务】</a>";
			if(row.state==1)
				return "<a href='javascript:;' style='font-size:14px;color:white' onclick='dotask(\""+row.id+"\",1)'>【提交任务】</a>";
			return "";
		}
	}] ];
	
	function dotask(taskid,flag){
		    createmodalwindow("上传文件", 400, 240, '${baseurl}task/tofileupload.action?taskid='+taskid); 
	}
	
	function upload(taskid){
		url = '${baseurl}task/submitTask.action?id='+taskid;
		msg = "确认要提交任务吗？";
		$.messager.confirm("确认", msg, function (r) {
	        if (r) {
				$.ajax({
					type:'get',
					url:url,
					dataType:'json',
					success:function(res){
						
						if(res.success){
							$.messager.alert('提示信息',res.msg,'info');
							querytask();
						}else{
							$.messager.alert('提示信息',res.msg,'error');
						}
					}
				})
	        }
	    });
	}
	function doTask(taskid,flag){
		
		var url = '${baseurl}task/startTask.action?id='+taskid;
		var msg = "确认要开始任务吗？";
		
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
		Date.prototype.format = function(format) {
			var args = {
				"M+" : this.getMonth() + 1,
				"d+" : this.getDate(),
				"h+" : this.getHours(),
				"m+" : this.getMinutes(),
				"s+" : this.getSeconds(),
				"q+" : Math.floor((this.getMonth() + 3) / 3), 
				"S" : this.getMilliseconds()
			};
			if (/(y+)/.test(format))
				format = format.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var i in args) {
				var n = args[i];
				if (new RegExp("(" + i + ")").test(format))
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n
							: ("00" + n).substr(("" + n).length));
			}
			return format;
		};
		$("#starttime").val(new Date().format("yyyy-MM-dd")+" 00:00:00");
		$("#endtime").val(new Date().format("yyyy-MM-dd")+" 23:59:59");
		
		var winHei = $(window).height();
		$('#taskGrid').datagrid({
			title : '任务列表',//数据列表标题
			nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
			striped : true,//条纹显示效果
			fitColumns:true,
			url : '${baseurl}task/querymytask.action',//加载数据的连接，引连接请求过来是json数据
			queryParams:{
				starttime:$("#starttime").val(),
				endtime:$("#endtime").val()
			},
			idField : 'id',//此字段很重要，数据结果集的唯一约束(重要)，如果写错影响 获取当前选中行的方法执行
			loadMsg : '加载中...',
			columns : columns_v,
			height:winHei-40,
			pagination : true,//是否显示分页
			rownumbers : true,//是否显示行号
			pageList:[15,30,50],
			pageSize:15,
			rowStyler: function(index,row){
				if(row.state == 4 && row.sjendtime > row.jhendtime){//超时完成，橘色
					$("#datagrid-row-r2-2-"+index+" td div").css('color','white');
					return 'background-color:orange;';
				}else if(row.state != 4 && new Date().getTime() > row.jhendtime){//超时未完成，红色
					$("#datagrid-row-r2-2-"+index+" td div").css('color','white');
					return 'background-color:red;';
				}else if(row.state == 1){//进行中，灰色
					$("#datagrid-row-r2-2-"+index+" td div").css('color','white');
					return 'background-color:gray;';
				}else if(row.state == 2){//测试中，浅绿色
					return 'background-color:cyan;';
				}else if(row.state == 3){//bug，黄色 修改中
					return 'background-color:yellow;';
				}else if(row.state == 4){//完成，绿色
					$("#datagrid-row-r2-2-"+index+" td div").css('color','white');
					return 'background-color:green;';
				}else{//完成，绿色
					return 'background-color:white;';
				}
			}
		});
		$("#taskGrid").datagrid({  
	        //双击事件  
	        onDblClickRow: function (index, row) {  
	        	var id = row.id;
				createmodalwindow("查看任务", 800, 345, '${baseurl}task/loadtask.action?action=view&id='+id);     
	        } //宽 ,高 设置...顺序 
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
					<TD>
						<INPUT id="starttime"
								name="starttime" 
								class="Wdate" 
								 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
								 style="width:90%"/>
					</TD>
					<TD width="10"></td>
					
					<TD class="left" width="80">结束时间：</td>
					<TD>
						<INPUT id="endtime"
								name="endtime" 
								class="Wdate"  
								 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
								 style="width:90%"/>
					</TD>
					<TD width="10"></td>
					
					<TD class="left" width="40">状态：</td>
					<TD>
						<select id="state" name="state" style="WIDTH: 200px">
								<option value="">全部</option>
								<option value="0">初始中任务</option>
								<option value="1">进行中任务</option>
								<option value="2">测试中任务</option>
								<option value="3">修改中任务</option>
								<option value="4">已完成任务</option>
								<option value="5">未完成任务</option>
								<option value="6">超时任务</option>
						</select>
					</TD>
					<TD width="10"></td>
					
					<TD width="200" style="padding-left:10px;">
						<a id="btn" href="#" onclick="querytask()"
							class="easyui-linkbutton" iconCls='icon-search' plain="true">查询</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="btn1" href="#" onclick="cleanform()"
							class="easyui-linkbutton" iconCls='icon-no' plain="true">清空</a>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	
		<!-- 查询列表 -->
		<table id="taskGrid"></table>
	</form>
</body>
</html>