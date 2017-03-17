<%-- 
 * 文件名称: buyback_apply_batch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-11-05
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
							<a class="btn-mini" href="javascript:updateBillInfo();">票据管理</a>
					   </div>
					   <div class="span6" id="btn-right"></div>
				  	</div>
				 </form>
			 </div>
   		 	 <%-- /按钮操作区 --%>
		     <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
							<th class="center" >
								<span class="lbl"></span>
							</th>
							<th class="center">批次号</th>
							<th class="center">交易对手</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">利率</th>
							<th class="center">转贴现日期</th>
							<th class="center">合计张数</th>
							<th class="center">合计金额</th>
							<th class="center">清算方式</th>
							<th class="center">不得转让标记</th>
							<th class="center">回购开放日</th>
							<th class="center">回购截止日</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="batch" varStatus="vs">
									<tr>
										<td class="center">
											<input type="checkbox" onclick="radioStyle(this,'saleId')" name="saleId" value="${batch.saleId}"/><span class="lbl"></span>
										</td>
										<td class="center">${batch.batchNo}</td>
											<td class="center">${batch.custName}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',batch.billType)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}</td>
											<td class="center">${fns:formateRate(batch.rate)}</td>
											<td class="center">${batch.saleDt}</td>
											<td class="center">${batch.totalSize}</td>
											<td class="center">${fns:formateMoney(batch.sumMoney)}</td>
											<td class="center">${fns:getDictLabel('K_ISONLINE',batch.isOnline)}</td>
											<td class="center">${fns:getDictLabel('K_YORN',batch.forbidFlag)}</td>
											<td class="center">${batch.buybackOpenDt}</td>
											<td class="center">${batch.rebuyDueDt}</td>
											<td class="center">
												<a href="javascript:goApplyInfo('${batch.saleId}')">查看</a>
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
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>buybackApplyController.do?method=buybackApplyList" method="post" name="pageForm">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	//更新利息的信息
	function updateBillInfo(){
		var checkNum = getCheckNum("saleId");
	  	 if (checkNum !=1){
	  		bootbox.alert("请先选择要编辑的记录!");
	  		return;
	  	 }
	  	var saleId = getCheckStr("saleId");
	   	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dynamicHiddenElement('pageForm','saleId',saleId);
		pageForm.action = "<%=basePath%>buybackApplyController.do?method=updateBillInfo";
		pageForm.submit();
	}
	//批次详情页面
	function goApplyInfo(saleId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>saleApplyController.do?method=goApplyInfo&saleId='+saleId+'&flag=cancelAccount';
		 diag.Width = 1000;
		 diag.Height = 505;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>