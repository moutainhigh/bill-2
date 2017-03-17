<%-- 
 * 文件名称: interest_adjust.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 利息调整
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
		<title>调整利息</title>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%>
		<%@ include file="/webpage/system/admin/footer.jsp"%> 
	</head>
	
<body style="font-family:'微软雅黑';background:#f4f8fb;">
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="<%=basePath%>saleApplyController.do?method=interestAdjust" name="Form" id="Form"
				method="post" class="form-search">
				<input type="hidden" name="salemxId" value="${salemxId}" placeholder=""/>
				<div class="row-fluid" >
				<label for="adjustMoney" class="text-right"><span class="star">*</span>请输入调整金额</label>
				<input type="text" class="input-medium" id="adjustMoney" name="adjustMoney" valid="required">
			</div>
			<div class="row-fluid center" style="color:red;font-weight:bold;line-height:20px;">
				(注:调整的范围大于等于零,小于等于票面金额)
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
	<script type="text/javascript">
		function save(){
			if(!$("#adjustMoney").valid()){
				showTips("adjustMoney","请输入调整后的利息");
				$("#adjustMoney").focus();
				return false;
			}else{
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				$("#Form").submit();
				$("#Form").hide();
			}
		}
	</script>
</body>
</html>