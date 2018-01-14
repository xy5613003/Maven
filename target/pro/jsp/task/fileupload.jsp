<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/tag.jsp"%>
<%@ include file="/jsp/common/common_js.jsp"%>
<%@ include file="/jsp/common/common_css.jsp"%>
<html>
<head>
<LINK rel="stylesheet" type="text/css"
	href="${baseurl}common/jquery-easyui-1.5.3/themes/styles/default.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
select {
	width: 93%;
	height: 25px;
}

.form_input {
	width: 99%
}
</style>
<title>文件提交</title>
<script type="text/javascript">
	$(function() {
		$("#sumit_btn").click(function() {
		 	var xhr_provider = function() {
				var xhr = jQuery.ajaxSettings.xhr();
				
				if (xhr.upload) {
					xhr.upload.addEventListener('progress', progressFunction, false);
				}
				return xhr;
			}; 
			var formdata = new FormData();
			formdata.append("file", $("#file")[0].files[0]);
			formdata.append("taskid", $("#taskid").val())
			$.ajax({
				url : "${baseurl}task/fileupload.action",
				type : "post",
				dataType : "json",
				data : formdata,
				cache : false,
				xhr : xhr_provider, 
				processData : false,
				contentType : false,
				async : true,
				success : function(data) {
					console.log(data);
					if (data.success) {
						parent.closemodalwindow("上传成功");
						parent.upload($("#taskid").val());
					} else {
						$.messager.alert("提示", "上传失败")
						$("#progressBar").progressbar("setValue","0")
					}
				},
				error : function(data, status, e) {
					$.messager.alert("提示", "网络错误")

				}
			}); 
		})

	})
	
	function progressFunction(evt) {
    var progressBar = document.getElementById("progressBar");
    var percentageDiv = $("#progressBar .progressbar-text");
    
    if (evt.lengthComputable) {
        var percentComplete=Math.round((evt.loaded)*100/evt.total);
        $("#progressBar").progressbar("setValue",percentComplete)
    }
    }
	function fileSelected(){
		var file=$("#file")[0].files[0];
		if(file){
			if(file.size>1024*1024){
				fileSize=(Math.round(file.size*100/(1024*1024))/100).toString()+"MB";
			}else{
				fileSize=(Math.round(file.size*100/1024)/100).toString()+"KB"
			}
			$("#fileName").text("文件名:"+file.name);
			$("#fileSize").text("大小:"+fileSize);
			
		}
	}
</script>
</head>
<body>


	<form id="taskform">
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" height="100%"
			bgColor=#c4d8ed>

			<TBODY>
				<TR height="50" align="center">
					<TD class="category" width="35%" colspan="3">
						<div style="width: 100%">
							<input type="hidden" name="taskid" id="taskid" value="${taskid }">
							请选择文件:<input type="file" name="file" id="file"
								accept="application/*" onchange="fileSelected()">
						</div>
					</TD>
				</TR>
				<tr align="center">
					<td class="category">
						<div id="progressBar" class="easyui-progressbar"
							data-options="value:0" style="width: 200px; margin-left: 89px;">
						</div>

					</td>
				</tr>
				<TR align="center">
					<td class="category">
						<div id="fileName" style="height: 21px;margin-left: 10px"></div>
					</td>
				</TR>
				<TR align="center">
					<td class="category" >
						<div id="fileSize" style="height: 21px;margin-left: 10px"></div>
					</td>
				</TR>

				<tr style="height: 70px">
					<td align="center" class="category"><a
						class="easyui-linkbutton" iconCls="icon-ok" href="#"
						id="sumit_btn">提交</a> <a id="closebtn" class="easyui-linkbutton"
						iconCls="icon-cancel" href="#" onclick="parent.closemodalwindow()">关闭</a></td>
				</tr>
			</TBODY>
		</TABLE>
	</form>
</body>
</html>