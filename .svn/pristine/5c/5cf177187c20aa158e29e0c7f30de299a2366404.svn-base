<%-- 
 * 文件名称: operation_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-21 上午09:28:22
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
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>tmonthCheck.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
					<label for="check_date">核对日期</label>
					<input class="input-medium" name="checkDate" id="checkDate" value="${mapField.check_date}" type="text" placeholder="核对日期"/>
					<label for="bill_no">票号</label>
					<input class="input-medium" type="text" name="billNo" value="${mapField.bill_no}" placeholder="请输入票号"/>
					<a class="btn-mini" id="search" onclick="searchd();">查询</a>
				</div>
					<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
				</form>
			</div>
			<%-- /查询区  --%>
		    <%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'billNo')">
								<input type="checkbox" id="zcheckbox"/>
							</th>
							<th class='center sort-column check_date'>核对日期</th>
							<th class='center sort-column bill_no'>票号</th>
							<th class='center sort-column draft_log_id'>报文日志</th>
							<th class='center sort-column error_code'>错误代码</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="tmonthCheck" varStatus="vs">
							<tr>
								<td class="center">
									<input type='checkbox' name='billNo' value="${tmonthCheck.billNo}"/>
								</td>
								<td class="center">${tmonthCheck.checkDate}</td>
								<td class="center">${tmonthCheck.billNo}</td>
								<td class="center">${tmonthCheck.draftLogId}</td>
								<td class="center">${tmonthCheck.errorCode}</td>
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
			</div>
		   	<%-- /列表操作区 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>tmonthCheck.do?method=list"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="billNo" value="${mapField.bill_no}" />
					<input type="hidden" name="checkDate" value="${mapField.check_date}" /> 
				</form>
			</div>	
	  	</div>
	</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#userForm").submit();
	}
</script>
</body>
</html>