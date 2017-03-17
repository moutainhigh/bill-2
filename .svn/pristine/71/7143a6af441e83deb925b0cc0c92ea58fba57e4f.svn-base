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
<body style="font-family:'微软雅黑';">
<div class="clearfix">
	<div id="page-content" class="page-content">
		<div id="center" style="height:5% !important;border:none !important;margin-bottom:0px;padding:0px;">
			<div class="row-fluid">
				<c:if test="${checkFlag!=null}">
					<div class="span6" id="btn-left"></div>
					<div class="span6" id="btn-right">
						<a class="btn-mini pull-right" onclick="goBack();">返回</a> 
					</div>
				</c:if>
			</div>
		</div>
		<div id="upDiv" style="width:100%;height:66%;border:solid 1px #ccc;">
			<iframe name="rightFrame" id="rightFrame" frameborder="0" src="<%=basePath%>${dataUrl}" style="width:100%;height:100%" ></iframe>
		</div>
		<div id="lowDiv" style="width:inherit;height:26%;padding-top:10px;">
			<div id="list" style="width:50%;height:100%;float:left;border:solid 1px #ccc;">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center">审批人员</th>
							<th class="center">审批人员所在机构</th>
							<th class="center">审批结果</th>
							<th class="center">审批意见</th>
							<th class="center">审批时间</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty processList}">
								<c:forEach items="${processList}" var="process" varStatus="vs">
									<tr>
										<td class="center">${process.apExecPersonName}</td>
										<td class="center">${process.apExecBrchNo}</td>
										<td class="center">${process.apExecResult}</td>
										<td class="center">${process.apExecRemark}</td>
										<td class="center">${process.apDoneDt}</td>
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
		    <c:if test="${checkFlag==null}">
			     <div id="list2" style="width:50%;height:100%;float:left;padding:10px;">
			     	<form action="<%=basePath%>auditProcessController.do?method=submitAuditResult" name="auditForm" id="auditForm" method="post" class="form-search">
				        <div class="row-fluid" style="width:100%;">
							<label class="col-xs-3 control-label text-right" for="status"><span class="star">*</span>审批结果</label>
							<div class="col-xs-8">
								<t:dictSelect valid="required" name="apExecResult" other="" onchange="showBindTr();" defaultVal="${auditRoute.pubFlag}" dictGroup="K_AUDIT_STATUS" haveHead="true" title="审批结果" >
									</t:dictSelect>
							</div>
						</div>
						<div class="row-fluid" style="width:100%;">
							<label class="col-xs-3 control-label text-right" for="custAcctNo">审批意见</label>
							<div class="col-xs-8">
								<textarea id="apExecRemark" name="apExecRemark" class="form-control"></textarea>
							</div>
						</div>
						<div class="row-fluid" style="margin-top:10px;width:100%;">
							<div class=" center">
								<a class="btn-mini" onclick="submitAuditResult();">提交</a>
								<a class="btn-mini" onclick="cancelFn();">取消</a>
							</div>
						</div>
						<input type="hidden" id="atId" name="atId" value="${atId}">
						<input type="hidden" id="asId" name="asId" value="${asId}">
						<input type="hidden" id="apId" name="apId" value="${apId}">
			      	</form>
			    </div>
		    </c:if>
		    <form  action="#" name="dataCollectForm" method="post"></form>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
	function submitAuditResult(){
		if($("#auditForm").valid()){
			auditForm.submit();
		} 
	}
	function cancelFn(){
		auditForm.action="<%=basePath%>auditProcessController.do?method=searchAuditProcess"
		auditForm.submit();
	}
	var checkFlag = '${checkFlag}';
	function goBack(){
		if(checkFlag=='submittedAudit'){
		 dataCollectForm.action="<%=basePath%>submittedAuditController.do?method=search";
		}else if(checkFlag=='participantAudit'){
		 dataCollectForm.action="<%=basePath%>participantAuditController.do?method=search";
		}
		modal("#layer_loading,#image");
		dataCollectForm.submit();
	}
</script>
</body>
</html>