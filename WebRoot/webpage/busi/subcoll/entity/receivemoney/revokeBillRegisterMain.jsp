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
	<title>退票管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- button --%>
			<div id="center">
				<form action="<%=basePath%>receiveMoneyController.do?method=revokeBillRegister"  method="post" id="" name="custForm" style="margin-bottom:0px;" class="form-search">
					<div id="formDiv" class="f-filter row-fluid">
						<div class="but_sytle span6" id="btn-left">
							<a class="btn-mini" onclick="submits();">退票</a>
						</div>
						 <div class="span6" id="btn-right" >
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
							<th class="center">发托日期</th> 
							<th class="center">承兑人</th>
							<th class="center">票面金额</th>
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
										<td class="center">${subcoll.collDate}</td>
										<td class="center">${subcoll.acceptor}</td>
										<td class="center">${fns:formateMoney(subcoll.billMoney)}</td>
										<td class="center">${subcoll.billNo}</td>
										<td class="center">${subcoll.draweeBankName}</td>
										<td class="center">${subcoll.draweeBankNo}</td>
										<td class="center"> ${fns:getDictLabel('K_STATUS',subcoll.status)}</td> 
										<td class="center">${fns:getDictLabel('K_BUY_TYPE',subcoll.buyType)}</td>
										<td class="center">${subcoll.remark}</td>
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
	}
	//确认退票
	function submit(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请先选择要登记的记录");
	  		return;
	  	 }
		var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
	 	dynamicHiddenElement('dataCollectForm','ids',ids);
	 	dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=toSubmitrevokeBillRegister";
		dataCollectForm.submit();
	}
	//提交退票
	function submits(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请先选择要退票的记录");
	  		return;
	  	 }
	  	var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		bootbox.confirm("确定要退票选中的记录吗?", function(result) {
			if(result) {
				dynamicHiddenElement('dataCollectForm','ids',ids);
			 	dynamicHiddenElement('dataCollectForm','billClass',billClass);
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=revokeBill";
				dataCollectForm.submit();
			}
		});
	}	
</script>
</body>
</html>