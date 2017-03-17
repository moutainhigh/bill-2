<%-- 
 * 文件名称: custinfo_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wyl
 * 开发时间: 2016-7-7 上午06:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%>
	</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 列表操作区 --%>
			<div id="footer">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">序号</th>
						<th class='center '>客户名称</th>
						<th class='center '>客户账号</th>
						<th class='center '>开户行行号</th>
						<th class='center '>开户机构机构号</th>
						<th class='center '>开户机构名称</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty resultList}">
						<c:forEach items="${resultList}" var="resultList" varStatus="vs">
							<tr>
								<td class="center">${vs.index+1}</td>
								<td class="center">${custName}</td>
								<td class="center">${resultList.acctNo}</td>
								<td class="center">${resultList.acctBankNo}</td>
								<td class="center">${resultList.acctBranchNo}</td>
								<td class="center">${resultList.acctBranchName}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="100" class="center">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		<div>
	   <%-- /列表操作区 --%>
  	  </div>
	</div>	
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%>
</body>
</html>