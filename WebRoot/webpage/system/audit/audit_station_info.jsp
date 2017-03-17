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
		    		<td>审批节点名称</td>
		    		<td>${anName}</td>
		    	</tr>
		    	<tr>
		    		<td>审批岗位名称</td>
		    		<td>${auditStation.asName}</td>
		    		<td>审批岗位额度</td>
		    		<td>${fns:formateMoney(auditStation.asPrivilege)}</td>
		    	</tr>
		    	<tr>
		    		<td>审批岗位描述</td>
		    		<td>${auditStation.asRemark}</td>
		    		<td colspan="2"></td>
		    	</tr>
		    </table>
		    <form>
				<input type="hidden" id="audit-result" name="audit-result" value=""/>
			</form>
		    <div class="row-fluid center" id="tableButton">
		    	<div id="fixed">
		    		<a class="btn-mini" onclick="toAssignStation();">分配审批人员</a>
		    		<a class="btn-mini" onclick="toEditStaion();">编辑岗位</a>
		    		<a class="btn-mini" onclick="delStation();">删除岗位</a>
		    	</div>
		    </div>
	  	</div>
  	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	function toAssignStation(){
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="分配审批人员";
	 	diag.URL = "<%=basePath%>auditRouteController.do?method=toAssignStation&asId="+"${auditStation.id}"+"&anId="+"${auditStation.anId}"+"&arId="+"${auditStation.arId}";
	 	diag.Width = 1200;
	 	diag.Height = 400;
	 	diag.CancelEvent = function(){ //关闭事件
		   	diag.close();
	 	};
	 	diag.show(); 
	}
	function toEditStaion(){
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="新增审批路线";
	 	diag.URL = "<%=basePath%>auditRouteController.do?method=toEditStaion&asId="+"${auditStation.id}";
	 	diag.Width = 400;
	 	diag.Height = 300;
	 	diag.CancelEvent = function(){ //关闭事件
	 		//var val=localStorage.getItem('value');
		 	if($("#audit-result").val()==1){
		 		modal("#layer_loading,#image");
			 	parent.location.href="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute&arId="+"${auditStation.arId}";
		 	}
		 	//localStorage.removeItem('value');
		 	diag.close();
	 	};
	 	diag.show(); 
	}
	function delStation(){
		bootbox.confirm("确定删除该岗位吗？", function(result) {
	   		if(result){
	   			$.ajax({
					type: "POST",
					url: "<%=basePath%>auditRouteController.do?method=delStation",
			    	data: {"asId":"${auditStation.id}",
			    			"arId":"${auditStation.arId}"},
					dataType:'json',
					cache: false,
					success: function(data){	
						if(data.success){  //处理成功
							parent.location.href="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute&arId="+"${auditStation.arId}";
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
