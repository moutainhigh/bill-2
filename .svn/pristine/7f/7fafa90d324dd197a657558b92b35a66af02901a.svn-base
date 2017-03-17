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
				<form action="<%=basePath%>participantAuditController.do?method=search"  method="post" name="searchForm" id="searchForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="apExecResult">审批结果</label>
						<t:dictSelect className="select-medium" name="apExecResult" other="" dictGroup="K_AP_EXEC_RESULT" defaultVal="${bean.apExecResult}" haveHead="true"></t:dictSelect>
						<label class="no-padding-right" for="apDoneDt">审批日期</label>
						<input class="date-medium input-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="minApDoneDt"id="minApDoneDt" value ="${bean.minApDoneDt}"readonly="readonly" valid="required" type="text">
						<span class="wave-line">~</span>
						<input class="date-medium input-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="maxApDoneDt"id="maxApDoneDt" value ="${bean.maxApDoneDt}"readonly="readonly" valid="required" type="text">
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1500px;width:1500px;">
					<thead>
						<tr>
							<th class="center">任务编号</th>
							<th class="center">任务分类</th>
							<th class="center">审批提交人</th>
							<th class="center">审批提交人所在机构</th>
							<th class="center">审批提交人意见</th>
							<th class="center">待审批金额</th>
							<th class="center">审批状态</th>
							<th class="center">审批结果</th>
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
								<c:forEach items="${resultList}" var="apDto" varStatus="vs">
									<tr>
										<td class="center">${apDto.atId}</td>
										<td class="center">${apDto.prodName}</td>
										<td class="center">${apDto.apCommitPersonName}</td>
										<td class="center">${apDto.apCommitBrchNo}</td>
										<td class="center">${apDto.apCommitRemark}</td>
										<td class="center">${fns:formateMoney(apDto.waitAuditAmt)}</td>
										<td class="center">${fns:getDictLabel('K_AP_STATUS',apDto.apStatus)}</td>
										<td class="center">${fns:getDictLabel('K_AP_EXEC_RESULT',apDto.apExecResult)}</td>
										<td class="center">${apDto.atCreateDt}</td>
										<td class="center">${apDto.atCreateTime}</td>
										<td class="center">${apDto.atDoneDt}</td>
										<td class="center">${apDto.atDoneTime}</td>
										<td class="center">
											<a href="javascript:goDetailInfo('${apDto.atId}')">查看</a>
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
					<form action="<%=basePath%>participantAuditController.do?method=search"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="apExecResult" value="${bean.apExecResult }">
					<input type="hidden" name="apDoneDt" value="${bean.minApDoneDt }">
					<input type="hidden" name="apDoneDt" value="${bean.maxApDoneDt }">
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function searchd(){
		searchForm.submit();
	}
	function goDetailInfo(id){
		modal("#layer_loading,#image");
	  	dynamicHiddenElement('dataCollectForm','id',id);
	  	dynamicHiddenElement('dataCollectForm','checkFlag','participantAudit');
		dataCollectForm.action="<%=basePath%>submittedAuditController.do?method=goDetailInfo";
		dataCollectForm.submit();
	}
</script>
</body>
</html>