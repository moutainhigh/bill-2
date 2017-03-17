<%-- 
 * 文件名称: audit.jsp
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
<body style="background:#f4f8fb;">
	<div id="jump-content" class="page-content">
		<form action="<%=basePath%>collateralizationAuditController.do?method=audit" method="post" name="Form" id="Form" class="form-search">
			<div class="row-fluid">
				<label class="text-right" for="status"><span class="star">*</span>审核状态</label>
				<t:dictSelect valid="required" name="status" className="select-medium" other="" dictGroup="K_AUDIT_STATUS" haveHead="true" title="审核状态" >
				</t:dictSelect>
			</div>
			<div class="row-fluid">
				<label class="text-right" for="custAcctNo">审核意见</label>
				<textarea id="option" name="option" class="input-medium"></textarea>
			</div>
			<div class="row-fluid save">
				<div class="center">
					<a class="btn-mini" onclick="save();">保存</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					<input type="hidden" name="ids" id="ids" value="${ids}"/>
					<input type="hidden" name="saveId" id="saveId" value="${saveId}"/>
				</div>
			</div>
		</form>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function save(){
		if($("#Form").valid()){
			if($("#status").val()=="0" && $("#option").val()==""){
				bootbox.alert("请输入审核意见!");
				return;
			}else{
				$.ajax({
					url:"<%=basePath%>collateralizationAuditController.do?method=audit",
					type:"POST",
					data:$("#Form").serialize(),
					dataType:"json",
					success:function(rs){
						if(rs == "yes"){
							modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
							top.Dialog.close();
						}else{
							bootbox.alert("审核失败!");
						}
					}
				});
			}
		}
	}
</script>
</body>
</html>