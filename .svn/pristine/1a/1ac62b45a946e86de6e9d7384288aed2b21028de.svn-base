<%-- 
 * 文件名称: busidate_list.jsp
 * 系统名称: 
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2017-1-5 下午16:28:22
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
			<div id="center">
				<form action="<%=basePath%>busiDateController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
				<%-- 按钮操作区 --%>
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="edit();">修改</a>
			   			</div>
			   			<div class="span6" id="btn-right"></div>
		  			</div>
		  		</form>
		  	</div>
		    <%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'sysBankNos')"></th>
							<th class='center sort-column workday'>系统日期</th>
							<th class='center sort-column host_check_date'>核心对账日期</th>
							<th class='center sort-column sys_status'>系统状态</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="busidate" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='sysBankNos' value="${busidate.sysBankNo}" checked="checked"/>
									</td>
									<td class="center">${busidate.workday}</td>
									<td class="center">${busidate.hostCheckDate}</td>
									<td class="center">${fns:getDictLabel('K_SYSSTATUS',busidate.sysStatus)}</td>
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
			<form  action="#" name="dataCollectForm" method="post"></form>
		    <div id="page" class="pagination">
				<form action="<%=basePath%>busiDateController.do?method=list"  name="pageForm" method="post" >
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				</form>
			</div>
		</div>	
	</div>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//修改
	function edit(){
		 var checkNum = getCheckNum("sysBankNos");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var sysBankNos = getCheckStr("sysBankNos");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>busiDateController.do?method=toEdit&sys_bank_no='+sysBankNos;
		 diag.Width = 480;
		 diag.Height = 230;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>