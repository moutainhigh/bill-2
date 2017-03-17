<%-- 
 * 文件名称: interest_trial.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 利息试算
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-11-09
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
<body>
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="<%=basePath%>buybackApplyController.do?method=interestTrial" name="Form" id="Form"
				method="post" class="form-search">
			<input type="hidden" name="ids" value="${ids}"/>
			<input type="hidden" name="isSameCity" value="1"/>
			<input type="hidden" name="saleId" value="${saleId}"/>
			<input type="hidden" name="buybackRate" value="${buybackRate}"/>
			<input type="hidden" name="buybackId" value="${batch.buybackId}"/>
			<input type="hidden" name="batchNo" value="${batch.batchNo}"/>
			<div class="row-fluid" >
				<label for="delayType" class="text-right"><span class="star">*</span>请选择顺延规则</label>
				<t:dictSelect valid="required" name="delayType" className="select-medium" other="" dictGroup="K_ELEC_DELAYTYPE" defaultVal="0" haveHead="true" title="请选择顺延规则" >
				</t:dictSelect>
			</div>
			<div class="row-fluid delay" >
				<label for="delayDays" class="text-right"><span class="star">*</span>请输入顺延天数</label>
				<input type="text" valid="required" title="请输入顺延天数" class="input-medium" id="delayDays" name="delayDays" value="3"/>
				<span style="margin:8px 0 0 0px">天</span>
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
<script type="text/javascript">
	var delayType = $("#delayType").val();
	if($("#delayType").val()=="5"){
            $(".delay").show();
        }else{
            $(".delay").hide();
	}
	$(document).ready(function(){
	    $("#delayType").change(function(){
	        if($("#delayType").val()=="5"){
	            $(".delay").show();
	        }else{
	            $(".delay").hide();
	        }
	   	 });
	});
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