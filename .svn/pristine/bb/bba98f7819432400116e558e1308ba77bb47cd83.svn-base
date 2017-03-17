<%-- 
 * 文件名称: logs_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-7-12
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/poseui.tld"%>
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
				<form action="<%=basePath%>logsController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="log_serial" >日志编号</label>
						<input type="text" class="input-medium" name="log_serial" value="${mapField.log_serial}" placeholder="请输入日志编号"/>
						<label for="user_id">用户编号</label>
						<input type="text" class="input-medium" name=user_id value="${mapField.user_id}" placeholder="请输入用户编号"/>
						<label for="value_name" class="col-md-1 control-label text-right">操作日期</label>
						<input class="input-date date-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="startDay" id="startDay" value="${mapField.startDay}" type="text" placeholder="开始日期"/>
						<span style="margin:4px 5px 0 5px;">~</span>	
						<input class="input-date date-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="endDay" id="endDay" value="${mapField.endDay}" type="text" placeholder="结束日期"/>
					</div>
					<div class="row-fluid">
						<label for="auth_user">授权用户</label>
						<input type="text" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'}); name="auth_user" value="${mapField.auth_user}" placeholder="请输入授权用户"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
					<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
				</form>
			</div>
			<%-- /查询区  --%>	
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center sort-column log_serial'>日志编号</th>
							<th class='center sort-column user_id'>用户编号</th>
							<th class='center sort-column menu_code'>菜单代码</th>
							<th class='center sort-column operation_code'>按钮编码</th>
							<th class='center sort-column terminal_no'>终端编号</th>
							<th class='center sort-column op_date'>操作日期</th>
							<th class='center sort-column op_time'>操作时间</th>
							<th class='center sort-column auth_user'>授权用户</th>
							<th class='center sort-column ip'>发起IP</th>
							<th class="center">日志摘要</th>
							<th class="center">日志文件</th>
							<th class="center">浏览器类型</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="logs" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${logs.logSerial}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${logs.logSerial}</td>
									<td class="center">${logs.userId}</td>
									<td class="center">${logs.menuCode}</td>
									<td class="center">${logs.operationCode}</td>
									<td class="center">${logs.terminalNo}</td>
									<td class="center">${logs.opDate}</td>
									<td class="center">${logs.opTime}</td>
									<td class="center">${logs.authUser}</td>
									<td class="center">${logs.ip}</td>
									<td class="center">${logs.summary}</td>
									<td class="center">${logs.logFile}</td>
									<td class="center">${logs.broswer}</td>
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
			<form action="<%=basePath%>logsController.do?method=list"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="log_serial" value="${mapField.log_serial}" />
				<input type="hidden" name="user_id" value="${mapField.user_id}" />
				<input type="hidden" name="startDay" value="${mapField.startDay}" />
				<input type="hidden" name="endDay" value="${mapField.endDay}" />
				<input type="hidden" name="auth_user" value="${mapField.auth_user}" />
			</form>
  	</div>
</div>	
<%@ include file="../admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("log_serial").readOnly = true;
	}
	$(top.hangge());
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#userForm").submit();
	}
    function page(){
		$("#userForm").submit();
		return false;
    }
</script>
</body>
</html>