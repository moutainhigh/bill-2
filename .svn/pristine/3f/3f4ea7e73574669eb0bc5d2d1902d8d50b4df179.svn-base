<%-- 
 * 文件名称: disc_elec_apply_batch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-8-10
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
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
       		 <div id="header">
				<form action="<%=basePath%>discApplyController.do?method=elecBatchList" method="post" name="userForm" id="userForm" class="from-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="batchNo">批次号</label>
						<input type="text" id="batchNo" name="batchNo" class="input-medium"  value="${batchNo}" placeholder="请输入批次号"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>discApplyController.do?method=elecBatchList"  method="post" id="page-content" name="custForm" style="margin:0px;" class="row-fluid">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
							<a class="btn-mini" href="javascript:discManage();">票据管理</a>
					   </div>
					   <div class="span6" id="btn-right">
					   </div>
				  </div>
			 	</form>
			</div>
	    	<%-- /按钮操作区 --%>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th>批次号</th>
							<th>客户名称</th>
							<th>票据类型</th>
							<th>票据品种</th>
							<th>合计张数</th>
							<th>合计金额</th>
							<th>总利息</th>
							<th>实付金额</th>
							<th>申请生成日</th>
							<th>客户经理</th>
							<th>部门归属</th>
							<th>详情</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="batch" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='ids' value="${batch.discId}" /><span class="lbl"></span>
										</td>
										<td class="center">${batch.batchNo}</td>
										<td class="center">${batch.custName}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',batch.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}</td>
										<td class="center">${batch.totalNum}</td>
										<td class="center">${fns:formateMoney(batch.totalMoney)}</td>
										<td class="center">${fns:formateMoney(batch.totalInterest)}</td>
										<td class="center">${fns:formateMoney(batch.actualAmount)}</td>
										<td class="center">${batch.createDt}</td>
										<td class="center">${batch.custManagerName}</td>
										<td class="center">${batch.deptName}</td>
										<td class="center"><a class="" href="javascript:goBatchInfo('${batch.discId}');">查看</a></td>
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
			<%-- /列表操作区 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>discApplyController.do?method=elecBatchList" method="post" name="pageForm">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						<input type="hidden" name="batchNo" id="batchNo" value="${batchNo}"/>
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
    function searchd(){
		$("#userForm").submit();
    }
    //详情页面
	function goBatchInfo(discId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>discApplyController.do?method=goBatchInfo&discId='+discId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	function discManage(){
	    var checkNum = getCheckNum("ids");
	   	 if (checkNum !=1){
	   		bootbox.alert("一次只能对一个批次进行票据管理");
	   		return;
	   	 }
	   	var discId = getCheckStr("ids");
	   	//dynamicHiddenElement('dataCollectForm','acctNo',$('#acctNo').val());
		dynamicHiddenElement('dataCollectForm','discId',discId);
		modal("#layer_loading,#image");
		dataCollectForm.action = "<%=basePath%>discApplyController.do?method=elecBillList";
		dataCollectForm.submit();
	}  
</script>
</body>
</html>