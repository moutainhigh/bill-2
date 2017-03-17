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
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 查询条件 和 button --%>
			<div id="center" class="f-filter">
				<form action="<%=basePath%>receiveMoneyController.do?method=searchBillList" method="post" id="" name="custForm" style="margin-bottom:0px;" class="form-search">
					<div class="row-fluid">
						<div class="span6"  id="btn-left">
						</div>
						<div class="span6" id="btn-right">
						  	<a class="btn-mini pull-right" onclick="goBack();">返回</a>
							<a class="btn-mini pull-right" onclick="submitacct();">提交记账</a>
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
										<td class="center"><a href="javascript:goDetail('${subcoll.subcollmxId}')">查看</a></td>
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
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	//提交记账
	function submitacct(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请选择票据");
	  		return;
	  	 }
		var ids=document.getElementById("ids").value;
		var billClass=document.getElementById("billClass").value;
	 	dynamicHiddenElement('dataCollectForm','ids',ids);
	 	dynamicHiddenElement('dataCollectForm','billClass',billClass)
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=submitAccount";
		dataCollectForm.submit();
	}
	//返回
	function goBack(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=searchBillList";
		dataCollectForm.submit();
	}
	//详情页面
	function goDetail(subcollmxId){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>receiveMoneyController.do?method=goSqlDetail&subcollmxId='+subcollmxId;
		 diag.Width = 920;
		 diag.Height = 330;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>
