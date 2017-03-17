<%-- 
 * 文件名称: disc_bill_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
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
				<td colspan="2" style="padding-left:18px">出票日期</td>
				<td colspan="4">${resultList.issueDt}</td>
			</tr>
			<tr>
				<td rowspan="3" class="line">出<br/>票<br/>人</td>
				<td class="wid">出票人全称</td>
				<td class="lenwids">${resultList.remitter}</td>
				<td rowspan="3" class="line">收<br/>款<br/>人</td>
				<td class="wid">收款人全称</td>
				<td class="lenwid">${resultList.payeeName}</td>
			</tr>
			<tr>
				<td class="wid" style="width:123px;">出票人账号</td>
				<td class="lenwids">${resultList.remitterAcct}</td>
				<td class="wid" style="width:123px;">收款人账号</td>
				<td class="lenwid">${resultList.payeeAcct}</td>
			</tr>
			<tr>
				<td class="wid" style="width:123px;">出票人开户银行</td>
				<td class="lenwids">${resultList.remitterBankName}</td>
				<td class="wid" style="width:123px;">收款人开户银行</td>
				<td class="lenwid">${resultList.payeeBankName}</td>
			</tr>
			<tr>
				<td colspan="2">出票金额</td>
				<td colspan="4">${fns:formateMoney(resultList.billMoney)}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-condensed" id="tab2" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>汇票到期日</td>
				<td>${resultList.dueDt}</td>
				<td>付款行行号</td>
				<td>${resultList.draweeBankNo}</td>
			</tr>
			<tr>
				<td>合同文本编号</td>
				<td>${resultList.conferNo}</td>
				<td>付款行地址</td>
				<td>${resultList.draweeAddr}</td>
			</tr>
			<tr>
				<td>承兑行/人</td>
				<td>${resultList.acceptor}</td>
				<td>付款行全称</td>
				<td>${resultList.draweeBankName}</td>
			</tr>
			<tr>
				<td>承兑人开户行</td>
				<td>${resultList.acceptorBankName}</td>
				<td>备注</td>
				<td>${resultList.remark}</td>
			</tr>
			<tr>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',resultList.billType)}</td>
				<td>票据品种</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',resultList.billClass)}</td>
			</tr>
			<tr>
				<td colspan="6" class="center"><a class="btn-mini" onclick="top.Dialog.close();">关闭</a></td>
			</tr>
		</tbody>
	</table>
	</div>
</body>
</html>