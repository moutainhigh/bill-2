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
						<td>批次号</td>
						<td>${saveApplyInfo.batchNo}</td>
						<td>操作柜员</td>
						<td>${saveApplyInfo.operNo}</td>
						<td>客户号</td>
						<td>${saveApplyInfo.custNo}</td>
						<td>客户名称</td>
						<td>${saveApplyInfo.custName}</td>
					</tr>
					<tr>
						<td>批次种类</td>
						<td>${fns:getDictLabel('K_BILL_CLASS',saveApplyInfo.batchClass)}</td>
						<td>批次类型</td>
						<td>${fns:getDictLabel('K_BILL_TYPE',saveApplyInfo.batchType)}</td>
						<td>创建日期</td>
						<td>${saveApplyInfo.createDate}</td>
						<td>创建时间</td>
						<td>${saveApplyInfo.createTime}</td>
					</tr>
					<tr>
						<td>客户经理编号</td>
						<td>${saveApplyInfo.custManager}</td>
						<td>客户经理名称</td>
						<td>${saveApplyInfo.custManagerName}</td>
						<td>部门名称</td>
						<td>${saveApplyInfo.deptName}</td>
						<td>签发机构</td>
						<td>${saveApplyInfo.branchNo}</td>
					</tr>
					<tr>
						<td>产品编号</td>
						<td>${saveApplyInfo.prodNo}</td>
						<td>是否贴查同步</td>
						<td>${saveApplyInfo.isTc}</td>
						<td>质押保证金账号</td>
						<td>${saveApplyInfo.impawnBailAccount}</td>
						<td>质押保证金户名</td>
						<td>${saveApplyInfo.impawnBailName}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>