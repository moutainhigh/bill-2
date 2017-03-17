<%-- 
 * 文件名称: audit_btch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
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
			    <form action="<%=basePath%>outAuditController.do?method=cancelAuditBatchList" method="post" id="page-content" name="Form" style="padding:0px;" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="batchNo">批次号</label>
						<input type="text" class="input-medium" id="batchNo" name="batchNo" class="input-medium" value="${query.batchNo }" placeholder="请输入批次号"/>
						<a class="btn-mini" id="search" onclick="doSearch();" >查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
							<a class="btn-mini" href="javascript:cancelAudit();">下一步</a>
					   </div>
					   <div class="span6" id="btn-right">
					   </div>
				    </div>
				 </form>
			</div>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'intoId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th>批次号</th>
							<th>批次类型</th>
							<th>批次种类</th>
							<th>客户名称</th>
							<th>产品编号</th>
							<th>合计张数</th>
							<th>合计金额</th>
							<th>客户经理编号</th>
							<th>客户经理名称</th>
							<th>所属部门</th>
							<th>详情</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
								<c:when test="${not empty batchList}">
									<c:forEach items="${batchList}" var="batchList" varStatus="vs">
										<tr>
											<%--<td class="center">
											<label><input type='checkbox' name='outId' value="${batchList.outId}" /><span class="lbl"></span></label>
											</td>--%>
											<td class="center">
											<label><input type="checkbox" onclick="radioStyle(this,'outId')" name="outId" value="${batchList.outId}"/><span class="lbl"></span></label>
											</td>
											<td class="center">${batchList.batchNo}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',batchList.batchType)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_CLASS',batchList.batchClass)}</td>
											<td class="center">${batchList.custName}</td>
											<td class="center">${batchList.prodNo}</td>
											<td class="center">${batchList.totalNum}</td>
											<td class="center">${fns:formateMoney(batchList.totalMoney)}</td>
											<td class="center">${batchList.custManager}</td>
											<td class="center">${batchList.custManagerName}</td>
											<td class="center">${batchList.deptName}</td>
											<td class="center"><a href="javascript:goBatchDetail('${batchList.outId}')">查看</a></td>
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
				<form action="#" name="dataCollectForm" method="post"></form>
				 <%-- /列表操作区 --%>
				 <div>
					<div id="page" class="pagination">
						<form action="<%=basePath%>outAuditController.do?method=cancelAuditBatchList"  name="pageForm" method="post">
							<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						</form>
					</div>
				</div>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function doSearch(){
		Form.submit();
	}
	//撤销票据管理
	function cancelAudit(){
		 var checkNum = getCheckNum("outId");
	   	 if (checkNum !=1){
	   		bootbox.alert("请先选择要编辑的记录!");
	   		return;
	   	 }
	   	 var outId = getCheckStr("outId");
	   	 $("#_ButtonClose_0").css({'width':'27px','top':'4px','background-position': '-1px 0px'});
	   	 dynamicHiddenElement('dataCollectForm','outId',outId);
	   	 modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
	     dataCollectForm.action = "<%=basePath%>outAuditController.do?method=cancelAuditBillList"
	     dataCollectForm.submit();
	}
	//批次详情
	function goBatchDetail(outId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>outApplyController.do?method=goBatchDetail&outId="+outId;
		 diag.Width = 990;
		 diag.Height = 170;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>