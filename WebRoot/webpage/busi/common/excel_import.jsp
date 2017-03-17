<%-- 
 * 文件名称: bank-fund.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%> 

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
		<%@ include file="/webpage/system/admin/footer.jsp"%>
		<script type="text/javascript" src="<%=basePath%>webpage/busi/common/transfChinese.js"></script>
	</head>
<body>
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form  action="excelImportController.do?method=doImport" name="Form" id="Form" method="post" class="form-search" enctype="multipart/form-data">
				<input type="hidden" name="batchId" value="<%=request.getParameter("batchId")%>"/>
				<input type="hidden" name="billType" value="<%=request.getParameter("billType")%>"/>
				<input type="hidden" name="serviceName" value="<%=request.getParameter("serviceName")%>"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="roleCode" class="text-right">导入文件</label>
						<div  class="col-xs-7" >
							<input type="file" name="file" id="file" valid="required" onchange="upload()"/>
						</div>
					</div>
					<div class="row-fluid" >
						<label for="roleName" class="text-right">模板文件</label>
						<div  class="col-xs-5" >	
							<a href="#">模板下载</a>
						</div>
					</div>
				</div>
				<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
			</form>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	//导入
	function upload(){
		if($("#file").val()==""){
			bootbox.alert("请选择导入模版");
			return;
		}
		Form.submit();
	}
</script>
</body>
</html>