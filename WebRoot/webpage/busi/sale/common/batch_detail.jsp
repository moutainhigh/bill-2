<%-- 
 * 文件名称: batch_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: 
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
				<td>批次号</td>
				<td>${saleApplyInfo.batchNo}</td>
				<td>利率</td>
				<td>${fns:formateRate(saleApplyInfo.rate)}%</td>
				<td>票据总张数</td>
				<td>${saleApplyInfo.totalSize}</td>
			</tr>
			<tr>
				<td>票面总金额</td>
				<td>${fns:formateMoney(saleApplyInfo.sumMoney)}</td>
				<td>交易机构</td>
				<td>${saleApplyInfo.branchNo}</td>
				<td>利率类型</td>
				<td>${fns:getDictLabel('K_RATETYPE',saleApplyInfo.rateType)}</td>
			</tr>
			<tr>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',saleApplyInfo.billType)}</td>
				<td>票据种类</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',saleApplyInfo.billClass)}</td>
				<td>转卖日</td>
				<td>${saleApplyInfo.saleDt}</td>
			</tr>
			<tr>
				<td>转卖类型</td>
				<td>${saleApplyInfo.saleType}</td>
				<td>是否系统内</td>
				<td>${fns:getDictLabel('K_YORN',saleApplyInfo.isInner)}</td>
				<td>是否线上清算</td>
				<td>${fns:getDictLabel('K_ISONLINE',saleApplyInfo.isOnline)}</td>
			</tr>
			<tr>
				<td>禁止背书标志</td>
				<td>${fns:getDictLabel('K_YORN',saleApplyInfo.forbidFlag)}</td>
				<td>顺延方式</td>
				<td>${fns:getDictLabel('K_DELAYTYPE',saleApplyInfo.delayType)}</td>
				<td>顺延天数</td>
				<td>${saleApplyInfo.delayDays}</td>
			</tr>
			<tr>
				<td>客户类型</td>
				<td>${fns:getDictLabel('K_KHLX',saleApplyInfo.custType)}</td>
				<td>交易对手名称</td>
				<td>${saleApplyInfo.custName}</td>
				<td>交易对手机构</td>
				<td>${saleApplyInfo.aimBranchNo}</td>
			</tr>
			<tr>
				<td>入账帐号</td>
				<td>${saleApplyInfo.inAcctNo}</td>
				<td>入账账户名称</td>
				<td>${saleApplyInfo.inAcctName}</td>
				<td>实际收款行行号</td>
				<td>${saleApplyInfo.factBankNo}</td>
			</tr>
			<tr>
				<td>实际收款行行名</td>
				<td>${saleApplyInfo.factBankName}</td>
				<td>经营机构编号</td>
				<td>${saleApplyInfo.workingadsNo}</td>
				<td>经营机构名称</td>
				<td>${saleApplyInfo.workingadsName}</td>
			</tr>
			<tr>
				<td>订单编号</td>
				<td>${saleApplyInfo.orderId}</td>
				<td>财务公司客户号</td>
				<td>${saleApplyInfo.financialCustNo}</td>
				<td>产品编号</td>
				<td>${saleApplyInfo.prodNo}</td>
			</tr>
			<tr>
				<td>入账账户类型</td>
				<td>${saleApplyInfo.inAcctType}</td>
				<td>客户号</td>
				<td>${saleApplyInfo.custNo}</td>
				<td>客户名称</td>
				<td>${saleApplyInfo.custName}</td>
			</tr>
			<tr>
				<td>客户经理编号</td>
				<td>${saleApplyInfo.custManage}</td>
				<td>客户经理名称</td>
				<td>${saleApplyInfo.custManagerName}</td>
				<td>部门名称</td>
				<td>${saleApplyInfo.deptName}</td>
			</tr>
			<tr>
				<td>当前操作员所在机构号</td>
				<td>${saleApplyInfo.billStorageBrchno}</td>
				<td>当前操作员所在机构名称</td>
				<td>${saleApplyInfo.billStorageName}</td>
				<td>操作柜员</td>
				<td>${saleApplyInfo.operNo}</td>
			</tr>
			<tr>
				<td>创建日期</td>
				<td>${saleApplyInfo.createDt}</td>
				<td>创建时间</td>
				<td>${saleApplyInfo.createTime}</td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>