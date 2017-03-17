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
<body>
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="<%=basePath%>auditRouteController.do?method=saveAuditStation" method="post" name="Form" id="Form" class="form-search" role="form">
				<input type="hidden" name="id" value="${auditStation.id}"/>
				<input type="hidden" name="isEdit" value="${isEdit}"/>
				<div class="row-fluid" >
				 	<label for="param_value" class="pdTop"><span class="star">*</span>审批路线名称</label>
					<input type="text" class="input-medium" id="arName"  valid="required" value="${arName}" name="arName" readonly placeholder=""/>
					<input type="hidden" name="arId" value="${auditStation.arId}"/>
				</div>
				<div class="row-fluid" >	
					<label for="param_value" class="pdTop"><span class="star">*</span>审批节点名称</label>
					<input type="text" class="input-medium" id="anName"  valid="required" value="${anName}" name="anName" readonly placeholder=""/>
					<input type="hidden" name="anId" value="${auditStation.anId}"/>
				</div>
				<div class="row-fluid" >	
					<label for="param_value" class="pdTop"><span class="star">*</span>审批岗位名称</label>
					<input type="text" class="input-medium" id="asName"  valid="required" value="${auditStation.asName}" name="asName" placeholder=""/>
				</div>
				<div class="row-fluid" >	
					<label for="param_value" class="pdTop"><span class="star">*</span>审批岗位额度</label>
					<input type="text" class="input-medium" id="asPrivilege"  valid="required" value="${auditStation.asPrivilege}" name="asPrivilege" placeholder=""/>
				</div>
				<div class="row-fluid" >	
					<label for="param_value" class="pdTop"><span class="star">*</span>审批岗位描述</label>
					<input type="text" class="input-medium" id="asRemark"  valid="required" value="${auditStation.asRemark}" name="asRemark" placeholder=""/>
				</div>
				<div class="row-fluid">
					<div class="center save">
						<a class="btn-mini" onclick="save();">保存</a>
						<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					</div>
				</div>
		    </form>
    	</div>
    </div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	var isEdit=${isEdit};
	function save(){
		if($("#Form").valid()){
			$.ajax({
				url:"<%=basePath%>auditRouteController.do?method=saveAuditStation",
				type:"POST",
				data:$("#Form").serialize(),
				dataType:"json",
				success: function(data) {
	            	if(data.success){
	            		modal("#layer_loading,#image");
	            		//localStorage.setItem('value','1');
	            		$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm030401").contents().find("#audit-result").val("1");
	            		top.Dialog.close();
	            	}else{
	            		bootbox.alert(data.msg);
	            		$(".modal").css({"left":"75%","width":"360px"});
	            	}
	        	}
			});
	    }
	}
</script>
</body>
</html>