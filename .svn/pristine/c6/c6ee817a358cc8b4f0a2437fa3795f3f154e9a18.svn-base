<%-- 
 * 文件名称: rebuy_audit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-9-10
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
		<form action="#" method="post" name="Form" id="Form" class="form-search">
			<input type="hidden" name="ids" id="ids" value="${ids}" />
			<input type="hidden" name="rebuyId" id="ids" value="${rebuyId}" />
			<div class="row-fluid">
				<label class="text-right" for="status"><span class="star">*</span>审核状态</label>
				<t:dictSelect valid="required" name="status" className="select-medium" other="" dictGroup="K_AUDIT_STATUS" haveHead="true" title="审核状态" >
				</t:dictSelect>
			</div>
			<div class="row-fluid">
				<label class="text-right" for="custAcctNo">审核意见</label>
				<textarea id="reason" name="reason" class="form-control"></textarea>
			</div>
			<div class="row-fluid save">
				<div class="center">
					<a class="btn-mini" onclick="save();">提交</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
function save(){
	if($("#Form").valid()){
   		if($("#status").val()=="0" && $("#reason").val()==""){
				bootbox.alert("请输入审核意见");
				return;
			}else{
			var billClass = "${billClass}";
			var url = "<%=basePath%>rebuyAuditController.do?method=elecauditSubmit";
			if(billClass=="1"){
				url = "<%=basePath%>rebuyAuditController.do?method=auditSubmit";
			}
				$.ajax({
               	 	type: "POST",
                	url:url,
                	data:$('#Form').serialize(),
                	async: false,
                	dataType:'json',
                	success: function(data) {
                    	if(data.success){
                    		bootbox.alert("审核成功");
                    		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
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