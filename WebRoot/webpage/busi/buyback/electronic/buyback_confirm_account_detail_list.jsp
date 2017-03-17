<%-- 
 * 文件名称: buyback_review_account_detail_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-11-08
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
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" disabled value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<label for="custName">交易对手</label>
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
					<div class="row-fluid">
						<label for="isOnline">清算方式</label>
						<input type="text" class="input-medium" name="isOnline" disabled value="${fns:getDictLabel('K_ISONLINE',batch.isOnline)}" />
						<label for="buybackRate">回购利率</label>
						<input type="text" class="input-medium" id="buybackRate" name="buybackRate" disabled value="${batch.buybackRate}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="printList();">打印清单</a>
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" href="javascript:confirmAccount();">确认记账</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
						</div>
		  			</div>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
							<th class="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">原卖出日</th>
							<th class="center">实际回购日</th>
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
									<td class="center"><input type="checkbox" name="ids" value="${buyback.buybackmxId}" onclick="getall('ids')" price="${buyback.billMoney}"/></td>
									<td class="center">${buyback.billNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',buyback.billType)}</td>
									<td class="center">${buyback.saleDt}</td>
									<td class="center">${buyback.buybackDt}</td>
									<td class="center">${buyback.regressDt}</td>
									<td class="center">${buyback.interestDays}</td>
									<td class="center">${fns:formateMoney(buyback.billMoney)}</td>
									<td class="center">${fns:formateMoney(buyback.interest)}</td>
									<td class="center">${fns:formateMoney(buyback.buybackMoney)}</td>
									<td class="center">${fns:formateMoney(buyback.saleReceiveMoney)}</td>
									<td class="center">${buyback.acceptorBankName}</td>
									<td class="center">${buyback.remitter}</td>
									<td class="center"><a href="javascript:goDetail('${buyback.rgctId}')">查看</a></td>
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
			<div id="select-Info">
				<div id="selectInfo"><center>暂时没有相关数据</center></div>
			</div>
			<%-- /列表操作区 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>buybackAccountController.do?method=buybackApplyAccountDetailList"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="buybackId" value="${batch.buybackId}"/>
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dynamicHiddenElement('pageForm','buybackId',"");
		pageForm.action="<%=basePath%>buybackAccountController.do?method=buybackConfirmAccountList";
		pageForm.submit();
	}
	function confirmAccount(){
	 	var checkNum = getCheckNum("ids");
   	 	if (checkNum == 0){
	   		bootbox.alert("请先选择要提交的记录");
	   		return;
	 	}
		var ids = getCheckStr("ids");
		dynamicHiddenElement('pageForm','buybackId',"${batch.buybackId}");
		dynamicHiddenElement('pageForm','ids',ids);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		pageForm.action="<%=basePath%>buybackAccountController.do?method=confirmAccount";
		pageForm.submit();
	}
	//详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	//回购记账清单打印
	function printList(){
		 var batch_id = "${batch.buybackId}";
	 	 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
	   	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1060701&type=BuybackListPrint&baid="+batch_id+"&handleType=记 账"); 	 
	}
</script>
</body>
</html>