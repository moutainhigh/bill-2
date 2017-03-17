<%-- 
 * 文件名称: into_batch_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
 * 开发时间: 2016-09-05
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
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-family:'微软雅黑';background:#f4f8fb;">
<div class="clearfix">
		<div id="jump-content" class="page-content">
	<table class="table table-bordered" id="" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>票号</td>
				<td>${intoBillInfo.billNo}</td>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',intoBillInfo.billType)}</td>
				<td>票据种类</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',intoBillInfo.billClass)}</td>
				<td>票面金额</td>
				<td colspan="2">${fns:formateMoney(intoBillInfo.billMoney)}</td>
			</tr>
			<tr>
			    <td>出票日</td>
				<td>${intoBillInfo.issueDt}</td>
				<td>到期日</td>
				<td>${intoBillInfo.dueDt}</td>
				<td>创建日期</td>
				<td>${intoBillInfo.createDt}</td>
				<td>创建时间</td>
				<td colspan="2">${intoBillInfo.createTime}</td>
			</tr>
			<tr>
			    <td rowspan="3" class="line">出<br/>票<br/>人</td>
				<td>名称</td>
				<td>${intoBillInfo.remitter}</td>
				<td rowspan="3" class="line">收<br/>款<br/>人</td>
				<td>名称</td>
				<td>${intoBillInfo.payee}</td>
				<td rowspan="3" class="line">承<br/>兑<br/>人</td>
				<td>名称</td>
				<td>${intoBillInfo.acceptor}</td>
			</tr>
			<tr>
				<td>账号</td>
				<td>${intoBillInfo.remitterAcct}</td>
				<td>账号</td>
				<td>${intoBillInfo.payeeAcct}</td>
				<td>行号</td>
				<td>${intoBillInfo.acceptorBankNo}</td>
			</tr>
			<tr>
				<td>行名</td>
				<td>${intoBillInfo.remitterBankName}</td>
				<td>开户行名称</td>
				<td>${intoBillInfo.payeeBankName}</td>
				<td>开户行名称</td>
				<td>${intoBillInfo.acceptorBankName}</td>
			</tr>
			<tr>
				<td>操作机构</td>
				<td>${intoBillInfo.branchNo}</td>
				<td>交易前手</td>
				<td>${intoBillInfo.billBeforeOwner}</td>
				<td>票据来源</td>
				<td>${intoBillInfo.billSource}</td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>