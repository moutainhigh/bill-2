<%-- 
 * 文件名称: buyback_review_account_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-11-08
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
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
							<a class="btn-mini" href="javascript:reviewList();">下一步</a>
					   </div>
					   <div class="span6" id="btn-right"></div>
				  	</div>
			   	</form>
			 </div>
	    	 <%-- /按钮操作区 --%>
		     <div id="footer">
				 <table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px">
					 <thead>
						 <tr>
						    <th class="center"><input type="checkbox" name="allcheck" disabled="disabled" id="allcheck" onclick="selectAll('allcheck','buybackId')"/></th>
						   	<th>批次号</th>
							<th>交易对手</th>
							<th>票据类型</th>
							<th>票据品种</th>
							<th>回购利率</th>
							<th>合计张数</th>
							<th>合计金额</th>
							<th>总利息</th>
							<th>回购金额</th>
							<th>卖出日期</th>
							<th>回购开放日</th>
							<th>回购截止日</th>
							<th>申请生成日</th>
						 </tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="batch" varStatus="vs">
									<tr>
									    <td class="center"><input type="checkbox" onclick="radioStyle(this,'buybackId')" name="buybackId" value="${batch.buybackId}"/></td>
										<td class="center">${batch.batchNo}</td>
										<td class="center">${batch.custName}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',batch.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}</td>
										<td class="center">${fns:getDictLabel('K_PAY_TYPE',batch.buybackRate)}</td>
										<td class="center">${batch.totalNum}</td>
										<td class="center">${fns:formateMoney(batch.totalMoney)}</td>
										<td class="center">${fns:formateMoney(batch.totalInterest)}</td>
										<td class="center">${fns:formateMoney(batch.totalDraweeMoney)}</td>
										<td class="center">${batch.saleDt}</td>
										<td class="center">${batch.buybackOpenDt}</td>
										<td class="center">${batch.buybackDueDt}</td>
										<td class="center">${batch.createDt}</td>
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
				<form action="<%=basePath%>buybackAccountController.do?method=buybackConfirmAccountList"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function reviewList(){
		var checkNum = getCheckNum("buybackId");
	   	if (checkNum !=1){
	   		bootbox.alert("请先选择要记账的批次");
	   		return;
	   	}
	   	var buybackId = getCheckStr("buybackId");
	   	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dynamicHiddenElement('pageForm','buybackId',buybackId);
		pageForm.action="<%=basePath%>buybackAccountController.do?method=buybackConfirmAccountDetailList";
		pageForm.submit();
	}
</script>
</body>
</html>