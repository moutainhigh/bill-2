<%-- 
 * 文件名称: syserrmsg_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-11 下午04:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/poseui.tld"%>
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
			<form  action="sysErrmsgController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="errCode" class="text-right"><span class="star">*</span>错误代码</label>
						<input type="text" class="input-medium" name="errCode" id="errCode" placeholder="请输入错误代码" value="${syserrmsg.errCode}"/>
					</div>
					<div class="row-fluid">
						<label for="errMsg" class="text-right"><span class="star">*</span>错误信息</label>
						<input type="text" class="input-medium" name="errMsg" id="errMsg" placeholder="请输入错误信息" value="${syserrmsg.errMsg}"/>
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
		document.getElementById("errCode").readOnly = true;
	}
	//保存
	function save(){
		if($("#errCode").val()==""){
	        showTips("errCode", "请输入错误代码");
			$("#errCode").focus();
			return false;
		}
		if($("#errMsg").val()==""){
			showTips("errMsg", "请输入错误信息");
			$("#errMsg").focus();
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
		var errCode = $("#errCode").val();
		var url = "sysErrmsgController.do?method=checkExists&errCode="+errCode;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("errCode", "错误代码已存在");
					$("#errCode").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			 }
  		});
	}
</script>
</body>
</html>