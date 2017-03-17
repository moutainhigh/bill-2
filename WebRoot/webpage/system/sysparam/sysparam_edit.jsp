<%-- 
 * 文件名称: sysparam_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-7-7 上午06:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%@ include file="../admin/top.jsp"%> 	 
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="sysParamController.do?method=save" name="Form" id="Form" method="post" class="row-fluid">
				<input type="hidden" name="isedit" value="${isedit}"/>
					<div id="zhongxin">
						<div class="row-fluid" >
							<label for="param_id" class="text-right"><span class="star">*</span>参数标识</label>
							<input type="text" class="input-medium" name="param_id" id="param_id" placeholder="输入参数标识" value="${sysparam.paramId}"/>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="text-right"><span class="star">*</span>参数标识名称</label>
							<input type="text" class="input-medium" name="param_name" id="param_name" placeholder="请输入参数标识名称" value="${sysparam.paramName}"/>
						</div>
						<div class="row-fluid" >
							<label for="param_value" class="text-right"><span class="star">*</span>参数标识值</label>
							<input type="text" class="input-medium" name="param_value" id="param_value" placeholder="请输入参数标识值" value="${sysparam.paramValue}"/>
						</div>
						<div class="row-fluid">
							<label for="value_name" class="text-right"><span class="star">*</span>参数标识值说明</label>
							<textarea style="width:260px;height:100px;" id="value_name" name="value_name" value="${sysparam.valueName}" placeholder="请输入参数标识值说明"></textarea>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="text-right"><span class="star">*</span>系统归属</label>
							<input type="text" class="input-medium" name="belong_type" id="belong_type" placeholder="请输入系统归属" value="${sysparam.belongType}"/>
						</div>
						<div class="row-fluid">
							<div class="center save">
								<a class="btn-mini" onclick="save();">保存</a>
								<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
							</div>
						</div>
					</div>
				</form>
			 </div>
		</div>			
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
var isedit = '${isedit}';
if(isedit == '1'){
	document.getElementById("param_id").readOnly = true;
}
//保存
function save(){
	if($("#param_id").val()==""){
        showTips("param_id", "输入参数标识");
		$("#param_id").focus();
		return false;
	}
	if($("#param_name").val()==""){
		showTips("param_name", "请输入参数标识名称");
		$("#param_name").focus();
		return false;
	}
	if($("#param_value").val()==""){
		showTips("param_value", "请输入参数标识值");
		$("#param_value").focus();
		return false;
	}
	if($("#value_name").val()==""){
		showTips("value_name", "请输入参数标识值描述");
		$("#value_name").focus();
		return false;
	}
	if($("#belong_type").val().length>1){
		showTips("belong_type", "系统归属长度不能超过1");
		$("#belong_type").focus();
		return false;
	}
	if($("#belong_type").val().length<=0){
		showTips("belong_type", "请输入系统归属");
		$("#belong_type").focus();
		return false;
	}
	if (isedit == '1'){
		$("#Form").submit();
		$("#zhongxin").hide();
		modal("#layer_loading,#image");
	} else {
		checkExist();
	}
}
//判断编码是否存在
function checkExist(){
	var param_id = $("#param_id").val();
	var url = "sysParamController.do?method=checkExists&param_id="+param_id;
	$.ajax({
		url:url,
		type:"POST",
		dataType:"JSON",
		success: function(data){
 				if(!data.success){
				showTips("param_id", "参数标识已存在");
				$("#param_id").focus();
			
			} else{
				$("#Form").submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			}
 			 }
 		});
}
</script>
</body>
</html>