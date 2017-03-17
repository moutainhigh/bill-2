<%-- 
 * 文件名称: send_batch_manage.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次列表页面
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-25
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
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
<body >
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<form action="<%=basePath%>subcollStorageController.do?method=sendBatchManage"  method="post" name="infoForm" id="infoForm" class="form-search">
			</form>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
				<div class="row-fluid">
					<div class="span6" id="btn-left" >
						<a class="btn-mini" href="javascript:billManage();">票据管理</a>
				   </div>
				   <div class="span6" id="btn-right" >
				   </div>
			  </div>
			  </form>
			 </div>
		    <%-- /按钮操作区 --%>
   			<div id="footer">
				<form action="<%=basePath%>subcollStorageController.do?method=sendBatchManage"  method="post" name="infoForm" id="infoForm" class="form-search">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','subcollId')"/></th>
								<th>批次号</th>
								<th>票据类型</th>
								<th>票据品种</th>
								<th>票据张数</th>
								<th>总金额</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
									<c:when test="${not empty batchList}">
										<c:forEach items="${batchList}" var="subcollApplyInfo" varStatus="vs">
											<tr>
												<td class="center">
													<input type='checkbox' name='subcollId' value="${subcollApplyInfo.subcollId}" /><span class="lbl"></span>
												</td>
												<td class="center">${subcollApplyInfo.batchId}</td>
												<td class="center">${fns:getDictLabel('K_BILL_TYPE',subcollApplyInfo.billType)}</td>
												<td class="center">${fns:getDictLabel('K_BILL_CLASS',subcollApplyInfo.billClass)}</td>
												<td class="center">${subcollApplyInfo.totalNum}</td>
												<td class="center">${fns:formateMoney(subcollApplyInfo.totalMoney)}</td>
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
					</form>
				</div>
				<form  action="#" name="dataCollectForm" method="post"></form>
			 	<div>
					<div id="page" class="pagination">
						<form action="<%=basePath%>subcollStorageController.do?method=sendBatchManage"  name="pageForm" method="post">
							<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
							<input type="hidden" name=billClass id="billClass" value="${billClass}"/>
						</form>
					</div>
				</div>
				<input type="hidden" name=billClass id="billClass" value="${billClass}"/>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	//票据管理
	function billManage(){
		var checkNum = getCheckNum("subcollId");
		if (checkNum !=1){
			bootbox.alert("一次只能对一个批次进行票据管理");
	   		return;
	   	 }
		var subcollId = getCheckStr("subcollId");
		var billClass=document.getElementById("billClass").value;
		dynamicHiddenElement('dataCollectForm','subcollId',subcollId);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>subcollStorageController.do?method=editBillManage";
		dataCollectForm.submit();
	}
	function reset(){
		resetForm('infoForm');
		var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
		$("#dataBody").html(trHtml);
	}	
</script>
</body>
</html>