<%-- 
 * 文件名称: repurcollate_batch_detail.jsp
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
				<td>批次号</td>
				<td>${getApplyInfo.batchNo}</td>
				<td>客户号</td>
				<td>${getApplyInfo.custNo}</td>
				<td>客户名称</td>
				<td>${getApplyInfo.custName}</td>
				<td>产品编号</td>
				<td>${getApplyInfo.prodNo}</td>
			</tr>
			<tr>
				<td>批次种类</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',getApplyInfo.batchClass)}</td>
				<td>批次类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',getApplyInfo.batchType)}</td>
				<td>创建日期</td>
				<td>${getApplyInfo.createDt}</td>
				<td>创建时间</td>
				<td>${getApplyInfo.createTime}</td>
			</tr>
			<tr>
				<td>客户经理编号</td>
				<td>${getApplyInfo.custManager}</td>
				<td>客户经理名称</td>
				<td>${getApplyInfo.custManagerName}</td>
				<td>部门名称</td>
				<td>${getApplyInfo.deptName}</td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>