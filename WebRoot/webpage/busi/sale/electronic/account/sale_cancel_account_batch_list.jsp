<%-- 
 * 文件名称: sale_cancel_account_batch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次列表页面
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
		<%-- 按钮操作区 --%>
		<div id="center">
			<form id="btnForm">
				<div class="row-fluid">
					<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="billManage();">下一步</a> 
					</div>
				</div>
			</form>
		</div>
	    <div id="footer">
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class="center" >
							<input type="checkbox" id="zcheckbox" onclick="selectAll('zcheckbox', 'saleId')"/><span class="lbl"></span>
						</th>
						<th class="center">批次号</th>
						<th class="center">交易对手名称</th>
						<th class="center">票据类型</th>
						<th class="center">票据品种</th>
						<th class="center">利率</th>
						<th class="center">转贴现日期</th>
						<th class="center">合计张数</th>
						<th class="center">合计金额</th>
						<th class="center">合计利息</th>
						<th class="center">实收金额合计</th>
						<th class="center">清算方式</th>
						<th class="center">详情</th>
					</tr>
				</thead>
				<tbody id="dataBody">
				<c:choose>
						<c:when test="${not empty batchList}">
							<c:forEach items="${batchList}" var="saleApplyInfo" varStatus="vs">
					<tr>
						<td class="center">
							<input type='checkbox' name='saleId' value="${saleApplyInfo.saleId}" /><span class="lbl"></span>
						</td>
						<td class="center">${saleApplyInfo.batchNo}</td>
						<td class="center">${saleApplyInfo.custName}</td>
						<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleApplyInfo.billType)}</td>
						<td class="center">${fns:getDictLabel('K_BILL_CLASS',saleApplyInfo.billClass)}</td>
						<td class="center">${fns:formateRate(saleApplyInfo.rate)}</td>
						<td class="center">${saleApplyInfo.saleDt}</td>
						<td class="center">${saleApplyInfo.totalSize}</td>
						<td class="center">${fns:formateMoney(saleApplyInfo.sumMoney)}</td>
						<td class="center">${fns:formateMoney(saleApplyInfo.sumInterest)}</td>
						<td class="center">${fns:formateMoney(saleApplyInfo.sumReceiveMoney)}</td>
						<td class="center">${fns:getDictLabel('K_ISONLINE',saleApplyInfo.isOnline)}</td>
						<td class="center">
							<a href="javascript:goApplyInfo('${saleApplyInfo.saleId}')">查看</a>
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
		 <div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>saleAccountController.do?method=elecQueryApplyForCancelSaleAcct"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
	<%--可能的筛选条件隐藏域 --%>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function billManage(){
		var checkNum = getCheckNum("saleId");
		 if (checkNum ==0){
		 	bootbox.alert("请先选择要进行撤销记账操作的批次记录");
		 	return;
		 }else if (checkNum >1){
	  		bootbox.alert("一次只能对一个批次进行撤销记账操作");
	  		return;
	  	 }
	  	var saleId = getCheckStr("saleId");
	  	dynamicHiddenElement('dataCollectForm','batchId',saleId);
	  	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>saleAccountController.do?method=cancelAccountElecBillManage";
		dataCollectForm.submit();
	}
	//批次详情页面
	function goApplyInfo(saleId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>saleApplyController.do?method=goApplyInfo&saleId='+saleId+'&flag=elecCancelAccount';
		 diag.Width = 1000;
		 diag.Height = 505;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>