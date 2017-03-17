<%-- 
 * 文件名称: sale_account_bill_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 待审核批次的票据清单列表页面
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-8-10
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
				<form action=""	method="post" name="infoForm" id="infoForm" class="form-search detail-form">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="inAcctName">账户名称</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.inAcctName}" readonly />
						<label for="inAcctType">账号类型</label>
						<input type="text" class="input-medium" name="inAcctType" id="inAcctType" value="${batch.inAcctType}" readonly/>
						<label for="inAcctNo">入账账号</label>
						<input type="text" class="input-medium" name="inAcctNo" readonly value="${batch.inAcctNo}" />
					</div>
					<div class="row-fluid">
						<label for="saleDt">转卖日</label>
						<input type="text" class="input-medium" name="saleDt" readonly value="${batch.saleDt}"/>
						<label for="saleType">转卖类型</label>
						<input type="text" class="input-medium" name="saleType" id="saleType" readonly value="${batch.saleType}" />
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" readonly value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="rate">利率</label>
						<input type="text" class="input-medium" name="rate" readonly value="${fns:formateRate(batch.rate)}"/>
					</div>
					<div class="row-fluid">
						<label for="custManage">客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" value="${batch.custManage}" readonly />
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" readonly />
						<label for="deptName">部门</label>
						<input type="text" class="input-medium" readonly name="deptName" value="${batch.deptName}" />
					</div>
					<div class="row-fluid">
						<label for="totalSize">合计张数</label>
						<input type="text" class="input-medium" name="totalSize" readonly value="${batch.totalSize}" />
						<label for="sumMoney">合计金额</label>
						<input type="text" class="input-medium" name="sumMoney" readonly value="${fns:formateMoney(batch.sumMoney)}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<c:if test="${flag=='1' }">
				<div id="center">
					<form id="btnForm" name="btnForm" action="<%=basePath%>saleAuditController.do?method=auditList" method="post">
						<div class="row-fluid">
							<div class="span6" id="btn-left">
								<a class="btn-mini" onclick="printList();">打印清单</a>	
							</div>
							<div class="span6" id="btn-right"> 
								<a class="btn-mini pull-right" onclick="toAudit();">审核</a>
								<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
							</div>
						</div>
					</form>
				</div>
			</c:if>
			<%-- 列表操作区 --%>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover"
					id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'salemxId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">计息到期日</th>
							<th class="center">计息天数</th>
							<th class="center">票面金额</th>
							<th class="center">利息</th>
							<th class="center">实收金额</th>
							<th class="center">承兑人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="saleBill" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='salemxId' value="${saleBill.salemxId}" /><span class="lbl"></span>
										</td>
										<td class="center">${saleBill.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleBill.billType)}</td>
										<td class="center">${saleBill.issueDt}</td>
										<td class="center">${saleBill.dueDt}</td>
										<td class="center">${saleBill.galeDate}</td>
										<td class="center">${saleBill.interestDays}</td>
										<td class="center">${fns:formateMoney(saleBill.billMoney)}</td>
										<td class="center">${fns:formateMoney(saleBill.interest)}</td>
										<td class="center">${fns:formateMoney(saleBill.receiveMoney)}</td>
										<td class="center">${saleBill.acceptor}</td>
										<td class="center">
											<a href="javascript:goBillInfo('${saleBill.salemxId}')">查看</a>
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
			<%-- 用于页面跳转 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- 分页 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>saleAuditController.do?method=auditDetailList"
					name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="saleId" value="${batch.saleId}" />
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
 	if('${batch.saleType}'=='0')
	{
		$("#saleType").val('买断');
	}else if('${batch.saleType}'=='1'){
		$("#saleType").val('回购');
	}
	if('${batch.inAcctType}'=='1'){
		$("#inAcctType").val('结算账户');
	}else if('${batch.inAcctType}'=='2'){
		$("#inAcctType").val('内部账户');
	}else if('${batch.inAcctType}'=='3'){
		$("#inAcctType").val('影子账户');
	}
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>saleAuditController.do?method=auditList";
		dataCollectForm.submit();
	}
	//详情页面
	function goBillInfo(salemxId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>saleApplyController.do?method=goBillInfo&salemxId='+salemxId;
		 diag.Width = 920;
		 diag.Height = 420;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//审核
	function toAudit(){
		 var checkNum = getCheckNum("salemxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请选择需要审核的记录");
	   		return;
	   	 }
		var ids = getCheckStr("salemxId");
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="审核";
		 diag.URL = "<%=basePath%>saleAuditController.do?method=toAudit&ids="+ids;
		 diag.Width = 430;
		 diag.Height =270;
		 diag.CancelEvent = function(){ //关闭事件
			 btnForm.submit();
			 diag.close();
		 };
		 diag.show();
	}
	//打印转贴现转出清单
    //operStatusString 票据状态
	function printList(){
		var batch_id="${batch.saleId}";
		var checkNum = getCheckNum("salemxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("salemxId");
		$.ajax({
			url:"saleApplyController.do?method=checkBillsHasCalcInterest",
			type:"post",
			data:{"ids":ids},
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
  					top.hangge();
					bootbox.alert(data.msg); 
				}else{
					doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1060301&type=SaleListPrint&baid="+batch_id+"&handleType=审 核"); 	 
				}
  			}
  		});	
	}
</script>
</body>
</html>