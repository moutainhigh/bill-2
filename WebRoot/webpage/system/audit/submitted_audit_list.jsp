<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<%-- jsp文件头和头部 --%>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action="<%=basePath%>submittedAuditController.do?method=search"  method="post" name="searchForm" id="searchForm" class="form-search">
					<%-- 查询区  --%>
					<div class="form-search">
						<label class="no-padding-right" for="atCreateDt">任务创建日期</label>
						<input class="date-medium input-date" name="minAtCreateDt" id="minAtCreateDt" value ="${bean.minAtCreateDt}" valid="required" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
						<span class="wave-line">~</span>
						<input class="date-medium input-date" name="maxAtCreateDt" id="maxAtCreateDt" value ="${bean.maxAtCreateDt}" valid="required" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
						<label class="no-padding-right" for="atStatus">审批状态</label>
						<t:dictSelect name="atStatus" className="select-medium" other="" dictGroup="K_AT_STATUS" defaultVal="${bean.atStatus}" haveHead="true"></t:dictSelect>
					</div>
					<div class="form-search">
						<label class="no-padding-right" for="atDoneDt">任务结束日期</label>
						<input class="date-medium input-date" name="minAtDoneDt" id="minAtDoneDt" value ="${bean.minAtDoneDt}" valid="required" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" >
						<span class="wave-line">~</span>
						<input class="date-medium input-date" name="maxAtDoneDt" id="maxAtDoneDt" value ="${bean.maxAtDoneDt}" valid="required" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
						<label class="no-padding-right" for="dealStatus">任务分类</label>
						<sys:treeselect id="prodNo" name="prodNo" value="${bean.prodNo}" module="0" allowClear="true" labelName="auditRoute.prodName" labelValue="${prodName }"
							title="产品" className="input-medium" placeholder="请选择任务分类" url="tagController.do?method=treeData" extId="${menu.menuCode}"  subnode="prod_no" 
							pnode = "parent_prod_no" nodename="prod_name" sourcename="tproduct"/>
						<a class="btn-mini"  id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center">任务编号</th>
							<th class="center">任务分类</th>
							<th class="center">审批状态</th>
							<th class="center">批次编号</th>
							<th class="center">待审批金额</th>
							<th class="center">任务创建日期</th>
							<th class="center">任务创建时间</th>
							<th class="center">任务结束日期</th>
							<th class="center">任务结束时间</th>
							<th class="center">详情</th>
						</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="task" varStatus="vs">
										<tr>
											<td class="center">${task.id}</td>
											<td class="center">${fns:getTableLabel('tproduct','prod_no','prod_name',task.prodNo)}</td>
											<td class="center">${fns:getDictLabel('K_AT_STATUS',task.atStatus)}</td>
											<td class="center">${task.batchNo}</td>
											<td class="center">${fns:formateMoney(task.waitAuditAmt)}</td>
											<td class="center">${task.atCreateDt}</td>
											<td class="center">${task.atCreateTime}</td>
											<td class="center">${task.atDoneDt}</td>
											<td class="center">${task.atDoneTime}</td>
											<td class="center">
												<a href="javascript:goDetailInfo('${task.id}')">查看</a>
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
			</div>
			<form  action="#" name="dataCollectForm" method="post"></form>
	 		<%-- /列表操作区 --%>
	 		<div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>submittedAuditController.do?method=search"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="minAtCreateDt" value="${bean.minAtCreateDt }">
					<input type="hidden" name="maxAtCreateDt" value="${bean.maxAtCreateDt }">
					<input type="hidden" name="minAtDoneDt" value="${bean.minAtDoneDt }">
					<input type="hidden" name="maxAtDoneDt" value="${bean.maxAtDoneDt }">
					<input type="hidden" name="prodNo" value="${bean.prodNo }">
					<input type="hidden" name="atStatus" value="${bean.atStatus }">
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function searchd(){
		modal("#layer_loading,#image");
		searchForm.submit();
	}
	function goDetailInfo(id){
		modal("#layer_loading,#image");
	  	dynamicHiddenElement('dataCollectForm','id',id);
	  	dynamicHiddenElement('dataCollectForm','checkFlag','submittedAudit');
		dataCollectForm.action="<%=basePath%>submittedAuditController.do?method=goDetailInfo";
		dataCollectForm.submit();
	}
</script>
</body>
</html>