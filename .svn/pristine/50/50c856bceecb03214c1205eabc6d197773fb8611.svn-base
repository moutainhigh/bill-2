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
				<td>批次号</td>
				<td>${intoApplyInfo.batchNo}</td>
				<td>操作柜员</td>
				<td>${intoApplyInfo.operNo}</td>
				<td>客户号</td>
				<td>${intoApplyInfo.custNo}</td>
				<td>客户名称</td>
				<td>${intoApplyInfo.custName}</td>
			</tr>
			<tr>
				<td>批次种类</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',intoApplyInfo.batchClass)}</td>
				<td>批次类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',intoApplyInfo.batchType)}</td>
				<td>创建日期</td>
				<td>${intoApplyInfo.createDate}</td>
				<td>创建时间</td>
				<td>${intoApplyInfo.createTime}</td>
			</tr>
			<tr>
				<td>客户经理编号</td>
				<td>${intoApplyInfo.custManager}</td>
				<td>客户经理名称</td>
				<td>${intoApplyInfo.custManagerName}</td>
				<td>部门名称</td>
				<td>${intoApplyInfo.deptName}</td>
				<td>签发机构</td>
				<td>${intoApplyInfo.branchNo}</td>
			</tr>
			<tr>
				<td>产品编号</td>
				<td>${intoApplyInfo.prodNo}</td>
				<td>是否贴查同步</td>
				<td>${fns:getDictLabel('K_IS_TC',intoApplyInfo.isTc)}</td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>