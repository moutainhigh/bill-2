<%-- 
 * 文件名称: edit_bill_manage.jsp.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次下清单列表
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
	<title>票据管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
	<script type="text/javascript" src="webpage/system/addPrint/commonFunc.js"></script>
</head>
<body>
<div class="clearfix">
	<div id="page-content" class="page-content">
		<div id="header">
			<%-- 批次信息明细  --%>
			<form action="<%=basePath%>subcollApplyController.do?method=editBillManage"  method="post" name="subcollManage" id="subcollManage" class="form-search">
				<div class="row-fluid">
					<label for="batchId" class="pull-left control-label">批次号</label>
					<div class="pull-left batch">${batch.batchId}</div>
					<label for="billType">票据类型</label>
					<input type="text" class="input-medium" name="billType" value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}" readonly />
					<label for="billClass">票据品种</label>
					<input type="text" class="input-medium" name="billClass" value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" readonly />
				</div>
				<div class="row-fluid">
					<label for="totalMoney">总金额</label>
					<input type="text" class="input-medium" name="totalMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
					<label for="totalNum">票据张数</label>
					<input type="text" class="input-medium" name="totalNum" readonly value="${batch.totalNum}" />
					<label for="ems">EMS</label>
					<input type="text" class="input-medium" name="ems" id="ems"  value="${batch.ems}"  readonly/>
				</div>
			</form>
		</div>
		<%-- 按钮操作区 --%>
		<div id="center">
			<form  id="btnForm">
				<div class="row-fluid">
					<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="printDocument();">打印凭证</a>
						<a class="btn-mini" onclick="printList();">打印清单</a>
						<a class="btn-mini" onclick="addBatch();">添加</a>
						<a class="btn-mini" onclick="del();">删除</a>
				   </div>
				   <div class="span6" id="btn-right">
				   		<a class="btn-mini pull-right" onclick="apply();">提交</a>
						<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
				   </div>
			  	</div>
			</form>
		</div>
		<%-- 按钮操作区 --%>
	 	<div id="footer" class="footer">
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" 
			style="min-width:100%;max-width:1600px;width:1600px;">
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
									<td class="center"><input type="checkbox" name="ids" value="${subcoll.subcollmxId}"/></td>
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
		<%-- /列表操作区 --%>
		<div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>subcollApplyController.do?method=editBillManage"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="subcollId" value="${subcollId}"/>
					<input type="hidden" name="billClass" id="billClass" value="${billClass}"/>
				</form>
			</div>
		</div>
		<input type="hidden" name="subcollId" id="subcollId" value="${subcollId}"/>
		<input type="hidden" name="billClass" id="billClass" value="${billClass}"/>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		//dynamicHiddenElement('dataCollectForm','subcollId',subcollId);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=batchManage";
		dataCollectForm.submit();
	}
	//添加
	function addBatch(){
		var diag = new top.Dialog();
		var subcollId=document.getElementById("subcollId").value;
		var billType="${batch.billType}";
		var billClass="${batch.billClass}";
		 diag.Drag = true;
		 diag.Title ="添加";
		 diag.URL = "subcollApplyController.do?method=toAddBill&subcollId="+subcollId+"&billType="+billType+"&billClass="+billClass;
		 diag.Width = 1200;
		 diag.Height =470; 
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.action = "<%=basePath%>subcollApplyController.do?method=editBillManage";
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//删除票据信息
	function del(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	 }
		var subcollmxId = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		dynamicHiddenElement('dataCollectForm','subcollmxIds',subcollmxId);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=delBillForApply";
		dataCollectForm.submit();
	}
	//提交
	function apply(){
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要提交的记录");
	   		return;
	   	 }
		var subcollId = getCheckStr("ids");
		var billClass=document.getElementById("billClass").value;
		dynamicHiddenElement('dataCollectForm','subcollIds',subcollId);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=applyBill";
		dataCollectForm.submit();
	}
	//详情页面
	function goDetail(subcollmxId){
   	 var diag = new top.Dialog();
	 diag.Drag = true;
	 diag.Title ="详情";
	 diag.URL = '<%=basePath%>subcollApplyController.do?method=goSqlDetail&subcollmxId='+subcollmxId;
	 diag.Width = 930;
	 diag.Height =575;
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
			var mysubId="${batch.batchId}";
			//批次号			
    	  	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1070101&type=SubCollPzOtherPrint&baid="+mysubId);	
		} 
	
</script>
</body>
</html>