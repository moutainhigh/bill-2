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
			<div id="center">
				<div class="row-fluid">
					<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="submits();">撤销</a>
					</div>
					<div class="span6" id="btn-right" >
					</div>
				</div>
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
				<%-- /列表操作区 --%>
				<form  action="#" name="dataCollectForm" method="post"></form>
				<div>
					<div id="page" class="pagination">
						<form action="<%=basePath%>receiveMoneyController.do?method=revokeBillList"  name="pageForm" method="post">
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
	//确认撤销
	function submit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请选择票据");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		dynamicHiddenElement('dataCollectForm','ids',ids);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=toRevokeAccount";
		dataCollectForm.submit();
	}
	//提交撤销
	function submits(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请先选择要撤销的记录");
	  		return;
	  	 }
	  	var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		bootbox.confirm("确定要撤销选中的记录吗?", function(result) {
			if(result) {
				dynamicHiddenElement('dataCollectForm','ids',ids);
			 	dynamicHiddenElement('dataCollectForm','billClass',billClass);
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=submitRevokeAccount";
				dataCollectForm.submit();
			}
		});
	}
	//详情页面
	function goDetail(subcollmxId){
		 var diag = new top.Dialog();
	 diag.Drag = true;
	 diag.Title ="详情";
	 diag.URL = '<%=basePath%>receiveMoneyController.do?method=goSqlDetail&subcollmxId='+subcollmxId;
	 diag.Width = 920;
	 diag.Height = 360;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
	};
</script>
</body>
</html>