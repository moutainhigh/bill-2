<%-- 
 * 文件名称: bill_manage.jsp.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-25
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
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
	<title>电票管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-family:'微软雅黑';">
<div class="clearfix">
	<div id="page-content" class="page-content">
		<div id="header">
			<form action="<%=basePath%>subcollApplyController.do?method=elecBillManage" method="post" name="subcollManage" id="subcollManage" class="form-search">
				<%-- 查询区  --%>
				<div class="row-fluid">
					<label for="dueDt" class="no-padding-right">到期区间</label>
			 		<input class="date-medium input-date" name="startDay" id="startDay" value="${startDay}" type="text"  placeholder="开始日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					<span class="wave-line">~</span>
			 		<input class="date-medium input-date" name="endDay" id="endDay" value="${endDay}" type="text"  placeholder="结束日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					<label class="no-padding-right" for="billType">票据类型</label>
					<t:dictSelect name="billType" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${billType}" haveHead="true"  title="票据类型" >
					</t:dictSelect>
					<label class="no-padding-right" for="billClass">票据品种</label>
					<t:dictSelect name="billClass" className="select-medium" other="" dictGroup="K_BILL_CLASS" defaultVal="${billClass}" haveHead="true"  title="票据品种" disabled="disabled">
					</t:dictSelect>
				</div>
				<div class="row-fluid">
					<label for="billMoney" class="col-md-1 control-label text-right">金额区间</label>
			 		<input class="date-medium" name="smallMoney" id="smallMoney" value="${smallMoney}"  type="text" placeholder="最小金额"/></td>
					<span class="wave-line">~</span>
			 		<input class="date-medium" name="bigMoney" id="bigMoney" value="${bigMoney}" type="text" placeholder="最大金额"/>
					<label class="no-padding-right" for="billNo">票号</label>
					<input class="input-medium" name="billNo" id="billNo" value="${billNo}" type="text" placeholder="票号"/>
					<label class="no-padding-right" for="acceptor">承兑人</label>
					<input class="input-medium" name="acceptor" id="acceptor" value="${acceptor}" type="text" placeholder="承兑人"/>
					<a class="btn-mini" id="search" onclick="query();">查询</a>
				</div>
			</form>
		</div> 
		<%-- 按钮操作区 --%>
		<div id="center">
			<div class="row-fluid">
				<div class="span6" id="btn-left">
					<a class="btn-mini" onclick="billManage();">下一步</a>
			   </div>
			   <div class="span6" id="btn-right">
			   </div>
		  </div>
	</div>
	<%-- /按钮操作区 --%>
	<div id="footer" class="footer">
	<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
		<thead>
			<tr>
				<th><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
				<th class="center">票号</th>
				<th class="center">票据类型</th>
				<th class="center">票据品种</th>
				<th class="center">业务类型</th>
				<th class="center">出票日期</th>
				<th class="center">票面到期日</th>
				<th class="center">票面金额</th>
				<th class="center">付款方行号</th>
				<th class="center">承兑人</th>
				<th class="center">承兑人开户行行号</th>
				<th class="center">备注</th>
				<th class="center">详情</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${not empty resultList}">
				<c:forEach items="${resultList}" var="subcoll" varStatus="vs">
				<tr>
					<td class="center"><input type="checkbox" name="ids" value="${subcoll.id}" onclick="getall('ids')" price="${subcoll.billMoney}"/></td>
					<td class="center">${subcoll.billNo}</td>
					<td class="center">${fns:getDictLabel('K_BILL_TYPE',subcoll.billType)}</td>
					<td class="center">${fns:getDictLabel('K_BILL_CLASS',subcoll.billClass)}</td>
					<td class="center">${fns:getDictLabel('K_BUY_TYPE',subcoll.buyType)}</td>
					<td class="center">${subcoll.issueDt}</td>
					<td class="center">${subcoll.dueDt}</td>
					<td class="center">${fns:formateMoney(subcoll.billMoney)}</td>
					<td class="center">${subcoll.draweeBankNo}</td>
					<td class="center">${subcoll.acceptor}</td>
					<td class="center">${subcoll.acceptorBankNo}</td>
					<td class="center">${subcoll.remark}</td>
					<td class="center"><a href="javascript:goDetail('${subcoll.id}')">查看</a></td>
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
		<form  action="#" name="dataCollectForm" method="post"></form>
	</div>
	<div>
		<div id="page" class="pagination">
			<form action="<%=basePath%>subcollApplyController.do?method=elecBillManage"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="id" value="${id}"/>
				<input type="hidden" name="startDay" value="${startDay}"/>
				<input type="hidden" name="endDay" value="${endDay}"/>
				<input type="hidden" name="billType" value="${billType}"/>
				<input type="hidden" name="billClass" value="${billClass}"/>
				<input type="hidden" name="smallMoney" value="${smallMoney}"/>
				<input type="hidden" name="bigMoney" value="${bigMoney}"/>
				<input type="hidden" name="billNo" value="${billNo}"/>
				<input type="hidden" name="acceptor" value="${acceptor}"/>
			</form>
		</div>
	</div>
</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//查询
	function query(){
		var billNo = document.getElementById('billNo').value;
		var acceptor = document.getElementById('acceptor').value;
		var smallMoney = document.getElementById('smallMoney').value;
		var bigMoney = document.getElementById('bigMoney').value;
		var No = /^(\w{0,30}|d*)$/;
		var Money =/^(\d{0,10}(\.\d{0,2})?|d*)$/;
		var name=/^([\u4E00-\u9FFF]+|d*)$/;
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
	    	} else
	 	if(!name.test(acceptor)){
		bootbox.alert("请在输入承兑人时使用文字");
		return;
		}else{
			modal("#layer_loading,#image");//点击查询按钮，弹出‘加载中’的遮罩层
			$("#subcollManage").submit();
		}
	}
	//下一步
	function billManage(){
		var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请先选择要下一步操作的记录");
	  		return;
	  	 }
		var ids = getCheckStr("ids");
		var billClass= "${billClass}";
		var billType= "${billType}";
		var startDay= "${startDay}";
		var endDay= "${endDay}";
		var bigMoney = "${bigMoney}";
		var smallMoney = "${smallMoney}";
		var acceptor = "${acceptor}";
		var billNo = "${billNo}";
		dynamicHiddenElement('dataCollectForm','ids',ids);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		dynamicHiddenElement('dataCollectForm','billType',billType);
		dynamicHiddenElement('dataCollectForm','startDay',startDay);
		dynamicHiddenElement('dataCollectForm','endDay',endDay);
		dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
		dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
		dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
		dynamicHiddenElement('dataCollectForm','billNo',billNo);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=elecNextView";
		dataCollectForm.submit();
	} 
	//详情页面
	function goDetail(id){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId='+id;
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