<%-- 
 * 文件名称:table.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-09-26
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
	<title>节点页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-style:'微软雅黑;'">
  <div class="clearfix">
		<div id="page-content" class="page-content">
		    <table class="table table-striped table-bordered table-display" cellpadding="0" cellspacing="0">
		    	<tr>
		    		<td>审批路线名称</td>
		    		<td>${auditRoute.arName}</td>
		    		<td>审批岗位执行模式</td>
		    		<td>${fns:getDictLabel('K_EXEC_MODE',auditRoute.anExecMode)}</td>
		    	</tr>
		    	<tr>
		    		<td>指定使用产品名称</td>
		    		<td>${auditRoute.prodNo}</td>
		    		<td>审批路线使用模式</td>
		    		<td>${auditRoute.pubFlag}</td>
		    	</tr>
		    	<tr>
		    		<td>审批起始机构级别</td>
		    		<td>${fns:getDictLabel('K_BRCH_LEVEL',auditRoute.auditStartBrchLvl)}</td>
		    		<td>指定使用机构名称</td>
		    		<td>${auditRoute.bindBranchNo}</td>
		    	</tr>
		    	<tr>
		    		<td>路线创建人所在机构</td>
		    		<td>${auditRoute.createBrchNo}</td>
		    		<td>创建日期</td>
		    		<td>${auditRoute.createDt}</td>
		    	</tr>
		    	<tr>
		    		<td>审批路线描述</td>
		    		<td>${auditRoute.arRemark}</td>
		    		<td colspan="2"></td>
		    	</tr>
		    </table>
		    <form>
				<input type="hidden" id="audit-result" name="audit-result" value=""/>
			</form>
		    <div class="row-fluid center" id="tableButton">
		    	<div id="fixed">
		    		<a class="btn-mini" onclick="toAddNode();">新增节点</a>
		    	</div>
		    </div>
	  	</div>
  	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	function toAddNode(){
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="新增审批节点";
	 	diag.URL = "<%=basePath%>auditRouteController.do?method=toAddNode&arId="+"${auditRoute.id}";
	 	diag.Width = 400;
	 	diag.Height = 220;
	 	diag.CancelEvent = function(){ //关闭事件
	 		//var val=localStorage.getItem('value');
		 	if($("#audit-result").val()==1){
		 		modal("#layer_loading,#image");
			 	parent.location.href="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute&arId="+"${auditRoute.id}";
		 	}
		 	//localStorage.removeItem('value');
		 	diag.close();
	 	};
	 	diag.show(); 
	}
</script>
</body>
</html>
