<%-- 
 * 文件名称:disc_audit_detail_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-09-22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>票据管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 按钮操作区 --%>
			<div id="center">
				<form id="btnForm" name="btnForm" action="" method="post" enctype="multipart/form-data">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<input type="file" name="file" id="file" value="请选择文件" valid="required" onchange="upload()"/>
							<!-- <input type="text" value="请选择文件" id="fileValue" disabled style="background:#fafdfe !important"/> -->
						</div>
						<div class="span6" id="btn-right">
							<input type="hidden" name="batchId" value="<%=request.getParameter("batchId")%>"/>
							<input type="hidden" name="billType" value="<%=request.getParameter("billType")%>"/>
							<input type="hidden" name="serviceName" value="<%=request.getParameter("serviceName")%>"/>
							<c:if test="${not empty rs}"> 
								<a class="btn-mini pull-right" href="javascript:onSubmit();">下一步</a>
							</c:if>
					   	</div>
				  	</div>
				</form>
			</div>
			<div id="footer">
				<table class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:2600px;width:2600px"  id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<c:forEach items="${rs.titleList}" var="title" varStatus="vs">
								<th class="center">${title}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty rs}">
								<c:forEach items="${rs.successList}" var="list" varStatus="vs">
									<tr>
										<c:forEach items="${list}" var="data" varStatus="vs">
											<td class="center">${data}</td>
										</c:forEach>
									</tr>
								</c:forEach>
								<c:forEach items="${rs.failList}" var="list" varStatus="vs">
									<tr>
										<c:forEach items="${list}" var="data" varStatus="vs">
											<td class="center" style="background:red;">${data}</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</c:when>
						<c:otherwise>
							<tr>
								<td colspan="100" class="center">没有相关数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	/* $().ready(function(){
		var browser = navigator.appName;
		var trim_Version = version[1].replace(/[]/g,"");
		if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
			$("#fileValue").css({'position':'absolute','top':'10px','left':'5px','width':'150px','height':'25px','border':'none'});
			$("#file").css('margin-left','12px')
		}else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE8.0") {
			$("#fileValue").css({'position':'absolute','top':'5px','left':'0px','width':'150px','height':'25px','border':'none'});
			$("#file").css('margin-left','12px')
		}else{
			$("#fileValue").css({'width':'300px','position':'absolute','top':'5px','left':'75px','height':'25px','border':'none'});
		}
	}) */
	//提交或者取消操作
	function onSubmit(){
		$("#layer_loading,#image").show();
		$.ajax({
			url:"<%=basePath%>excelImportController.do?method=importResultHandle",
			data:$("#btnForm").serialize(),
			type:"POST",
			async:false,
			dataType:"JSON",
			success: function(data){
				$("#layer_loading,#image").hide();
  				if(data == "yes"){
  					alert("操作成功");
					top.Dialog.close();
				} else{
					bootbox.alert("操作失败,请重新操作");
				}
  			}
  		});
	}
	//导入
	function upload(){
		if (navigator.userAgent.search(/Trident/i)>=0) {
			var value = $("#file").val();
			value = value.substring(value.lastIndexOf("\\")+1,value.length);
		}else{
			var	value =  $("#file").val();
		}
		$("#fileValue").val(value);
		if( value.lastIndexOf(".xlsx") > 0 || value.lastIndexOf(".xls") > 0 ){
			$("#layer_loading,#image").show();
			$("#fileValue").val(value);
			btnForm.action="excelImportController.do?method=doImport";
			btnForm.submit();
			$('body').load(function(){
				$("#fileValue").val(value);
			})
		}else{
			bootbox.alert("请选择excel模版");
		}
	}
</script>
</body>
</html>