<%-- 
 * 文件名称: buyback_batch_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-11-05
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
						<td>${info.batchNo}</td>
						<td>回购利率</td>
						<td>${info.buybackRate}%</td>
						<td>利率类型</td>
						<td>年利率</td>
					</tr>
					<tr>
						<td>产品编号</td>
						<td>${info.prodNo}</td>
						<td>申请种类</td>
						<td>${fns:getDictLabel('K_BILL_CLASS',info.billClass)}</td>
						<td>申请票据类型</td>
						<td>${fns:getDictLabel('K_BILL_TYPE',info.billType)}</td>
					</tr>

					<tr>
						<td>顺延方式</td>
						<td>${info.delayType}</td>
						<td>赎回截止日</td>
						<td>${info.buybackDueDt}</td>
						<td>创建时间</td>
						<td>${info.createTime}</td>
					</tr>
					<tr>
						<td>赎回开放日</td>
						<td>${info.buybackOpenDt}</td>
						<td>客户名称</td>
						<td>${info.custName}</td>
						<td>订单编号</td>
						<td>${info.orderId}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>