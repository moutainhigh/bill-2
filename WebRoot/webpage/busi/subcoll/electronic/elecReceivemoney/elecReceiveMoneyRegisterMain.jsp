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
<div id="header">
<%-- 查询条件 和 button --%>
		<form action="<%=basePath%>receiveMoneyController.do?method=elecReceiveMoneyRegister"  method="post" id="custForm" name="custForm" style="padding:0px;" class="form-search">
			<div class="row-fluid">
						<label class="item-label pull-left text-right" style="margin-left:30px;">票号</label>
						<input class="input-medium" name="billNo" id="billNo" type="text" value="${billNo}" placeholder="票号"></input>
						<label class="item-label text-right">票据类型</label>
						<t:dictSelect className="select-medium"  name="billType" other="" dictGroup="K_BILL_TYPE" defaultVal="${billType}" haveHead="true" title="票据类型" >
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<label class="item-label pull-left" style="margin-left:30px;">汇票到期起始日</label>
						<input class="input-medium input-date" type="text" name="startDay" id="startDay" value="${startDay}"/>
						<label class="item-label">汇票到期截止日</label>
						<input class="input-medium input-date" type="text" name="endDay" id="endDay" value="${endDay}"/>
					</div>
					<div class="row-fluid">
						<label class="item-label pull-left" style="margin-left:30px;">最小票据金额</label>
						<input class="input-medium" name="smallMoney" id="smallMoney" value="${smallMoney}" type="text" placeholder="0.00~1,000,000,000"></input>
						<label class="item-label col-md-2 control-label">最大票据金额</label>
						<input class="input-medium" name="bigMoney" id="bigMoney" value="${bigMoney}" type="text" placeholder="0.00~1,000,000,000"></input>
						<a class="btn-mini" id="search" style="margin:2px 10px 0 30px;" onclick="query();">查询</a>
					</div>
		</form>
	</div>
	<div id="center">
		<div class="row-fluid" style="margin-bottom:0px;">
			<div class="span6" id="btn-left" >
				<a class="btn-mini" onclick="submits();">登记</a>
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
					<td class="center"><input type="checkbox" name="ids" value="${subcoll.subcollmxId}" onclick="getall('ids')" price="${subcoll.billMoney}"/></td>
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
	<div id="select-Info">
		<div id="selectInfo"><center>暂时没有相关数据</center></div>
	</div>
	<%-- /列表操作区 --%>
	<form  action="#" name="dataCollectForm" method="post"></form>
	<div>
		<div id="page" class="pagination">
			<form action="<%=basePath%>receiveMoneyController.do?method=elecReceiveMoneyRegister"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="subcollId" value="${subcollId}"/>
				<input type="hidden" name="billNo" value="${billNo}"/>
				<input type="hidden" name="bigMoney" value="${bigMoney}"/>
				<input type="hidden" name="smallMoney" value="${smallMoney}"/>
				<input type="hidden" name="billType" value="${billType}"/>
				<input type="hidden" name="billClass" value="${billClass}"/>
				<input type="hidden" name="startDay" value="${startDay}"/>
				<input type="hidden" name="endDay" value="${endDay}"/>
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
	$(document).ready(function(){
		/* 日期的校验 */
		getDateObject("startDay");
		getDateObject("endDay");
		checkDate("startDay");
		checkDate("endDay");
		$(".ui-datepicker-trigger").css("right","15px");
	});
	//查询
	function query(){
		var billNo = document.getElementById('billNo').value;
		var smallMoney = document.getElementById('smallMoney').value; 
		var bigMoney = document.getElementById('bigMoney').value;  
		var Money =/^(\d{0,10}(\.\d{0,2})?|d*)$/;
		var No = /^(\w{0,30}|d*)$/;
	 	if(!No.test(billNo)){
		bootbox.alert("票号最多可输入30位");
		return;
	    	}else
	 	 if(!Money.test(smallMoney)){
	 		bootbox.alert("最小金额只能输入数字且小数点后只能输入两位");
		return;
	   		}else
		if(!Money.test(bigMoney)){
			bootbox.alert("最大金额只能输入数字且只能输入十亿以下的金额");
		return;
	    	}
	    	else{ $("#custForm").submit();}
	 
	}

	//确认登记
	function submit(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请选择票据");
	  		return;
	  	 }
		var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		var billType= "${billType}";
		var startDay= "${startDay}";
		var endDay= "${endDay}";
		var bigMoney = "${bigMoney}";
		var smallMoney = "${smallMoney}";
		var billNo = "${billNo}";
		dynamicHiddenElement('dataCollectForm','billType',billType);
		dynamicHiddenElement('dataCollectForm','startDay',startDay);
		dynamicHiddenElement('dataCollectForm','endDay',endDay);
		dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
		dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
		dynamicHiddenElement('dataCollectForm','billNo',billNo);
		dynamicHiddenElement('dataCollectForm','ids',ids);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=toSubmitAccountRegister";
		dataCollectForm.submit();
	}	
	//提交登记
	function submits(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请先选择要登记的记录");
	  		return;
	  	 }
		var ids = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		var billType= "${billType}";
		var startDay= "${startDay}";
		var endDay= "${endDay}";
		var bigMoney = "${bigMoney}";
		var smallMoney = "${smallMoney}";
		var billNo = "${billNo}";
		bootbox.confirm("确定要登记选中的记录吗?", function(result) {
			if(result) {
				dynamicHiddenElement('dataCollectForm','ids',ids);
			 	dynamicHiddenElement('dataCollectForm','billClass',billClass);
			 	dynamicHiddenElement('dataCollectForm','billType',billType);
			 	dynamicHiddenElement('dataCollectForm','startDay',startDay);
				dynamicHiddenElement('dataCollectForm','endDay',endDay);
				dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
				dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
				dynamicHiddenElement('dataCollectForm','billNo',billNo);
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				dataCollectForm.action = "<%=basePath%>receiveMoneyController.do?method=submitAccountRegister";
				dataCollectForm.submit();
			}
		});
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
	};
</script>
</body>
</html>