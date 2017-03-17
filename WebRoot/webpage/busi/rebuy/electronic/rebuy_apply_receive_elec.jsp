<%-- 
 * 文件名称: rebuy_apply_receive_elec.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 电票转入接收页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
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
						<select class="select2 input-medium" name="custType" id="custType" onchange="changeCustType();">
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
						    <a class="btn-mini" href="javascript:receive();">接收</a>
							<a class="btn-mini" href="javascript:reject();">拒绝</a>
					    </div>
					    <div class="span6" id="btn-right">
					    </div>
				    </div>
				</form>
			</div>
	    	<%-- /按钮操作区 --%>
    		<div id="footer">
				<table class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1600px;width:1600px;" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'discId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">是否央行卖票</th>
							<th class="center">转入类型</th>
							<th class="center">清算方式</th>
							<th class="center">利率</th>
							<th class="center">转入日</th>
							<th class="center">赎回开放日</th>
							<th class="center">赎回截止日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">报文实付金额</th>
							<th class="center">承兑人</th>
							<th class="center">出票人</th>
							<th class="center">详细</th>
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
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=applyMainElec";
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