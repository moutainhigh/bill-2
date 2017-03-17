<%-- 
 * 文件名称: bill_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
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
						<td>${discApplyInfo.batchNo}</td>
						<td>利率</td>
						<td>${discApplyInfo.rate}%</td>
						<td>付息比例</td>
						<td>${discApplyInfo.buyPayRate}%</td>
					</tr>
					<tr>
						<td>客户账号</td>
						<td>${discApplyInfo.custAccount}</td>
						<td>机构</td>
						<td>${discApplyInfo.branchNo}</td>
						<td>付息方式</td>
						<td>${fns:getDictLabel('K_PAY_TYPE',discApplyInfo.payType)}</td>
					</tr>
					<tr>
						<td>付息人行号</td>
						<td>${discApplyInfo.payBankNo}</td>
						<td>付息人名称</td>
						<td>${discApplyInfo.payCustName}</td>
						<td>付息人账号</td>
						<td>${discApplyInfo.payAccount}</td>
					</tr>
					<tr>
						<td>产品编号</td>
						<td>${discApplyInfo.prodNo}</td>
						<td>利率类型</td>
						<td>年利率</td>
						<td>申请票据类型</td>
						<td>${fns:getDictLabel('K_BILL_TYPE',discApplyInfo.billType)}</td>
					</tr>
					<tr>	
						<td>申请种类</td>
						<td>${fns:getDictLabel('K_BILL_CLASS',discApplyInfo.billClass)}</td>
						<td>贴现日</td>
						<td>${discApplyInfo.discDt}</td>
						<td>客户经理名称</td>
						<td>${discApplyInfo.custManagerName}</td>
					</tr>
					<tr>
						<td>是否贴查同步</td>
						<td>${discApplyInfo.isTc}</td>
						<td>代理贴现人名称</td>
						<td>${discApplyInfo.agentCustName}</td>
						<td>代理贴现人账号</td>
						<td>${discApplyInfo.agentCustAccount}</td>
					</tr>
					<tr>
						<td>顺延方式</td>
						<td>${discApplyInfo.delayType}</td>
						<td>赎回截止日</td>
						<td>${discApplyInfo.redemptionDt}</td>
						<td>部门号</td>
						<td>${discApplyInfo.deptNo}</td>
					</tr>
					<tr>
						<td>创建时间</td>
						<td>${discApplyInfo.createTime}</td>
						<td>部门名称</td>
						<td>${discApplyInfo.deptName}</td>
						<td>赎回开放日</td>
						<td>${discApplyInfo.redeemDate}</td>
					</tr>
					<tr>
						<td>客户名称</td>
						<td>${discApplyInfo.custName}</td>
						<td>委托人名称</td>
						<td>${discApplyInfo.trusteeName}</td>
						<td>委托人他行账号</td>
						<td>${discApplyInfo.trusteeAcct}</td>
					</tr>
					<tr>
						<td>委托人他行行名</td>
						<td>${discApplyInfo.trusteeBankName}</td>
						<td>委托人他行行号</td>
						<td>${discApplyInfo.trusteeBankNo}</td>
						<td>经营机构归属名</td>
						<td>${discApplyInfo.profOwnerNo}</td>
					</tr>
					<tr>
						<td>经营机构归属号</td>
						<td>${discApplyInfo.profOwner}</td>
						<td>归属客户名称</td>
						<td>${discApplyInfo.billOwner}</td>
						<td>经营机构归属</td>
						<td>${discApplyInfo.workingadsNo1}</td>
					</tr>
					<tr>
						<td>成本利率</td>
						<td>${discApplyInfo.cbRate}%</td>
						<td>银行产品编号</td>
						<td>${discApplyInfo.bankProdNo}</td>
						<td>经营机构id</td>
						<td>${discApplyInfo.workingadsNo}</td>
					</tr>
					<tr>
						<td>签发机构</td>
						<td>${discApplyInfo.signBranchNo}</td>
						<td>客户经理编号</td>
						<td>${discApplyInfo.custManage}</td>
						<td>客户号</td>
						<td>${discApplyInfo.custNo}</td>
					</tr>
					<tr>
						<td>买方付息账号类型</td>
						<td>${fns:getDictLabel('K_ZHLX',discApplyInfo.payAccountType)}</td>
						<td>订单编号</td>
						<td>${discApplyInfo.orderId}</td>
						<td>卖方账号类型</td>
						<td>${discApplyInfo.custAccountType}</td>
					</tr>
					<tr>
						<td>账务机构号</td>
						<td>${discApplyInfo.acctBranchNo}</td>
						<td>产品业务类型</td>
						<td>${discApplyInfo.prodBusiType}</td>
						<td>创建日期</td>
						<td>${discApplyInfo.createDt}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>