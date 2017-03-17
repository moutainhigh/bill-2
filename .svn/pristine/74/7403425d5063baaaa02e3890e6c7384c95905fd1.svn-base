<%-- 
 * 文件名称: buyback_audit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
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
		<form action="<%=basePath%>buybackAuditController.do?method=entityAudit" method="post" name="Form" id="Form" class="form-search">
			<div class="row-fluid">
				<label class="text-right" for="status"><span class="star">*</span>审核状态</label>
				<t:dictSelect className="select-medium" valid="required" name="status" other="" dictGroup="K_AUDIT_STATUS" haveHead="true" title="审核状态" >
				</t:dictSelect>
			</div>
			<div class="row-fluid save">
				<div class="center">
					<a class="btn-mini" onclick="save();">保存</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					<input type="hidden" name="ids" id="ids" value="${ids}"/>
					<input type="hidden" name="buybackId" id="buybackId" value="${buybackId}"/>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function save(){
		modal("#layer_loading,#image");
			$.ajax({
				url:"<%=basePath%>buybackAuditController.do?method=entityAudit",
				type:"POST",
				data:$("#Form").serialize(),
				dataType:"json",
				success:function(rs){
					if(rs == "yes"){
						modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
						top.Dialog.close();
					}else{
						bootbox.alert("审核失败");
					}
				}
			});
		}
</script>
</body>
</html>