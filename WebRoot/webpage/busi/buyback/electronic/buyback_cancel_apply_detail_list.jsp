<%-- 
 * 文件名称:buyback_cancel_apply_detail_list.jsp
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
</head>
<body>
<div class="clearfix">
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="Form" id="Form" class="form-search detail-form">
					<input type="hidden" id="buybackId" value="${query.buybackId}"></input>
					<input type="hidden" id="ids" value="${ids}"></input>
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" disabled value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
					</div>
					<div class="row-fluid">
						<label for="custName">交易对手</label>
						<input type="text" class="input-medium" id="custName" name="custName" value="${batch.custName}" disabled />
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" disabled value="${fns:formateMoney(batch.totalMoney)}" />
					</div>
					<div class="row-fluid">
						<label for="profOwner">总利息</label>
						<input type="text" class="input-medium" name="profOwner" disabled value="${fns:formateMoney(batch.totalInterest)}" />
						<label for="profOwner">实付金额</label>
						<input type="text" class="input-medium" name="totalDraweeMoney" disabled value="${fns:formateMoney(batch.totalDraweeMoney)}" />
						<label for="isOnline">清算方式</label>
						<input type="text" class="input-medium" name="isOnline" disabled value="${fns:getDictLabel('K_ISONLINE',batch.isOnline)}" />
						<label for="buybackRate">回购利率</label>
						<input type="text" class="input-medium" id="buybackRate" name="buybackRate" disabled value="${batch.buybackRate}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" href="javascript:cancelApply();">撤销</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
					   </div>
				  	</div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
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
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>buybackApplyController.do?method=cancelApplyDetailList" method="post" name="pageForm">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						<input type="hidden" name="buybackId" value="${batch.buybackId}"/>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function cancelApply(){
		var ids = $("#ids").val();
		var buybackId = $("#buybackId").val();
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dynamicHiddenElement('Form','ids',ids);
		dynamicHiddenElement('Form','buybackId',buybackId);
		Form.action = "<%=basePath%>buybackApplyController.do?method=cancelApply";
		Form.submit();
	}
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		Form.action = "<%=basePath%>buybackApplyController.do?method=buybackApplyCancelList";
		Form.submit();
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
</script>
</body>
</html>