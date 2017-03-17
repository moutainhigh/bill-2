<%-- 
 * 文件名称: busibranch_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
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
			<form  action="busiBranchController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<%--<input type="hidden" name="id" value="${busibranch.id}"/>--%>
				<div id="zhongxin">
					<div class="row-fluid">
						<label for="brch_no" class="text-right"><span class="star">*</span>机构编号</label>
						<input type="text" class="input-medium" name="brch_no" id="brch_no" placeholder="请输入机构编号" value="${busibranch.brchNo}" title="机构编号"/>
					</div>
					<div class="row-fluid">
						<label for="name" class="text-right"><span class="star">*</span>机构名称</label>
						<input type="text" class="input-medium" name="name" id="name" placeholder="请输入机构名称" value="${busibranch.name}" title="机构名称"/>
					</div>
					<div class="row-fluid">
						<label for="value_name" class="text-right"><span class="star">*</span>机构状态</label>
						<t:dictSelect name="status" other="width:153px; " dictGroup="K_QYBZ" defaultVal="${busibranch.status}" haveHead="true"  title="状态">
						</t:dictSelect>
					</div>
					<div class="row-fluid center">
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
		if($("#brch_no").val()==""){
			showTips("brch_no", "请输入机构编号");
			$("#brch_no").focus();
			return false;
		}
		if($("#name").val()==""){
	        showTips("name", "请输入机构名称");
			$("#name").focus();
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
		var brch_no = $("#brch_no").val();
		var url = "busiBranchController.do?method=checkExists&brch_no="+brch_no;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("brch_no", "机构编号已存在");
					$("#brch_no").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			}
  		});
	}
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("brch_no").readOnly = true;
	}
	var isstatus='${busibranch.status}';
	if(isstatus=="未启用"){
	    document.getElementById("statusno").checked=true;
	}
</script>	
</body>
</html>