<%-- 
 * 文件名称: buyback_bill_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-08-18
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
<body style="font-family:'微软雅黑';background:#f4f8fb;">
<div class="page-content" id="jump-content">
	<table class="table table-bordered table-condensed" id="tab1" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td colspan="2" style="padding-left:18px">票号</td>
				<td colspan="4">${buybackBill.billNo}</td>
			</tr>
			<tr>
				<td rowspan="3" class="line">出<br/>票<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwids">${buybackBill.remitter}</td>
				<td rowspan="3" class="line">收<br/>款<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwid">${buybackBill.payeeName}</td>
			</tr>
			<tr>
				<td class="wid">账号</td>
				<td class="lenwids">${buybackBill.remitterAcct}</td>
				<td class="wid">账号</td>
				<td class="lenwid">${buybackBill.payeeAcct}</td>
			</tr>
			<tr>
				<td class="wid">开户行</td>
				<td class="lenwids">${buybackBill.remitterBankName}</td>
				<td class="wid">开户行</td>
				<td class="lenwid">${buybackBill.payeeBankName}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-condensed" id="tab2" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>票据品种</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',buybackBill.billClass)}</td>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',buybackBill.billType)}</td>
				<td>票据来源</td>
				<td>${fns:getDictLabel('K_BUY_TYPE',buybackBill.billSource)}</td>
			</tr>
			<tr>
				<td>承兑人</td>
				<td style="width:260px;" class="bank">${buybackBill.acceptor}</td>
				<td>客户名称</td>
				<td>${buybackBill.billOwner}</td>
				<td>金额</td>
				<td>${fns:formateMoney(buybackBill.billMoney)}</td>
			</tr>
			<tr>
				<td>出票日</td>
				<td>${buybackBill.issueDt}</td>
				<td>票面到期日</td>
				<td style="width:150px;">${buybackBill.dueDt}</td>
				<td>票据前手</td>
				<td>${buybackBill.billBeforeOwner}</td>
			</tr>
			<tr>
				<td>回购到期日</td>
				<td>${buybackBill.regressDt}</td>
				<td>顺延天数</td>
				<td>${buybackBill.delayDays}</td>
				<td>计息天数</td>
				<td>${buybackBill.interestDays}</td>
			</tr>
			<tr>
				<td>实付金额</td>
				<td>${fns:formateMoney(buybackBill.buybackMoney)}</td>
				<td>付款方地址</td>
				<td>${buybackBill.draweeAddr}</td>
				<td>原转出利率</td>
				<td>${buybackBill.saleInterest}</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>