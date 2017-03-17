<%-- 
 * 文件名称: ecdsimport.jsp
 * 系统名称: 票据管理平台
 * 模块名称: ecds基础数据入
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: lijiangtao
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%>
	</head>
<body style="font-family:'微软雅黑';">
<div class="clearfix">
		<div id="page-content" class="page-content">
	<div id="center">
		<div class="row-fluid">
			<div class="span6" id="btn-left" >
				<a class="btn-mini" href="javascript:importecdsfile();">导入</a>
			</div>
			<div class="span6" id="btn-right">
			</div>
		</div>
	</div>
    <div id="footer">
		<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="center" onclick="selectAll('allcheck', 'ecdsname')">
						<input type="checkbox" id="allcheck" />
					</th>
					<th class="center">文件名</th>
				</tr>
			</thead>
			<tbody id="dataBody">
			 <c:choose>
					<c:when test="${not empty ecdspathList}">
						<c:forEach items="${ecdspathList}" var="ecdspathList" varStatus="vs">
				<tr>
					<td class="center">
						<input type='checkbox' name='ecdsname' value="${ecdspathList}" />
					</td>
					<td class="center">${ecdspathList}</td>
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
	<form  action="#" name="dataCollectForm" method="post"></form>
	 <%-- /列表操作区 --%>
	 <div>
		<div id="page" class="pagination">
			<form action="<%=basePath%>ecdsDataImportController.do?method=mainview"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
			</form>
		</div>
	</div>
</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>	
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
function importecdsfile(){
	var checkNum = getCheckNum("ecdsname");
  	 if (checkNum !=1){
  		bootbox.alert("请选择一条文件路径信息");
  		return;
  	 }
  	var ecdsname = getCheckStr("ecdsname");	
  	modal("#layer_loading,#image");
  	$.ajax({
  	 	url:"<%=basePath%>ecdsDataImportController.do?method=importecdsdata",
  	 	data:{'filenames':ecdsname},
  	 	type:"post",
  	 	dataType:'JSON',
  	 	success:function(data){
		  dataCollectForm.action = "<%=basePath%>ecdsDataImportController.do?method=mainview";
		  dataCollectForm.submit();
   	 	},
  	 	error:function(data){
  	 	dataCollectForm.action = "<%=basePath%>ecdsDataImportController.do?method=mainview";
		  dataCollectForm.submit();
  	 	}
  	 });
}
</script>
</body>
</html>