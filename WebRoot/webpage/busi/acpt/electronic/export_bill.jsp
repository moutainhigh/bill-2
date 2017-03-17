<%-- 
 * 文件名称: export_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 导出票据
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wzc
 * 开发时间: 2016-9-6
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
<body style="font-family:'微软雅黑';">
<div class="clearfix">
	<div id="page-content" class="page-content">
	    <form action="<%=basePath%>acptApplyController.do?method=exportBill"  method="post" name="dataCollectForm" id="dataCollectForm" class="form-search" >
		<%-- 按钮操作区 --%>
		 <div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" id="btnExport" >导出票据</a>
						</div>
						<div class="span6" id="btn-right">
						</div>
				  	</div>
				</form>
		</div> 
		<%-- 列表操作区 --%>
	    <div id="footer">
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class='center sort-column batch_no' nowrap="nowrap">批次号</th>
					    <th class='center sort-column total_count'nowrap="nowrap">总张数</th>
						<th class='center sort-column total_amt'nowrap="nowrap">总金额</th>					
						<th class='center sort-column remitter_acct'  nowrap="nowrap">出票人账号</th>
						<th class='center sort-column remitter'nowrap="nowrap">出票人名称</th>
						<th class='center sort-column remitter_bank_no'nowrap="nowrap">出票开户行行号</th>
						<th class='center sort-column remitter_bank_name'nowrap="nowrap">出票人开户行</th>
					</tr>
				</thead>
				<tbody id="dataBody">
					<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="batchList" varStatus="vs">
									<tr>
							            <td class="center"nowrap="nowrap">${batchList.batchNo}</td>
							            <td class="center"nowrap="nowrap">${batchList.totalCount}</td>
										<td class="center"nowrap="nowrap">${fns:formateMoney(batchList.totalAmt)}</td>
										<td class="center"nowrap="nowrap">${batchList.remitterAcct}</td>
										<td class="center"nowrap="nowrap">${batchList.remitter}</td>
										<td class="center"nowrap="nowrap">${batchList.remitterBankNo}</td>
										<td class="center"nowrap="nowrap">${batchList.remitterBankName}</td>
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
		</form>
		<div>
		 	<%-- /列表操作区 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>acptApplyController.do?method=exportBill"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 		
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				var frameId = window.frameElement && window.frameElement.id || '';
				var diag = new top.Dialog();
		 		diag.Drag = true;
		 		diag.Title ="详情";
				diag.URL = "<%=basePath%>exportController.do?method=getExportInfo&tableName=TACPT_APPLY_INFO&action=exportController.do?method=commonExport&formName=dataCollectForm&frameId="+frameId;
				diag.Width = 800;
		 		diag.Height = 535;
		 		diag.CancelEvent = function(){ //关闭事件
					diag.close();
		 		};
		 		diag.show();
			});

		});
</script>

</body>
</html>
