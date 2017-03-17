<%-- 
 * 文件名称: rebu_batch_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
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
						<td>${rebuyApplyInfo.batchNo}</td>
						<td>利率</td>
						<td>${rebuyApplyInfo.rate}</td>
						<td>交易对手行号</td>
						<td>${rebuyApplyInfo.custBankNo}</td>
					</tr>
					<tr>
						<td>交易对手名称</td>
						<td>${rebuyApplyInfo.custBankName}</td>
						<td>机构</td>
						<td>${rebuyApplyInfo.branchNo}</td>
						<td>利率类型</td>
						<td>年利率</td>
					</tr>
					<tr>
						<td>票据类型</td>
						<td>${fns:getDictLabel('K_BILL_TYPE',rebuyApplyInfo.billType)}</td>
						<td>票据种类</td>
						<td>${fns:getDictLabel('K_BILL_CLASS',rebuyApplyInfo.billClass)}</td>
						<td>买入日</td>
						<td>${rebuyApplyInfo.rebuyDt}</td>
					</tr>
					<tr>
						<td>客户经理名称</td>
						<td>${rebuyApplyInfo.custManagerName}</td>
						<td>产品编号</td>
						<td>${rebuyApplyInfo.prodNo}</td>
						<td>成本利率</td>
						<td>${rebuyApplyInfo.cbRate}</td>
					</tr>
					<tr>
						<td>创建日期</td>
						<td>${rebuyApplyInfo.createDt}</td>
						<td>顺延方式</td>
						<td>${rebuyApplyInfo.delayType}</td>
						<td>顺延天数</td>
						<td>${rebuyApplyInfo.delayDays}</td>
					</tr>
					<tr>
						<td>部门号</td>
						<td>${rebuyApplyInfo.deptNo}</td>
						<td>部门名称</td>
						<td>${rebuyApplyInfo.deptName}</td>
						<td>买入返售到期日</td>
						<td>${rebuyApplyInfo.resaleDueDt}</td>
					</tr>
					<tr>
						<td>是否双向买断</td>
						<td>${rebuyApplyInfo.isBidirSale}</td>
						<td>双向买断到期日</td>
						<td>${rebuyApplyInfo.bidectDueDt}</td>
						<td>买入返售开放日</td>
						<td>${rebuyApplyInfo.resaleStaDt}</td>
					</tr>
					<tr>
						<td>是否系统内</td>
						<td>${rebuyApplyInfo.isInner}</td>
						<td>客户经理编号</td>
						<td>${rebuyApplyInfo.custManage}</td>
						<td>客户经理名称</td>
						<td>${rebuyApplyInfo.custManagerName}</td>
					</tr>
					<tr>
						<td>客户名称</td>
						<td>${rebuyApplyInfo.custName}</td>
						<td>入账账号</td>
						<td>${rebuyApplyInfo.tradeAcct}</td>
						<td>入账账号名称</td>
						<td>${rebuyApplyInfo.tradeAcctName}</td>
					</tr>
					<tr>
						<td>返售利率</td>
						<td>${rebuyApplyInfo.salebackRate}</td>
						<td>返售金额</td>
						<td>${fns:formateMoney(rebuyApplyInfo.salebackMoney)}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>