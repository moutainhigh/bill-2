<%-- 
 * 文件名称: documents_submitted.jsp
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
		<form action="<%=basePath%>acptAccountController.do?method=doCumentsSubmitted" method="post" name="Form" id="Form" class="form-search">
			<div class="row-fluid">
				<label class="text-right" for="custAcctNo" valid="required"><span class="star">*</span>请输入票号</label>
				<input type="text" class="input-medium" id="billNo" name="billNo" placeholder="票号长度为16" class="input-medium" valid="required" maxLength="16"/>
			</div>
			<div class="row-fluid">
				<div class="center save">
					<a class="btn-mini" onclick="save();">保存</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					<input type="hidden" name="ids" id="ids" value="${ids}"/>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//定义表单输入校验函数
	function isValid(){
	   if(form.user_id.value==""){
	      alert("登录名不能为空！");  
	      document.form.user_id.focus();
	      return false;
	  }
    }
	//交易凭证保存方法
	function save(){
		if($("#Form").valid()){
			$.ajax({
				url:"<%=basePath%>acptAccountController.do?method=doCumentsSubmitted",
				type:"POST",
				data:$("#Form").serialize(),
				dataType:"json",
				success:function(data){
					if(data.success){
						//alert("提交成功");
						modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
						top.Dialog.close();
					}else{
						showTips("billNo",data.msg,5)
						//bootbox.alert(data.msg); 
					}
				}
			});
		}
	}
</script>
</body>
</html>