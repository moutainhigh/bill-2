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
		    		<td>${arName}</td>
		    		<td>节点所在机构级别</td>
		    		<td>${fns:getDictLabel('K_BRCH_LEVEL',auditNode.anBrchLvl)}</td>
		    	</tr>
		    	<tr>
		    		<td>审批节点名称</td>
		    		<td>${auditNode.anName}</td>
		    		<td colspan="2"></td>
		    	</tr>
		    </table>
		    <form>
				<input type="hidden" id="audit-result" name="audit-result" value=""/>
			</form>
		    <div class="row-fluid center" id="tableButton">
		    	<div id="fixed">
		    		<a class="btn-mini" onclick="toAddStation();">新增岗位</a>
		    		<a class="btn-mini" onclick="toEditNode();">编辑节点</a>
		    		<a class="btn-mini" onclick="delNode();">删除节点</a>
		    	</div>
		    </div>
	  	</div>
  	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	function toAddStation(){
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="新增审批岗位";
	 	diag.URL = "<%=basePath%>auditRouteController.do?method=toAddStation&anId="+"${auditNode.id}";
	 	diag.Width = 400;
	 	diag.Height = 300;
	 	diag.CancelEvent = function(){ //关闭事件
	 		//var val=localStorage.getItem('value');
		 	if($("#audit-result").val()==1){
		 		modal("#layer_loading,#image");
			 	parent.location.href="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute&arId="+"${auditNode.arId}";
		 	}
		 	//localStorage.removeItem('value');
		 	diag.close();
	 	};
	 	diag.show(); 
	}
	function toEditNode(){
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="编辑审批节点";
	 	diag.URL = "<%=basePath%>auditRouteController.do?method=toEditNode&anId="+"${auditNode.id}";
	 	diag.Width = 400;
	 	diag.Height = 220;
	 	diag.CancelEvent = function(){ //关闭事件
	 		//var val=localStorage.getItem('value');
		 	if($("#audit-result").val()==1){
		 		modal("#layer_loading,#image");
			 	parent.location.href="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute&arId="+"${auditNode.arId}";
		 	}
		 	//localStorage.removeItem('value');
		 	diag.close();
	 	};
	 	diag.show(); 
	}
	function delNode(){
		bootbox.confirm("确定删除该节点吗？", function(result) {
	   		if(result){
	   			$.ajax({
					type: "POST",
					url: "<%=basePath%>auditRouteController.do?method=delNode",
			    	data: {"anId":"${auditNode.id}",
			    			"arId":"${auditNode.arId}"},
					dataType:'json',
					cache: false,
					success: function(data){	
						if(data.success){  //处理成功
							modal("#layer_loading,#image");
						 	parent.location.href="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute&arId="+"${auditNode.arId}";
					 	}else{
							bootbox.alert(data.msg);
						}
					 }
				});
	   		 }
	   	});
	}
</script>
</body>
</html>
