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
	<script type="text/javascript" src="webpage/system/addPrint/commonFunc.js"></script>
</head>
<body style="font-family:'微软雅黑';padding:0px;">
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action="<%=basePath%>receiveMoneyController.do?method=searchBillList"  method="post" id="custForm" name="custForm" style="padding:0px;"  class="form-search">
					<%-- 引入查询FORM --%>
					<div class="row-fluid">
						<label class="text-right">票号</label>
						<input name="billNo" id="billNo" type="text" value="${billNo}" class="input-medium" placeholder="请输入票号"></input>
						<label class="text-right">票据类型</label>
						<t:dictSelect  name="billType" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${billType}" haveHead="true" title="票据类型" >
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<label for="dueDt" class="no-padding-right">到期起始日</label>
						<input class="input-medium input-date" name="startDay" id="startDay"  type="text"  placeholder="开始日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<label for="dueDt" class="no-padding-right">到期截止日</label>
						<input class="input-medium input-date" name="endDay" id="endDay" type="text"  placeholder="结束日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</div>
					<div class="row-fluid">
						<label class="text-right">最小票据金额</label>
						<input name="" type="text" name="smallMoney" id="smallMoney"  class="input-medium" placeholder="请输入最小票据金额"></input>
						<label class="text-right">最大票据金额</label>
						<input name="" type="text"name="bigMoney" id="bigMoney"   class="input-medium" placeholder="请输入最大票据金额"></input>
						<a class="btn-mini"  id="search"   onclick="query();">查询</a>
					</div>
			 	 </form>
			 	 </div>
				<div id="center">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
							<a class="btn-mini" onclick="printDocument();">打印凭证</a>
							<a class="btn-mini" onclick="printList();">打印清单</a>
							<a class="btn-mini" onclick="submits();">记账</a>
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
						<form action="<%=basePath%>receiveMoneyController.do?method=searchBillList"  name="pageForm" method="post">
							<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
							<input type="hidden" name="subcollId" value="${subcollId}"/>
						<input type="hidden" name="billNo" value="${billNo}"/>
						<input type="hidden" name="bigMoney" value="${bigMoney}"/>
						<input type="hidden" name="smallMoney" value="${smallMoney}"/>
						<input type="hidden" name="billType" value="${billType}"/>
						<input type="hidden" name="billClass" value="${billClass}"/>
						</form>
					</div>
				</div>
				<input type="hidden" name=billClass id="billClass" value="${billClass}"/>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script src="weblib/bizjs/checkDate.js"></script> 
<script>
	//查询
	function query(){
		
		var billNo = document.getElementById("billNo").value;
		var smallMoney = document.getElementById("smallMoney").value; 
		var bigMoney = document.getElementById('bigMoney').value;  
		var Money =/^(\d{0,10}(\.\d{0,2})?|d*)$/;
		var No = /^(\w{0,16}|d*)$/;
	 	if(!No.test(billNo)){
			bootbox.alert("票号最多可输入16位");
			return;
	    }else if(!Money.test(smallMoney)){
	 		bootbox.alert("最小金额只能输入数字且小数点后只能输入两位");
			return;
	   	}else if(!Money.test(bigMoney)){
			bootbox.alert("最大金额只能输入数字且只能输入十亿以下的金额");
			return;
		}
		else{ $("#custForm").submit();
		}
	}

	//确认记账
	function submit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请选择票据");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
	 	dynamicHiddenElement('dataCollectForm','ids',ids);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
	 	dynamicHiddenElement('dataCollectForm','billClass',billClass);
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=toSubmitAccount";
		dataCollectForm.submit();
	}
	//提交记账
	function submits(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请先选择要记账的记录");
	  		return;
	  	 }
	  	var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		bootbox.confirm("确定要记账选中的记录吗?", function(result) {
			if(result) {
				dynamicHiddenElement('dataCollectForm','ids',ids);
			 	dynamicHiddenElement('dataCollectForm','billClass',billClass);
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=submitAccount";
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
	 diag.Height = 330;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
	}
	//打印托收清单
		function printList(){
			var checkNum = getCheckNum("ids");
  			if (checkNum == 0){
  				bootbox.alert("请先选择要打印的记录");
	  			return;
  	 		}
  	 		var ids = getCheckStr("ids");
			//批次号
			//var mysubId =$("#mxid").val();
			var mysubId="${batch.batchId}";
			doPrint("addPrintController.do?method=doPrint&ids="+ids+"&baid="+mysubId+"&moduleId=1070401&type=SubCollListOtherPrint");			
		}
		 //打印托收凭证
    	function printDocument(){
    		var checkNum = getCheckNum("ids");
  			if (checkNum == 0){
  				bootbox.alert("请先选择要打印的记录");
	  			return;
  	 		}
  	 		var ids = getCheckStr("ids");
			//批次号
			var mysubId="${batch.batchId}";			
    	  	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1070101&type=SubCollPzOtherPrint&baid="+mysubId);	
		} 
</script>
</body>
</html>