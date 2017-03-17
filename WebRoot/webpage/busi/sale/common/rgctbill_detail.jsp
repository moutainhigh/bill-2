<%-- 
 * 文件名称: rgctbill_detail.jsp
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
<body style="font-family:'微软雅黑';">
<div class="page-content" id="jump-content">
	<table class="table table-bordered table-condensed" id="tab1" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td colspan="2" style="padding-left:18px">票号</td>
				<td colspan="4">${bill.info.billNo }</td>
			</tr>
			<tr>
				<td rowspan="3" class="line">出<br/>票<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwids">${bill.info.remitter }</td>
				<td rowspan="3" class="line">收<br/>款<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwid">${bill.info.payeeName }</td>
			</tr>
			<tr>
				<td class="wid">账号</td>
				<td class="lenwids">${bill.info.remitterAcct }</td>
				<td class="wid">账号</td>
				<td class="lenwid">${bill.info.payeeAcct }</td>
			</tr>
			<tr>
				<td class="wid">开户行</td>
				<td class="lenwids">${bill.info.remitterBankName }</td>
				<td class="wid">开户行</td>
				<td class="lenwid">${bill.info.payeeBankName }</td>
			</tr>
			
			<tr>
				<td rowspan="3" class="line">付<br/>款<br/>人</td>
				<td class="wid">开户行行号</td>
				<td class="lenwids">${bill.info.draweeBankNo }</td>
				<td rowspan="3" class="line">承<br/>兑<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwid">${bill.info.acceptor }</td>
			</tr>
			<tr>
				<td class="wid">开户行行名</td>
				<td class="lenwids">${bill.info.draweeBankName }</td>
				<td class="wid">账号</td>
				<td class="lenwid">${bill.info.acceptorAcct }</td>
			</tr>
			<tr>
				<td class="wid">开户行地址</td>
				<td class="lenwids">${bill.info.draweeAddr }</td>
				<td class="wid">开户行</td>
				<td class="lenwid">${bill.info.acceptorBankName }</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-condensed" id="tab2" cellpadding="0" cellspacing="0">
		<tbody>
			
			<tr>
				<td>票据品种</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',bill.info.billClass)}</td>
				<td>票据来源</td>
				<td>${bill.info.billSource }</td>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',bill.info.billType)}</td>
			</tr>
			<tr>
				<td>出票日</td>
				<td>${bill.info.issueDt }</td>
				<td>票面到期日</td>
				<td style="width:137px;">${bill.info.dueDt }</td>
				<td>金额</td>
				<td>${fns:formateMoney(bill.info.billMoney)}</td>
			</tr>
			<tr>
				<td>交易合同编号</td>
				<td>${bill.info.conferNo }</td>
				<td>正面禁止背书标志</td>
				<td>${fns:getDictLabel('K_YORN',bill.info.infoForbidFlag)}</td>
				<td>票据用途</td>
				<td>${bill.info.billUsage }</td>
			</tr>
			<tr>
				<td>发票号码</td>
				<td>${bill.info.invoiceNo}</td>
			</tr>
			<tr>
				<td colspan="6" class="center"><a class="btn-mini" onclick="top.Dialog.close();">关闭</a></td>
			</tr>
		</tbody>
	</table>
	</div>
</body>
</html>