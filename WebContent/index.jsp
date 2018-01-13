<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="jquery.easyui.min.js"></script>
<script>
		function Opentabs(title,jsp){
			 if($("#tabs").tabs('exists',title)){//如果当前的选项卡存在 显示当前 已存在的选项卡
				$("#tabs").tabs('select',title)
			}else{
				
				var myContext = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="
					+jsp+"></iframe>"
					$('#tabs').tabs('add',{
						title: title,
						content: myContext,
						closable: true
					});
			}
			 
		}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="overflow:hidden;height:60px;background:#B3DFDA;padding:10px">
		
		<span style="float:right;margin:10px">欢迎${username}登录,您是今天第${count}个登录</span>
		

	</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
		<div id="aa" class="easyui-accordion" style="width:250px;" fit="true">
			<div title="学生管理" data-options="iconCls:'icon-user'" style="overflow:auto;padding:10px;">
			<a href="#" onclick="Opentabs('信息管理','emplist.jsp')" class="easyui-linkbutton" data-options="plain:true">账户信息管理</a>
			<a href="#" onclick="Opentabs('信息修改','emplist.jsp')" class="easyui-linkbutton" data-options="plain:true">账户信息修改</a>
			</div>
		</div>

	</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<div id="tabs"  class="easyui-tabs" data-options="tools:'#tab-tools'" fit="true">
		
		
		</div>
		
	
	
	</div>
</body>
</html>