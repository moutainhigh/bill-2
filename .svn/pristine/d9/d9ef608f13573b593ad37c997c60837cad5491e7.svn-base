<%-- 
 * 文件名称: disc_audit.jsp
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
	<div id="jump-content" class="page-content">
		<form action="<%=basePath%>discAuditController.do?method=audit" method="post" name="Form" id="Form" class="form-search">
			<div class="row-fluid">
				<label class="text-right" for="status"><span class="star">*</span>审核状态</label>
				<t:dictSelect valid="required" className="select-medium" name="status" other="" dictGroup="K_AUDIT_STATUS" haveHead="true" title="审核状态" >
				</t:dictSelect>
			</div>
			<div class="row-fluid">
				<label class="text-right" for="custAcctNo">审核意见</label>
				<textarea id="reason" name="reason" class="input-medium"></textarea>
			</div>
			<div class="row-fluid center save">
				<div class="center">
					<a class="btn-mini" onclick="save();">保存</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					<input type="hidden" name="ids" id="ids" value="${ids}"/>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function save(){
		if($("#Form").valid()){
			if($("#status").val()=="0" && $("#reason").val()==""){
				showTips("reason","请输入审批意见");
				return;
			}else{
				$.ajax({
               	 	type: "POST",
                	url:"<%=basePath%>discAuditController.do?method=audit",
                	data:$('#Form').serialize(),
                	async: false,
                	dataType:'json',
                	success: function(data) {
                    	if(data.success){
                    		//bootbox.alert("审核成功");
                    		$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010203").contents().find("#audit-result").val("1");
                    		//localStorage.setItem('value','1');
                    		top.Dialog.close();
                    	}
                	}
            	});
			}
		}
	}
</script>
</body>
</html>