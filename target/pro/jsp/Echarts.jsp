<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/common/tag.jsp"%>
<%@ include file="/jsp/common/common_js.jsp"%>
<%@ include file="/jsp/common/common_css.jsp"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../common/echarts.min.js"></script>
<script type="text/javascript">
	$(function(){
		var date=new Date();
		var month=date.getMonth()+1;
		queryEchart(month);
		
	})
	function clickquery(){
		
		var month=$("#month").val();
		queryEchart(month);
	}
	
	function queryEchart(month){
		var myChart = echarts.init(document.getElementById("chart"));
		var text=month+"月任务统计图";
		$.post("${baseurl}task/Echartdata.action",{"month":month},function(data){
			if(data.items.length==0){
				$.messager.alert("提示",month+"月份无数据")
				return;
			}
			var legend=new Array();
			//以下为图表设计
			myChart.setOption({
				    title : {
				        text:text,
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				    	 bottom: 0,
				        //orient : 'vertical',
				        x : 'center',
				        data:data.items
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {
				                show: true, 
				                type: ['pie', 'line','bar','funnel'],
				                option: {
				                    funnel: {
				                        x: '25%',
				                        width: '50%',
				                        funnelAlign: 'left',
				                        max: 1548
				                    }
				                }
				            },
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    series : [
				        {
				            name:'统计数量',
				            type:'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:data.series
				        }
				    ]
				})
			//以上为图表设计
		},'json')
	}
	
</script>
<title>Insert title here</title>
</head>
<body>
<!-- 查询条件 -->
		<TABLE width="99%">
			<TBODY>
				<TR>
				<td width="800"></td>
					<TD class="left" width="80">请选择月份：</td>
					<TD>
						<select id="month" name="month" style="WIDTH: 50px">
								<c:forEach begin="1" end="12" var="i" >
								<option value="${i}">${i}月</option>
								</c:forEach>
						</select>
					</TD>
					<TD width="10"></td>
					
					<TD width="200" style="padding-left:10px;">
						<a id="btn" href="#" onclick="clickquery()"
							class="easyui-linkbutton" iconCls='icon-search' plain="true">查询</a>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	<!-- 图标的div -->
	<div id="chart" style="width: 500px;height: 450px;position:relative;top:10px;left:25%"></div>
</body>
</html>