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
				<form action="<%=basePath%>auditProcessController.do?method=searchAuditProcess"  method="post" name="searchForm" id="searchForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="apCommitDt">上级提交日期</label>
						<input type="text" id="apCommitDt" name="apCommitDt" class="input-medium input-date"  placeholder="请输入上级提交日期" valid="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<a class="btn-mini"  id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
						    <a class="btn-mini" onclick="audit();">审批</a>
					    </div>
					    <div class="span6" id="btn-right"></div>
				    </div>
				 </form>
			 </div>
		     <%-- /按钮操作区 --%>
			 <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" ></th>
							<th class="center">任务编号</th>
							<th class="center">批次编号</th>
							<th class="center">任务分类</th>
							<th class="center">审批状态</th>
							<th class="center">任务提交人</th>
							<th class="center">任务提交人所在机构</th>
							<th class="center">上级审批人</th>
							<th class="center">上级审批意见</th>
							<th class="center">上级审批人所在机构</th>
							<th class="center">上级提交日期</th>
							<th class="center">上级提交时间</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty processList}">
								<c:forEach items="${processList}" var="process" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='ids' value="${process.id}|${process.atId}|${process.apExecStationId}" onclick="radioStyle(this,'ids')" />
										</td>
										<td class="center">${process.atId}</td>
										<td class="center">${process.batchNo}</td>
										<td class="center">${process.prodNo}</td>
										<td class="center">${process.apStatus}</td>
										<td class="center">${process.atAuthorName}</td>
										<td class="center">${process.brchName}</td>
										<td class="center">${process.apCommitPersonName}</td>
										<td class="center">${process.apCommitRemark}</td>
										<td class="center">${process.apCommitBrchName}</td>
										<td class="center">${process.apCommitDt}</td>
										<td class="center">${process.apCommitTime}</td>
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
					<form action="<%=basePath%>auditProcessController.do?method=searchAuditProcess"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="arName" value="${bean.arName }">
					<input type="hidden" name="prodNo" value="${bean.prodNo }">
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
	function audit(){
		var checkNum = getCheckNum("ids");
	  	if (checkNum !=1){
	  		bootbox.alert("请选择一条记录");
	  		return;
	  	}
	  	var auditId = getCheckStr("ids").split("|");
	  	modal("#layer_loading,#image");
	  	dynamicHiddenElement('dataCollectForm','apId',auditId[0]);
	  	dynamicHiddenElement('dataCollectForm','atId',auditId[1]);
	  	dynamicHiddenElement('dataCollectForm','asId',auditId[2]);
		dataCollectForm.action="<%=basePath%>auditProcessController.do?method=toAudit";
		dataCollectForm.submit();
	}	
</script>
</body>
</html>