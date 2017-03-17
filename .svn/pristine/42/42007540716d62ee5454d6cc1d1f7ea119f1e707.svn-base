<%-- 
 * 文件名称: disc_cancel_account_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
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
		<div id="page-content" class="page-content">
			<div id="header">
				<form action="<%=basePath%>discAccountController.do?method=cancelAccountList"  method="post" id="page-content" name="custForm" class="form-search">
			    <%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="batchNo">批次号</label>
						<input type="text" id="batchNo" name="batchNo" class="input-medium" valid="required" value="" placeholder="请输入批次号"/>
						<a class="btn-mini" id="search" onclick="doSearch();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<div class="row-fluid">
					<div class="span6" id="btn-left" >
						<a class="btn-mini" href="javascript:cancelAccount();">下一步</a>
				   	</div>
				   	<div class="span6" id="btn-right"></div>
			  	</div>
			</div>
		    <%-- /按钮操作区 --%>
    		<div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
						    <th class="center"><input type="checkbox" disabled="disabled"/></th>
							<th class="center">批次号</th>
							<th class="center">客户名称</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">合计张数</th>
							<th class="center">合计金额</th>
							<th class="center">总利息</th>
							<th class="center">实付金额</th>
							<th class="center">客户经理</th>
							<th class="center">申请生成日</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="disc" varStatus="vs">
									<tr>
									    <td class="center"><input type="checkbox" onclick="radioStyle(this,'discId')" name="discId" value="${disc.discId}"/></td>
										<td class="center">${disc.batchNo}</td>
										<td class="center">${disc.custName}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',disc.billClass)}</td>
										<td class="center">${disc.totalNum}</td>
										<td class="center">${fns:formateMoney(disc.totalMoney)}</td>
										<td class="center">${fns:formateMoney(disc.totalInterest)}</td>
										<td class="center">${fns:formateMoney(disc.actualAmount)}</td>
										<td class="center">${disc.custManagerName}</td>
										<td class="center">${disc.createDt}</td>
										<td class="center">
											<a class=""  href="javascript:goBatchInfo('${disc.discId}');">查看</a>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="100" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- /列表操作区 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>discAccountController.do?method=cancelAccountList" method="post" name="pageForm">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function doSearch(){
		//var validResult = $("#searchForm").valid();
		//if(!validResult){
			//bootbox.alert("客户账号、客户号、客户名称不能为空！");
		//}else{
			//调用后台查询方法查询客户账号对应的批次信息
			custForm.submit();
		//}
		}
	function cancelAccount(){
		var checkNum = getCheckNum("discId");
   		if (checkNum !=1){
   			bootbox.alert("请先选择要操作的记录");
   			return;
   	 	}
   		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
   		var discId = getCheckStr("discId");	
   		dynamicHiddenElement('dataCollectForm','discId',discId);
   		dataCollectForm.action = "discAccountController.do?method=cancelAccountDetailList";
   		dataCollectForm.submit();
	}
	//详情页面
	function goBatchInfo(discId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>discApplyController.do?method=goBatchInfo&discId='+discId;
		 diag.Width = 800;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>