<%--
 * 文件名称: saleback_entity_revoke_apply_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次下清单列表
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>票据管理页面</title>
<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="infoForm" id="infoForm" class="form-search disEleOuter">
					<%--查询区 --%>
					<div class="row-fluid firstLine">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="billType">票据类型</label>
						<input type="text" class="input-medium" name="billType" id="billType" value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}" readonly/>
						<label for="inAcctNo">票据品种</label>
						<input type="text" class="input-medium" name="inAcctNo" readonly value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
					</div>
					<div class="row-fluid outer">
						<label for="inAcctName">客户名称</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.custName}" readonly />
						<label for="inAcctNo">入账账号</label>
						<input type="text" class="input-medium" id="inAcctNo" readonly name="inAcctNo"  value="${batch.inAcctNo}" onblur="fillAcctInfo();"/>
						<label for="inAcctName">账户名称</label>
						<input type="text" class="input-medium" id="inAcctName" name="inAcctName" value="${batch.inAcctName}" readonly />
						<%--<label for="inAcctType">账<span style="margin:0 6px;"></span>号<span style="margin:0 6px;"></span>类<span style="margin:0 6px;"></span>型</label>
						<input type="text" class="input-medium" name="inAcctType" id="inAcctType" value="${batch.inAcctType}" readonly/>
						</div>--%>
					</div>
					<div class="row-fluid threeLine">
						<label for="saleDt">返售开放日</label>
						<input type="text" class="input-medium input-date" name="saleDt" readonly value="${batch.salebackOpenDt}"/>
						<label for="saleType">返售截止日</label>
						<input type="text" class="input-medium input-date" name="saleType" readonly id="saleType" value="${batch.salebackDueDt}" />
						<label for="billType">返售日期</label>
						<input type="text" class="input-medium input-date" name="createDt" readonly id="createDt" value="${batch.createDt}" />
					</div>
					<div class="row-fluid fiveLine">
						<label for="totalSize">合计张数</label>
						<input type="text" class="input-medium" name="totalSize" readonly value="${batch.totalNum}" />
						<label for="sumMoney">合计金额</label>
						<input type="text" class="input-medium" name="sumMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
						<label for="rate">利率</label>
						<input type="text" class="input-medium" id ="rate" readonly name="rate"  value="${fns:formateRate(batch.rate)}" onblur="checkRate();"/>
					</div>
				</form>
			</div>
			<%--按钮操作区--%>
			<div id="center">
				<form id="btnForm" name="btnForm" action="<%=basePath%>SaleBackAuditController.do?method=auditbilllist" method="post">
					<div class="row-fluid">
						<div class="span6" id="btn-left"></div>
						<div class="span6" id="btn-right"> 
							<a class="btn-mini pull-right" onclick="toAudit();">撤销审核</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
						</div>
					</div>
				</form>
			</div>
			<%--列表操作区--%>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover"
					id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1400px;width:1400px">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'salemxId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
								</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">返售到期日</th>
							<th class="center">票面金额</th>
							<th class="center">出票日</th>
							<th class="center">计息天数</th>
							<th class="center">利息支出</th>
							<th class="center">实收金额</th>
							<th class="center">出票人</th>
							<th class="center">出票人开户行行号</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="saleBillInfo"
									varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='salemxId' value="${saleBillInfo.salebackmxId}" /><span class="lbl"></span>
										</td>
										<td class="center">${saleBillInfo.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleBillInfo.billType)}</td>
										<td class="center">${saleBillInfo.salebackDueDt}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.billMoney)}</td>
										<td class="center">${saleBillInfo.issueDt}</td>
										<td class="center">${saleBillInfo.interestDays}</td>
										<td class="center">${saleBillInfo.interest}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.salebackMoney)}</td>
										<td class="center">${saleBillInfo.remitter}</td>
										<td class="center">${saleBillInfo.remitterBankNo}</td>
										<td class="center">
											<a href="javascript:goDetail('${saleBillInfo.salebackmxId}')">查看</a>
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
			<%--用于页面跳转--%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%--分页--%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>SaleBackAuditController.do?method=entityrevokeAuditSalebackBill" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="salebackId" id="saleId" value="${batch.salebackId}" />
					<input type="hidden" name="billClass" id="billClass" value="${batch.billClass}" />
				</form>
			</div>
		</div>
	</div>
<%@include file="/webpage/system/admin/modalDialog.jsp" %> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>SaleBackAuditController.do?method=entityAuditSalebackBatch";
		dataCollectForm.submit();
	}
	//撤销审核
	function toAudit(){
		 var checkNum = getCheckNum("salemxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请至少选择一条要进行撤销的记录");
	   		return;
	   	 }
		var salebackmxId = getCheckStr("salemxId");//清单主键
		var salebcakId = "${batch.salebackId}"
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dynamicHiddenElement('dataCollectForm','salebackmxId',salebackmxId);
	  	dynamicHiddenElement('dataCollectForm','salebackId',salebcakId);
		dataCollectForm.action = "<%=basePath%>SaleBackAuditController.do?method=revokeauditsubmit";
		dataCollectForm.submit();
	}
	//详情页面
	function goDetail(salebackmxId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>SalebackApplyController.do?method=goBillInfo&salebackmxId="+salebackmxId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>
