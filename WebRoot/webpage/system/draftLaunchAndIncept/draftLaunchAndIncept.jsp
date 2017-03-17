<%-- 
 * 文件名称: draftLaunchAndIncept.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-8-31 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<form action="<%=basePath%>sysFreeFormatController.do?method=send" method="post" name="Form" id="Form" class="form-search">
				<div class="row-fluid">
					<label for="param_id" style="margin-top:10px;"><span id="mustfill" style="color:red">*</span>报文内容</label>
					<div  class="" >
						<textarea  name="drafttext" style="width: 600px;height:400px" onblur="checkIsEmpty();"  id="t1" valid="required"></textarea>
					</div>
				</div>
			</form>
			<div class="span6 center" id="btn-left" style="margin-top:20px;">
				<a class="btn-mini" id="submit" onclick="send();" >发送报文</a>
				<a class="btn-mini" id="fillBtn" onclick="receive();" >接收报文</a>
	       	</div>
		</div>	
	</div>	
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//检查
	function checkIsEmpty() {
		if (!$("#t1").valid()) {
			showTips("t1","内容不许为空!");
			return;
		}
	}
	//发送报文		
	function send() {
		var drafttext = $("#t1").val();
		$.ajax({
			url : "draftLaunchAndInceptController.do?method=send",
			data : {"drafttext" : drafttext},
			type : "POST",
			dataType:'json',
			cache: false,
			success: function(data){
				bootbox.alert(data.msg); 
			}
		});
	}
	//接收报文		
	function receive() {
		var drafttext = $("#t1").val();
		$.ajax({
			url : "draftLaunchAndInceptController.do?method=receive",
			data : {"drafttext" : drafttext},
			type : "POST",
			dataType:'json',
			cache: false,
			success: function(data){
				bootbox.alert(data.msg); 
			}
		});
	}
</script>			
</body>
</html>
