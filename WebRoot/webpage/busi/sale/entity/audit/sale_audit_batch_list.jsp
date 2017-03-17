<%-- 
 * 文件名称: sale_audit_batch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 待审核的批次列表页面
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
							<a class="btn-mini pull-left" onclick="auditDetailList();">下一步</a>
						</div>
					</div>
				</form>
			</div>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'saleId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">批次号</th>
							<th class="center">交易对手名称</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">合计张数</th>
							<th class="center">合计金额</th>
							<th class="center">合计利息</th>
							<th class="center">申请生成日</th>
							<th class="center">客户经理</th>
							<th class="center">部门归属</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="saleBatch" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='saleId' value="${saleBatch.saleId}" /><span class="lbl"></span>
										</td>
										<td class="center">${saleBatch.batchNo}</td>
										<td class="center">${saleBatch.custName}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleBatch.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',saleBatch.billClass)}</td>
										<td class="center">${saleBatch.totalSize}</td>
										<td class="center">${fns:formateMoney(saleBatch.sumMoney)}</td>
										<td class="center">${fns:formateMoney(saleBatch.sumInterest)}</td>
										<td class="center">${saleBatch.createDt}</td>
										<td class="center">${saleBatch.custManagerName}</td>
										<td class="center">${saleBatch.deptName}</td>
										<td class="center">
										<a href="javascript:goApplyInfo('${saleBatch.saleId}')">查看</a>
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
			<%-- 用于页面跳转 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- 分页 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>saleAuditController.do?method=auditList"
					name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function auditDetailList(){
		var checkNum = getCheckNum("saleId");
	   	if (checkNum !=1){
	   		bootbox.alert("请选择一条记录");
	   		return;
	   	}
	   	var saleId = getCheckStr("saleId");
	   	dynamicHiddenElement('dataCollectForm','saleId',saleId);
	   	dynamicHiddenElement('dataCollectForm','flag','1');
	   	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>saleAuditController.do?method=auditDetailList";
		dataCollectForm.submit();
	}
	//批次详情页面
	function goApplyInfo(saleId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>saleApplyController.do?method=goApplyInfo&saleId='+saleId+'&flag=audit';
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