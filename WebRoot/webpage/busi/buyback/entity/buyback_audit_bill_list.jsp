<%-- 
 * 文件名称:buyback_audit_bill_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
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
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>票据管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
	<script type="text/javascript" src="webpage/system/addPrint/commonFunc.js"></script>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="Form" id="Form" class="form-search detail-form">
					<input type="hidden" id="buybackId" value="${query.buybackId}"></input>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" disabled value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" id="custName" name="custName" value="${batch.custName}" disabled />
					</div>
					<div class="row-fluid">
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" disabled value="${fns:formateMoney(batch.totalMoney)}" />
						<label for="profOwner">总利息</label>
						<input type="text" class="input-medium" name="profOwner" disabled value="${fns:formateMoney(batch.totalInterest)}" />
						<label for="profOwner">实付金额</label>
						<input type="text" class="input-medium" name="totalDraweeMoney" disabled value="${fns:formateMoney(batch.totalDraweeMoney)}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="printList();">打印清单</a>
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" href="javascript:audit();">审核</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
					   </div>
				  	</div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
						<th class="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
						<th class="center">票号</th>
						<th class="center">票据类型</th>
						<th class="center">原卖出日</th>
						<th class="center">回购到期日</th>
						<th class="center">计息天数</th>
						<th class="center">票面金额</th>
						<th class="center">回购利息</th>
						<th class="center">实付金额</th>
						<th class="center">原转出实收金额</th>
						<th class="center">承兑行</th>
						<th class="center">出票人</th>
						<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="buyback" varStatus="vs">
									<tr>
									<td class="center"><input type="checkbox" name="ids" value="${buyback.buybackmxId}"/></td>
									<td class="center">${buyback.billNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',buyback.billType)}</td>
									<td class="center">${buyback.saleDt}</td>
									<td class="center">${buyback.regressDt}</td>
									<td class="center">${buyback.interestDays}</td>
									<td class="center">${fns:formateMoney(buyback.billMoney)}</td>
									<td class="center">${fns:formateMoney(buyback.interest)}</td>
									<td class="center">${fns:formateMoney(buyback.buybackMoney)}</td>
									<td class="center">${fns:formateMoney(buyback.saleReceiveMoney)}</td>
									<td class="center">${buyback.acceptorBankName}</td>
									<td class="center">${buyback.remitter}</td>
									<td class="center"><a href="javascript:goBillDetail('${buyback.buybackmxId}')">查看</a></td>
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
			<form action="#" name="dataCollectForm" method="post"></form>
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>buybackAuditController.do?method=entityAuditBillList" method="post" name="pageForm">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						<input type="hidden" name="buybackId" value="${batch.buybackId}"/>
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//审核
	function audit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要审核的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		var buybackId = '${batch.buybackId}';
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="审核";
		 diag.URL = "<%=basePath%>buybackAuditController.do?method=toAuditForEntity&ids="+ids+"&buybackId="+buybackId;
		 diag.Width = 430;
		 diag.Height =130;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
	        dataCollectForm.action = "<%=basePath%>buybackAuditController.do?method=entityAuditBatchList"
	        dataCollectForm.submit();
		 };
		 diag.show();
	}
	//返回
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		Form.action = "<%=basePath%>buybackAuditController.do?method=entityAuditBatchList";
		Form.submit();
	}
	//详情页面
	function goBillDetail(buybackmxId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>buybackApplyController.do?method=goBillDetail&buybackmxId="+buybackmxId;
		 diag.Width = 930;
		 diag.Height = 320;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	//回购审核
	function printList(){
		 var batch_id = "${query.buybackId}";
	 	 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
	   	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1060701&type=BuybackListPrint&baid="+batch_id+"&handleType=申 请"); 	 
	}
</script>
</body>
</html>