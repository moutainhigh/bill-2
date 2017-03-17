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
			<%--按钮操作区  --%>
			<div id="center">
				<div class="span6" id="btn-left">
			   	 	<a class="btn-mini"  onclick="showTask('1');">日终准备中</a>
					<a class="btn-mini" onclick="showTask('2');">日终清算中</a>
					<a class="btn-mini"  onclick="showTask('3');">日终初始化</a>
				 </div>
			</div> 
			<%-- 列表操作区 --%>
			<div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center">作业名称</th>
							<th class="center">延时标志</th>
							<th class="center">延时时间</th>
							<th class="center">生效标志</th>
							<th class="center">重复执行标志</th>
							<th class="center">处理状态</th>
							<th class="center">处理信息</th>
							<th class="center">错误信息</th>
							<th class="center">开始时间</th>
							<th class="center">结束时间</th>  
							<th class="center">操作次数</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="pool"
									varStatus="vs">
									<tr>
										<td class="center">${pool.taskName}</td>
										<td class="center">
											<c:if test="${pool.delayFlag=='1'}">延迟处理</c:if>
											<c:if test="${pool.delayFlag!='1'}">不延迟</c:if>
										</td>
										<td class="center">${pool.delayTime}</td>
										<td class="center">
											<c:if test="${pool.effectiveFlag=='1'}">生效</c:if>
											<c:if test="${pool.effectiveFlag!='1'}">无效</c:if>
										</td>
										<td class="center">
											<c:if test="${pool.repeatFlag=='1'}">允许</c:if>
											<c:if test="${pool.repeatFlag!='1'}">不允许</c:if>
										</td>
										<td class="center">${fns:getDictLabel('K_TASKDEALSTATUS',pool.dealStatus)}</td>
										<td class="center">${pool.dealMsg}</td>
										<td class="center">${pool.errMsg}</td>
										<td class="center">${pool.beginTime}</td>
										<td class="center">${pool.endTime}</td>
										<td class="center">${pool.repeatNum}</td>
										<td class="center">
											<a  onclick="execute('${pool.taskNo}','${pool.taskType}');">执行</a>
										</td>
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
		<div>
	   		<form  action="#" name="dataCollectForm" method="post"></form>
		</div>
  	</div>
</div>	
<%@ include file="../admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	function showTask(taskType){
		dynamicHiddenElement('dataCollectForm','taskType',taskType);
		dataCollectForm.action = "<%=basePath%>dayendController.do?method=taskMonitor";
		modal("#layer_loading,#image");
		dataCollectForm.submit();
	}
	function execute(taskNo,taskType){
		bootbox.confirm("是否确定要执行选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>dayendController.do?method=executeTask',
					data: {'taskNo': taskNo,'taskType': taskType},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							bootbox.alert(data.msg); 
						} else {
							top.hangge();
							bootbox.alert(data.msg); 
						}
						showTask(taskType);
					}
				});
			}
		});
	}
</script>
</body>
</html>