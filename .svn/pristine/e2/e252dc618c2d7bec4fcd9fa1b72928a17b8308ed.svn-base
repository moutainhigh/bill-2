<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>提交退票页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-family:'微软雅黑';padding:0px;">
<div class="clearfix">
<div id="page-content" class="page-content">

	<%-- button --%>
<div id="center">
<form action="<%=basePath%>receiveMoneyController.do?method=revokeBillRegister"  method="post" id="" name="custForm" style="margin-bottom:0px;" class="form-search">
		<div class="row-fluid" style="margin-bottom:0px;">
			<div class="span6"  id="btn-left">
			</div>
			<div class="span6" id="btn-right">
				<a class="btn-mini pull-right" onclick="revokeBill();">
				<%-- <i class="icon-circle-arrow-right"></i> --%>提交退票</a>
			  	<a class="btn-mini pull-right" onclick="goBack();">
			  	<%-- <i class="icon-circle-arrow-left"></i> --%>返回</a>
			</div>
		</div>
	</form>
	</div>
	<%-- button结束 --%>
    <div id="footer" class="footer">
		<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" 
			style="min-width:100%;max-width:1600px;width:1600px;">
			<thead>
				<tr>
					<th><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
					<th class="center">票据种类</th>
					<th class="center">票据类型</th>
					<th class="center">出票日期</th>
					<th class="center">票面到期日</th>
					<%-- <th class="center">计息到期日</th> --%>
					<th class="center">发托日期</th> 
					<th class="center">承兑人</th>
					<th class="center">票面金额</th>
					<%-- <th class="center">客户名称</th> --%>
					<th class="center">票号</th>
					<th class="center">付款人开户银行</th>
					<th class="center">付款方行号</th>
					<th class="center">状态</th>
					<th class="center">业务类型</th>
					<th class="center">备注</th>
					<th class="center">详情</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty billList}">
						<c:forEach items="${billList}" var="subcoll" varStatus="vs">
				<tr>
					<td class="center"><input type="checkbox" name="ids" value="${subcoll.subcollmxId}"/></td>
					<td class="center">${fns:getDictLabel('K_BILL_TYPE',subcoll.billType)}</td>
					<td class="center">${fns:getDictLabel('K_BILL_CLASS',subcoll.billClass)}</td>
					<td class="center">${subcoll.issueDt}</td>
					<td class="center">${subcoll.dueDt}</td>
					<%-- <td class="center">${subcoll.galeDate}</td> --%>
					<td class="center">${subcoll.collDate}</td>
					<td class="center">${subcoll.acceptor}</td>
					<td class="center">${fns:formateMoney(subcoll.billMoney)}</td>
					<%-- <td class="center">${subcoll.custName}</td> --%>
					<td class="center">${subcoll.billNo}</td>
					<td class="center">${subcoll.draweeBankName}</td>
					<td class="center">${subcoll.draweeBankNo}</td>
					<td class="center"> ${fns:getDictLabel('K_STATUS',subcoll.status)}</td> 
					<td class="center">${fns:getDictLabel('K_BUY_TYPE',subcoll.buyType)}</td>
					<td class="center">${subcoll.remark}</td>
					<td class="center"><a href="javascript:goDetail('${subcoll.rgctId}')">查看</a></td>
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
			<form action="<%=basePath%>receiveMoneyController.do?method=revokeBillRegister"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="subcollId" value="${subcollId}"/>
			</form>
		</div>
	</div>
	<input type="hidden" name=billClass id="billClass" value="${billClass}"/>
</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	//提交退票
	function revokeBill(){
		var checkNum = getCheckNum("ids");
	 	 if (checkNum == 0){
	 		bootbox.alert("请选择票据编辑");
	 		return;
	 	 }
		var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		dynamicHiddenElement('dataCollectForm','ids',ids);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=revokeBill";
		dataCollectForm.submit();
	}

	//详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId='+rgctId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//返回
	function goBack(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=elecRevokeBillRegister";
		dataCollectForm.submit();
	}	
</script>
</body>
</html>