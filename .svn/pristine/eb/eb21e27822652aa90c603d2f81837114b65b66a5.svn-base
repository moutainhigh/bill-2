<%-- 
 * 文件名称: interest_trial.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 利息试算
 * 系统版本: @version2.0.0.1
 * 开发人员: 
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
		<title>利息试算</title>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%>
		<%@ include file="/webpage/system/admin/footer.jsp"%> 
	</head>
<body style="font-family:'微软雅黑';background:#f4f8fb;">
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="<%=basePath%>saleApplyController.do?method=interestTrial" name="Form" id="Form"
				method="post" class="form-search">
				<input type="hidden" name="salemxIds" value="${salemxIds}" placeholder=""/>
				<div class="row-fluid" >
				<label for="isSameCity" class="text-right"><span class="star">*</span>请选择同城异地</label>
				<c:if test="${billClass=='1' }">
					<t:dictSelect valid="required" className="select-medium" name="isSameCity" other="" dictGroup="K_ISSAMECITY" haveHead="true" defaultVal="1" title="同城异地" ></t:dictSelect>
				</c:if>
				<c:if test="${billClass=='2' }">
					<t:dictSelect valid="required" className="select-medium" name="isSameCity" other="" dictGroup="K_ISSAMECITY" haveHead="true" defaultVal="1" title="同城异地" disabled="true"></t:dictSelect>
					<input type="hidden" name="isSameCity" id="isSameCity"  value="1" />
				</c:if>
			</div>
			<div class="row-fluid" >
				<label for="delayType" class="text-right"><span class="star">*</span>请选顺延规则择</label>
				<t:dictSelect valid="required" className="select-medium" name="delayType" other="" dictGroup="K_DELAYTYPE" haveHead="true" defaultVal="0" title="顺延规则" ></t:dictSelect>
			</div>
			<div class="row-fluid" >
				<label for="delayDays" class="text-right"><span class="star">*</span>请输异地顺延天数</label>
				<input type="text" class="input-medium" id="delayDays" name="delayDays" valid="required" tips="请输入异地顺延天数" value="3"/>
				<span style="margin:10px 0 0 0px">天</span>
			</div>
			<div class="row-fluid">
				<div class="center save">
					<a class="btn-mini" onclick="save();">提交</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
	<script type="text/javascript">
	function save() {
		if($("#Form").valid()){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			$("#Form").submit();
			//$("#zhongxin").hide();
		}
	}
	</script>
</body>
</html>