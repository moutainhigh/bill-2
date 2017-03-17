<%-- 
 * 文件名称: disc_elec_cancel_audit_batch_list.jsp
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
			<form action="<%=basePath%>discAuditController.do?method=cancelAuditElecBatchList" method="post" id="page-content" name="searchForm" style="padding:0px;" class="form-search">
			<%-- 查询区  --%>
				<div class="row-fluid">
					<label class="no-padding-right" for="batchNo">批次号</label>
					<input type="text" id="batchNo" name="batchNo" class="input-medium" value="${batchNo}" placeholder="请输入批次号"/>
					<a class="btn-mini" id="search" onclick="searchd();">查询</a>
				</div>
			</form>
		</div>
		<%-- 按钮操作区 --%>
		<div id="center">
			<form  id="btnForm">
				<div class="row-fluid">
					<div class="span6" id="btn-left" >
						<a class="btn-mini" href="javascript:cancelList();">下一步</a>
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
					    <th class="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','discId')"/></th>
						<th class="center">批次号</th>
						<th class="center">客户名称</th>
						<th class="center">票据类型</th>
						<th class="center">票据品种</th>
						<th class="center">合计张数</th>
						<th class="center">合计金额</th>
						<th class="center">总利息</th>
						<th class="center">实付金额</th>
						<th class="center">申请生成日</th>
						<th class="center">客户经理</th>
						<th class="center">部门归属</th>
						<th class="center">详情</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty batchList}">
							<c:forEach items="${batchList}" var="disc" varStatus="vs">
								<tr>
								    <td class="center"><input type="checkbox" name="discId" value="${disc.discId}"/></td>
									<td class="center">${disc.batchNo}</td>
									<td class="center">${disc.custName}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
									<td class="center">${fns:getDictLabel('K_BILL_CLASS',disc.billClass)}</td>
									<td class="center">${disc.totalNum}</td> 
									<td class="center">${fns:formateMoney(disc.totalMoney)}</td>
									<td class="center">${fns:formateMoney(disc.totalInterest)}</td>
									<td class="center">${fns:formateMoney(disc.actualAmount)}</td>
									<td class="center">${disc.createDt}</td>
									<td class="center">${disc.custManagerName}</td>
									<td class="center">${disc.deptName}</td>
									<td class="center"><a class=""  href="javascript:goBatchInfo('${disc.discId}');">查看</a></td>
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
		<div id="page" class="pagination">
			<form action="<%=basePath%>discAuditController.do?method=cancelAuditElecBatchList"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="batchNo" id="batchNo" value="${batchNo}"/>
			</form>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>  
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
		function searchd(){
				searchForm.submit();
		}
		function cancelList(){
		      var checkNum = getCheckNum("discId");
	   		 if (checkNum !=1){
	   			bootbox.alert("一次只能对一个批次进行撤销");
	   			return;
	   	 	}
	   		var discId = getCheckStr("discId");
	   		modal("#layer_loading,#image");	
			location.href="<%=basePath%>discAuditController.do?method=cancelAuditElecBillList&discId="+discId;
		}
		//详情页面
	   function goBatchInfo(discId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>discApplyController.do?method=goBatchInfo&discId='+discId;
		 diag.Width = 800;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>