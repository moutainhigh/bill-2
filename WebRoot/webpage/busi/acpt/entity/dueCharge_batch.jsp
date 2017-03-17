<%-- 
 * 文件名称: dueCharge_batch.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 纸票到期扣款页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wzc
 * 开发时间: 2016-9-26
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
		    <form action="<%=basePath%>acptAccountController.do?method=dueCharge"  method="post" name="dataCollectForm" id="dataCollectForm" class="form-search" >
			    <%-- 按钮操作区 --%>
				<div id="center">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
							<a class="btn-mini" onclick="javascript:elecBillManage();">下一步</a>
					   </div>
				   </div>
		 		</div>
			    <%-- 列表操作区 --%>
			    <div id="footer">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
							    <th class="center"><input type="checkbox" name="ids" value="" disabled="disabled"/></th>
								<th class='center sort-column batch_no' nowrap="nowrap">批次号</th>
								<th class='center sort-column branch_no'  nowrap="nowrap">签发机构</th>
								<th class='center sort-column remitter'nowrap="nowrap">出票人客户名称</th>
								<th class='center sort-column drawee_bank_name'nowrap="nowrap">付款行行名</th>
								<th class='center sort-column issue_dt'nowrap="nowrap">出票日</th>
								<th class='center sort-column due_dt'nowrap="nowrap">到期日</th>
								<th class='center sort-column bill_class'nowrap="nowrap">票据分类</th>
								<th class='center sort-column bill_type'nowrap="nowrap">票据种类</th>
								<th class='center sort-column total_count'nowrap="nowrap">总笔数</th>
								<th class='center sort-column total_amt'nowrap="nowrap">汇总金额</th>
					    		<th class='center sort-column cust_manager'     nowrap="nowrap">客户经理</th>
								<th class='center sort-column account_dt'   nowrap="nowrap">记账日期</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
									<c:when test="${not empty batchList}">
										<c:forEach items="${batchList}" var="batchList" varStatus="vs">
											<tr>
												<td class="center"><input type="checkbox"  onclick="radioStyle(this,'ids')" name="ids"
														value="${batchList.acptId}" />
													</td>
									            <td class="center"nowrap="nowrap">${batchList.batchNo}</td>
												<td class="center"nowrap="nowrap">${batchList.branchNo}</td>
												<td class="center"nowrap="nowrap">${batchList.remitter}</td>
												<td class="center"nowrap="nowrap">${batchList.draweeBankName}</td>
												<td class="center"nowrap="nowrap">${batchList.issueDt}</td>
												<td class="center"nowrap="nowrap">${batchList.dueDt}</td>
												<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_CLASS',batchList.billClass)}</td>
												<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_TYPE',batchList.billType)}</td>
												<td class="center"nowrap="nowrap">${batchList.totalCount}</td>
												<td class="center"nowrap="nowrap">${fns:formateMoney(batchList.totalAmt)}</td>
												<td class="center"nowrap="nowrap">${batchList.custManager}</td>
												<td class="center"nowrap="nowrap">${batchList.accountDt}</td>
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
					<div id="page" class="pagination">
						<form action="<%=basePath%>acptAccountController.do?method=dueCharge"  name="pageForm" method="post">
							<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						</form>
					</div>	
				</div>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
    function elecBillManage(){
       	var checkNum = getCheckNum("ids");
   	    if (checkNum !==1){
	   		bootbox.alert("一次只能对一个批次进行扣款");
	   		return;
   	    }
        var acptId = getCheckStr("ids");
		dynamicHiddenElement('dataCollectForm','acptId',acptId);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>acptAccountController.do?method=dueChargeBill";
		dataCollectForm.submit();
	}	
</script>
</body>
</html>
