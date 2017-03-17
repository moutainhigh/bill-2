<%-- 
 * 文件名称: rebuy_bill_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
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
				<td>${bill.billNo}</td>
				<td colspan="2">批次号</td>
				<td>${apply.batchNo}</td>
			</tr>
			<tr>
				<td rowspan="3" class="line">出<br/>票<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwids">${bill.remitter}</td>
				<td rowspan="3" class="line">收<br/>款<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwid">${bill.payee}</td>
			</tr>
			<tr>
				<td class="wid">账号</td>
				<td class="lenwids">${bill.remitterAcct}</td>
				<td class="wid">账号</td>
				<td class="lenwid">${bill.payeeAcct}</td>
			</tr>
			<tr>
				<td class="wid">开户行</td>
				<td class="lenwids">${bill.remitterBankName}</td>
				<td class="wid">开户行</td>
				<td class="lenwid">${bill.payeeBankName}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-condensed" id="tab2" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>票据品种</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',bill.billClass)}</td>
				<td>票据来源</td>
				<td>${bill.billSource}</td>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',bill.billType)}</td>
			</tr>
			<tr>
				<td>承兑人</td>
				<td style="width:260px;" class="bank">${bill.acceptor}</td>
				<td>客户名称</td>
				<td>${bill.billOwner}</td>
				<td>金额</td>
				<td>${fns:formateMoney(bill.billMoney)}</td>
			</tr>
			<tr>
				<td>出票日</td>
				<td>${bill.issueDt}</td>
				<td>票面到期日</td>
				<td style="width:137px;">${bill.dueDt}</td>
				<td>计息到期日</td>
				<td>${bill.galeDate}</td>
			</tr>
			<tr>
				<td>转入日</td>
				<td>${bill.rebuyDt}</td>
				<td>买入返售开放日</td>
				<td>${bill.resaleStaDt}</td>
				<td>买入返售到期日</td>
				<td>${bill.resaleDueDt}</td>
			</tr>
			<tr>
				<td>实付金额</td>
				<td>${fns:formateMoney(bill.payMoney)}</td>
				<td>同城异地</td>
				<td>${bill.isSameCity}</td>
				<td>计息天数</td>
				<td>${bill.interestDays}</td>
			</tr>
			<tr>
				<td>顺延天数</td>
				<td>${bill.delayDays}</td>
				<td>转入利率</td>
				<td>${apply.rate}</td>
				<td>成本利率</td>
				<td>${apply.cbRate}</td>
			</tr>
			<tr>
				<td>交易对手机构号</td>
				<td>${bill.acceptorBankNo}</td>
				<td>入账账号</td>
				<td>${bill.acceptorBankNo}</td>
				<td>票据前手</td>
				<td>${bill.acceptorBankNo}</td>
			</tr>
			<tr>
				<td style="width:144px;">出票人开户行行号</td>
				<td></td>
				<td>是否本行承兑</td>
				<td colspan="3">${bill.isAccpt}</td>
			</tr>
			<tr>
				<td>付款方地址</td>
				<td>${bill.acceptorBankNo}</td>
				<td>备注</td>
				<td colspan="3">${bill.remark}</td>
			</tr>
			<tr>
				<td colspan="6" class="center"><a class="btn-mini" onclick="top.Dialog.close();">关闭</a></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>