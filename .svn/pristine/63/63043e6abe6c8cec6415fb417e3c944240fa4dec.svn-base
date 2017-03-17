<%--
 * 文件名称: audit.jsp
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
		<%--jsp文件头和头部--%>
		<%@ include file="/webpage/system/admin/top.jsp"%>
	</head>
<body style="padding-top:20px;background:#f4f8fb;">
	<div id="jump-content" class="page-content">
		<form action="<%=basePath%>discAuditController.do?method=audit" method="post"
			name="Form" id="Form" class="form-search">
			<div class="row-fluid">
				<label class="text-right" for="status"><span class="star">*</span>审核状态</label>
				<t:dictSelect valid="required" name="status" other="" className="select-medium"
						dictGroup="K_AUDIT_STATUS" haveHead="true" title="审核状态">
				</t:dictSelect>
			</div>
			<div class="row-fluid">
				<label class="text-right" for="option">审核意见</label>
				<textarea id="option" name="option" class="input-medium"  placeholder="请写审核意见"></textarea>
			</div>
			<div class="row-fluid save">
				<div class="center">
					<a class="btn-mini btn-primary" onclick="save();">保存</a> 
					<a class="btn-mini btn-primary" onclick="top.Dialog.close();">取消</a>
					<input type="hidden" name="ids" id="ids" value="${ids}" />
				</div>
			</div>
		</form>
	</div>
<!--用于页面跳转 -->
<form  action="#" name="dataCollectForm" method="post"></form>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function save(){
		if($("#Form").valid()){
			if($("#status").val()=="0" && $("#option").val()==""){
				showTips("reason","请输入审批意见");
				return;
			}else{
				$.ajax({
               	 	type: "POST",
                	url:"<%=basePath%>SaleBackAuditController.do?method=auditapply",
                	data:$('#Form').serialize(),
                	async: false,
                	dataType:'json',
                	success: function(data) {
                    	if(data.success){
                    		//bootbox.alert("审核成功");
                    		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
                    		$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010503").contents().find("#audit-result").val("1");
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