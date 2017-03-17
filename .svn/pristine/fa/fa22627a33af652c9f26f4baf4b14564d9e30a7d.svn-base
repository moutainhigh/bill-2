<%-- 
 * 文件名称: collate_batch_detail.jsp
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
						<td>${saveBillInfo.billNo}</td>
						<td>票据类型</td>
						<td>${fns:getDictLabel('K_BILL_TYPE',saveBillInfo.billType)}</td>
						<td>票据种类</td>
						<td>${fns:getDictLabel('K_BILL_CLASS',saveBillInfo.billClass)}</td>
						<td>票面金额</td>
						<td colspan="2">${fns:formateMoney(saveBillInfo.billMoney)}</td>
					</tr>
					<tr>
					    <td>出票日</td>
						<td>${saveBillInfo.issueDt}</td>
						<td>到期日</td>
						<td>${saveBillInfo.dueDt}</td>
						<td>创建日期</td>
						<td>${saveBillInfo.createDt}</td>
						<td>创建时间</td>
						<td colspan="2">${saveBillInfo.createTime}</td>
					</tr>
					<tr>
					    <td rowspan="3" class="line">出<br/>票<br/>人</td>
						<td>名称</td>
						<td>${saveBillInfo.remitter}</td>
						<td rowspan="3" class="line">收<br/>款<br/>人</td>
						<td>名称</td>
						<td>${saveBillInfo.payee}</td>
						<td rowspan="3" class="line">承<br/>兑<br/>人</td>
						<td>名称</td>
						<td>${saveBillInfo.acceptor}</td>
					</tr>
					<tr>
						<td>账号</td>
						<td>${saveBillInfo.remitterAcct}</td>
						<td>账号</td>
						<td>${saveBillInfo.payeeAcct}</td>
						<td>行号</td>
						<td>${saveBillInfo.acceptorBankNo}</td>
					</tr>
					<tr>
						<td>行名</td>
						<td>${saveBillInfo.remitterBankName}</td>
						<td>开户行名称</td>
						<td>${saveBillInfo.payeeBankName}</td>
						<td>开户行名称</td>
						<td>${saveBillInfo.acceptorBankName}</td>
					</tr>
					<tr>
						<td>操作机构</td>
						<td>${saveBillInfo.branchNo}</td>
						<td>交易前手</td>
						<td>${saveBillInfo.billBeforeOwner}</td>
						<td>票据来源</td>
						<td>${saveBillInfo.billSource}</td>
						<td colspan="3"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>