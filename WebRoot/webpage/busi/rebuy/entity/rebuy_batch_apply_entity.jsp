<%-- 
 * 文件名称: rebuy_batch_apply_entity.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-9-10
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
		<div id="page-content" class="page-content">
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>rebuyApplyController.do?method=searchBatch"  method="post" name="bankForm" id="bankForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="custType"><span class="star">*</span>客户类型</label>						
						<select class='select2' class="input-medium" name="custType" id="custType" onchange="changeCustType();">
							<option value="-1">请选择</option>
							<option value="1">同业</option>
							<option value="2">系统内</option>
						</select>						
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						    <a class="btn-mini" href="javascript:add();">新增</a>
							<a class="btn-mini" href="javascript:edit();">修改</a>
					   </div>
					   <div class="span6" id="btn-right">
					   </div>
				  </div>
			  </form>
		</div>
	    <%-- /按钮操作区 --%>
	    <div id="footer">
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class="center" onclick="selectAll('zcheckbox', 'discId')">
							<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
						</th>
						<th class="center">批次号</th>
						<th class="center">票据类型</th>
						<th class="center">票据品种</th>
						<th class="center">合计张数</th>
						<th class="center">合计金额</th>
						<th class="center">申请创建日</th>
						<th class="center">利率</th>
						<th class="center">入账账号</th>
						<th class="center">账户名称</th>
						<th class="center">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<form  action="#" name="dataCollectForm" method="post"></form>
	</div>
</div>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	$("#tab th,td").addClass("center");
	//客户类型下拉框修改事件
	function changeCustType(){
		var custType = $("#custType").val();
		dynamicHiddenElement('dataCollectForm','custType',custType);
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=apply";
		dataCollectForm.submit();
	}
	//新增
	function add(){
	   	bootbox.alert("请选择客户类型");
	}
	//编辑
	function edit(){
	   	bootbox.alert("请选择客户类型");
	}
	function reset(){
		$("#custType").val("-1");
	}
</script>
</body>
</html>