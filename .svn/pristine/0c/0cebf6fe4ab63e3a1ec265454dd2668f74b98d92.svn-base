<%-- 
 * 文件名称: sale_apply_choose_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 转卖申请选票页面
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>选票页面</title>
<base href="<%=basePath%>">
<%-- jsp文件头和头部 --%>
<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-family:'微软雅黑';padding:10px;background:#f4f8fb;">
	<div class="clearfix">
		<div id="page-content" class="page-content" style="background:#f4f8fb;">
			<%-- 查询区 --%>
			<div id="header">
				<form action="<%=basePath%>saleApplyController.do?method=toAddBill" id="page-content" method="post" name="searchForm" id="searchForm" class="form-search">
					<input type="hidden" name="billType" value="${query.billType}"/>
					<input type="hidden" name="billClass" value="${query.billClass}"/>
					<input type="hidden" name="saleId" id="saleId" value="${query.saleId}">
					<div class="row-fluid">
						<label class="no-padding-right" for="billNo">票号</label>
						<input type="text" class="input-medium" id="billNo" name="billNo" class="col-md-16" value="${query.billNo}" />
						<label class="no-padding-right" for="billMoney">票面金额</label>
						<input type="text" class="date-medium" id="startMoney" name="startMoney" value="${query.startMoney}" /> 
						<span class="wave-line">~</span>
						<inpu type="text" class="date-medium" id="endMoney" name="endMoney"  value="${query.endMoney}"/>
						<label class="no-padding-right" for="dueDt">票面到期日</label>
						<input name="startDay" id="startDay"  value ="${query.startDay}" valid="required" type="text" class="date-medium input-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
						<span class="wave-line">~</span>
						<input name="endDay"id="endDay"  value ="${query.endDay}"   valid="required" type="text" class="date-medium input-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮部分 --%>
			<div id="center">
				<form id="btnForm">
					<div class="row-fluid">
						<div class="input-medium" id="btn-right">
							<a class="btn-mini pull-right" onclick="top.Dialog.close();">取消</a>
							<a class="btn-mini pull-right" onclick="submit();">确定</a> 
						</div>
					</div>
				</form>
			</div>
			<%--  表格部分 --%>
			<div id="footer">
				<table class="table table-striped table-bordered table-hover"
					id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">票据种类</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">承兑人</th>
							<th class="center">买入类型</th>
							<th class="center">备注</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="billInfo"
									varStatus="vs">
									<tr>
										<td class="center">
											<input type="checkbox" name="ids" value="${billInfo.id}" onclick="getall('ids')" price="${billInfo.billMoney}"/>
										</td>
										<td class="center">${billInfo.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',billInfo.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',billInfo.billClass)}</td>
										<td class="center">${billInfo.issueDt}</td>
										<td class="center">${billInfo.dueDt}</td>
										<td class="center">${fns:formateMoney(billInfo.billMoney)}</td>
										<td class="center">${billInfo.acceptor}</td><%-- 承兑方全称 --%>
										<td class="center">${fns:getDictLabel('K_BUY_TYPE',billInfo.buyType)}</td><%-- 票据买入类型 --%>
										<td class="center">${billInfo.remark}</td>
										<td class="center">
											<a href="javascript:goDetail('${billInfo.rgctId}')">查看</a>
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
			<div id="select-Info">
				<div id="selectInfo"><center>暂时没有相关数据</center></div>
			</div>
			<%-- 用于页面跳转 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- 分页 --%>
			<div id="page" class="pagination" style="margin:0 10px 10px 0">
				<form action="<%=basePath%>saleApplyController.do?method=toAddBill" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="billType" value="${query.billType}"/>
					<input type="hidden" name="billClass" value="${query.billClass}"/>
					<input type="hidden" name="saleId" id="saleId" value="${query.saleId}">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/webpage/system/admin/footer.jsp"%>
    <%@ include file="/webpage/system/admin/modalDialog.jsp"%>
	<script type="text/javascript">
	 //详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	function searchd(){
		$("#searchForm").submit();
	}
	function submit(){
		var checkNum = getCheckNum("ids");
	   	 if (checkNum <= 0){
	   		bootbox.alert("请至少选择一条要进行添加的记录");
	   		return;
	   	 }
	   	 var ids = getCheckStr("ids");
	   	dynamicHiddenElement('dataCollectForm', 'rgctIds', ids);
	   	dynamicHiddenElement('dataCollectForm', 'saleId', $("#saleId").val());
	   	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
	   	dataCollectForm.action = "<%=basePath%>saleApplyController.do?method=addBill";
	   	dataCollectForm.submit();
	}	
	//高级查询
	function highsearchd(){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="高级查询";
		 diag.URL = "<%=basePath%>saleApplyController.do?method=toHighSearch";
		 diag.Width = 500;
		 diag.Height = 400;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
</script>
</body>
</html>