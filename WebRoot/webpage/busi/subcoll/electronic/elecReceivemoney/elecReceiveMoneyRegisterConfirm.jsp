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
	<title>票据明细页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-family:'微软雅黑';padding:0px;">
<div class="clearfix">
<div id="page-content" class="page-content">
	<%-- 查询条件 和 button --%>
	<div id="center" class="f-filter">
	<form action="<%=basePath%>receiveMoneyController.do?method=toSubmitAccountRegister"  method="post" id="" name="custForm" style="margin-bottom:0px;" class="form-search">
		<div class="row-fluid" style="margin-bottom:0px;">
			<div class="span6"  id="btn-left">
			</div>
			<div class="span6" id="btn-right">
				<a class="btn-mini pull-right" onclick="submitacct();">
				<%-- <i class="icon-circle-arrow-right"></i> --%>提交登记</a>
			  	<a class="btn-mini pull-right" onclick="goBack();">
			  	<%-- <i class="icon-circle-arrow-left"></i> --%>返回</a>
			</div>
		</div>
	</form>
</div>

    <div id="footer" class="footer">
		<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
					<th class="center">票号</th>
					<th class="center">状态</th>
					<th class="center">票据类型</th>
					<th class="center">出票日</th>
					<th class="center">到期日</th>
					<th class="center">票据金额</th>
					<th class="center">承兑人全称</th>
					<th class="center">出库日期</th>
					<th class="center">详情</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty billList}">
						<c:forEach items="${billList}" var="subcoll" varStatus="vs">
				<tr>
					<td class="center"><input type="checkbox" name="ids" value="${subcoll.subcollmxId}"/></td>
					<td class="center">${subcoll.billNo}</td>
					<td class="center"> ${fns:getDictLabel('K_STATUS',subcoll.status)}</td> 
					<td class="center">${fns:getDictLabel('K_BILL_TYPE',subcoll.billType)}</td>
					<td class="center">${subcoll.issueDt}</td>
					<td class="center">${subcoll.dueDt}</td>
					<td class="center">${fns:formateMoney(subcoll.billMoney)}</td>
					<td class="center">${subcoll.acceptor}</td>
					<td class="center">${subcoll.collDate}</td>
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
	<form  action="#" name="dataCollectForm" method="post"></form>
	<%-- /列表操作区 --%>
	<%-- <div>
		<div id="page" class="pagination">
			<form action="<%=basePath%>receiveMoneyController.do?method=toSubmitAccountRegister"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="subcollId" value="${subcollId}"/>
			</form>
		</div>
	
	<input type="hidden" name="ids" id="ids" value="${ids}"/>
	<input type="hidden" name=billClass id="billClass" value="${billClass}"/>
</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	//提交登记
	function submitacct(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请选择票据");
	  		return;
	  	 }
		var ids=document.getElementById("ids").value;
		var billClass=document.getElementById("billClass").value;
		dynamicHiddenElement('dataCollectForm','ids',ids);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=submitAccountRegister";
		dataCollectForm.submit();
	}
	//返回
	function goBack(){
		var billType= "${query.billType}";
		var startDay= "${query.startDay}";
		var endDay= "${query.endDay}";
		var bigMoney = "${query.bigMoney}";
		var smallMoney = "${query.smallMoney}";
		var billNo = "${billNo}";
		dynamicHiddenElement('dataCollectForm','billType',billType);
		dynamicHiddenElement('dataCollectForm','startDay',startDay);
		dynamicHiddenElement('dataCollectForm','endDay',endDay);
		dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
		dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
		dynamicHiddenElement('dataCollectForm','billNo',billNo);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=elecReceiveMoneyRegister";
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
</script>
</body>
</html>