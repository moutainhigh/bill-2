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
				<form action="<%=basePath%>taskLogController.do?method=search"
					id="page-content" method="post" name="searchForm" id="searchForm" class="form-search">
					<div class="row-fluid">
						<label class="no-padding-right" for="workday">营业日期</label>
						<input class="date-medium input-date"  name="minWorkday"id="minWorkday"  value ="${bean.minWorkday}" type="text" valid="dateISO"  placeholder="请输入营业日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
								<span class="wave-line">~</span>
						<input class="date-medium input-date"  name="maxWorkday"id="maxWorkday"  value ="${bean.maxWorkday}" valid="dateISO" type="text"  placeholder="请输入营业日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<label class="no-padding-right" for="taskName">作业名称</label>
						<input class="input-medium" type="text" id="taskName" name="taskName" class="col-md-16" value="${bean.taskName}"  placeholder="请输入作业名称" />
						<label class="no-padding-right" for="dealStatus">处理状态</label>
						<t:dictSelect className="select-medium" name="dealStatus" other="" dictGroup="K_TASKDEALSTATUS" defaultVal="${bean.dealStatus}" haveHead="true"></t:dictSelect>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a> 
					</div>
				</form>
			</div>
			<%-- /查询区  --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center">营业日期</th>
							<th class="center">作业名称</th>
							<th class="center">作业类型</th>
							<th class="center">开始时间</th>
							<th class="center">结束时间</th>  
							<th class="center">延时标志</th>
							<th class="center">延时时间</th>
							<th class="center">处理状态</th>
							<th class="center">处理信息</th>
							<th class="center">错误信息</th>
							<th class="center">生效标志</th>
							<th class="center">操作员编号</th>
							<th class="center">操作次数</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="log" varStatus="vs">
									<tr>
										<td class="center">${log.workday}</td>
										<td class="center">${log.taskName}</td>
										<td class="center">${fns:getDictLabel('K_TASKTYPE',log.taskType)}</td>
										<td class="center">${log.beginTime}</td>
										<td class="center">${log.endTime}</td>
										<td class="center">
										<c:if test="${log.delayFlag=='1'}">延迟处理</c:if>
										<c:if test="${log.delayFlag!='1'}">不延迟</c:if>
										</td>
										<td class="center">${log.delayTime}</td>
										<td class="center">${fns:getDictLabel('K_TASKDEALSTATUS',log.dealStatus)}</td>
										<td class="center">${log.dealMsg}</td>
										<td class="center">${log.errMsg}</td>
										<td class="center">
										<c:if test="${log.effectiveFlag=='1'}">生效</c:if>
										<c:if test="${log.effectiveFlag!='1'}">无效</c:if>
										</td>
										<td class="center">${log.operNo}</td>
										<td class="center">${log.repeatNum}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="100" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			<div>
		   	<%-- /列表操作区 --%>
		   	<%-- 分页 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>taskLogController.do?method=search" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="minWorkday" id="minWorkday" value="${bean.minWorkday}" />
					<input type="hidden" name="maxWorkday" id="maxWorkday" value="${bean.maxWorkday}" />
					<input type="hidden" name="taskName" id="taskName" value="${bean.taskName}" />
					<input type="hidden" name="dealStatus" id="dealStatus" value="${bean.dealStatus}" />
				</form>
			</div>
	   		<form  action="#" name="dataCollectForm" method="post"></form>
		</div>
  	</div>
</div>	
<%@ include file="../admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%> 
<script src="weblib/bizjs/checkDate.js"></script>
<script type="text/javascript">
	function searchd(){
		modal("#layer_loading,#image");
		$("#searchForm").submit();
	}
</script>
</body>
</html>