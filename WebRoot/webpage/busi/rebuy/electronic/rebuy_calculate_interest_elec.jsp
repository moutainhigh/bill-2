<%-- 
 * 文件名称: rebuy_calculate_interest_elec.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 电票利息试算页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 利息试算
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-10-17
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
	</head>
<body>
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="<%=basePath%>rebuyApplyController.do?method=calculateInterestElec" name="interestForm" id="interestForm"
				method="post" class="form-search">
				<input type="hidden" name="ids" value="${ids}"/>
				<input type="hidden" name="isSameCity" value="${isSameCity}"/>
				<div class="row-fluid" >
					<label for="delayType" class="text-right"><span class="star">*</span>请选择顺延规则</label>
					<t:dictSelect valid="required" onchange="changeDelayType();" name="delayType" className="select-medium" other="" dictGroup="K_ELEC_DELAYTYPE" haveHead="true" title="请选择顺延规则" defaultVal='0' >
					</t:dictSelect>
				</div>
				<div class="row-fluid" id="delayDaysDiv" style="display:none;">
					<label for="delayDays" class="text-right"><span class="star">*</span>请输异地顺延天数</label>
					<input type="text" valid="required" title="请输入异地顺延天数" class="input-medium" id="delayDays" name="delayDays" value="3"/>
					<span style="margin:8px 0 0 -5px">天</span>
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
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
	function save() {
		if($("#interestForm").valid()){
			modal("#layer_loading,#image");
			$("#interestForm").submit();
			$("#zhongxin").hide();
		}
	}
	function changeDelayType(){
		if($("#delayType").val()=="5"){
            $("#delayDaysDiv").show();
        }else{
            $("#delayDaysDiv").hide();
		}
	}
</script>
</body>
</html>