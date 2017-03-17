<%-- 
 * 文件名称: sale_apply_bill_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次下清单列表
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
			<div id="center">
				<form id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">添 加</a> 
							<a class="btn-mini" onclick="del();">删除</a> 
							<a class="btn-mini" onclick="trial();">利息计算</a>
							<a class="btn-mini" onclick="adjust();">利息调整</a> 
							<a class="btn-mini" onclick="printList();">打印清单</a>	
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="apply();">提交</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
						</div>
					</div>
				</form>
			</div>
			<%-- 列表操作区 --%>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'salemxId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">计息到期日</th>
							<th class="center">计息天数</th>
							<th class="center">利息支出</th>
							<th class="center">实收金额</th>
							<th class="center">承兑人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="saleBillInfo"
									varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='salemxId' value="${saleBillInfo.salemxId}" /><span class="lbl"></span>
										</td>
										<td class="center">${saleBillInfo.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleBillInfo.billType)}</td>
										<td class="center">${saleBillInfo.issueDt}</td>
										<td class="center">${saleBillInfo.dueDt}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.billMoney)}</td>
										<td class="center">${saleBillInfo.galeDate}</td>
										<td class="center">${saleBillInfo.interestDays}</td>
										<td class="center">${saleBillInfo.interest}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.receiveMoney)}</td>
										<td class="center">${saleBillInfo.acceptor}</td>
										<td class="center">
											<a href="javascript:goBillInfo('${saleBillInfo.salemxId}')">查看</a>
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
				<form action="<%=basePath%>saleApplyController.do?method=billManage" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="saleId" id="saleId" value="${batch.saleId}" />
				</form>
			</div>
		</div>
	</div>
<%@include file="/webpage/system/admin/modalDialog.jsp" %>
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
	//检查是否进行过利息试算
	function checkBillsHasCalcInterest(salemxId){
		var url = "<%=basePath%>saleApplyController.do?method=checkBillsHasCalcInterest&ids="+salemxId;
		$.ajax({
			url:url,
			type:"post",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
  					top.hangge();
					bootbox.alert(data.msg); 
					value=1;
					//localStorage.setItem('value','1');
				}else{
					//localStorage.setItem('value','0');
					value=0;
				}
  			}
  		});
	}
	//利息试算
	function trial(){
		var checkNum = getCheckNum("salemxId");
	   	if (checkNum ==0){
	   		bootbox.alert("请至少选择一条记录进行利息计算");
	   		return;
	   	 }
	   	 var ids = getCheckStr("salemxId");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="利息计算";
		 diag.URL = "<%=basePath%>saleApplyController.do?method=toInterestTrial&ids="+ids;
		 diag.Width = 480;
		 diag.Height = 260;
		 diag.CancelEvent = function(){ //关闭事件
			 pageForm.action = "<%=basePath%>saleApplyController.do?method=billManage";
			 pageForm.submit();
			diag.close();
		 };
		 diag.show(); 
	}
	//利息调整
	function adjust(){
		var checkNum = getCheckNum("salemxId");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录");
	   		return;
	   	 }
	   	 var salemxId = getCheckStr("salemxId");
	   	$.ajax({
			url:"saleApplyController.do?method=checkBillsHasCalcInterest",
			type:"post",
			data:{"ids":salemxId},
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
  					top.hangge();
					bootbox.alert(data.msg); 
				}else{
		   	 		 var diag = new top.Dialog();
					 diag.Drag = true;
					 diag.Title ="利息调整";
					 diag.URL = "<%=basePath%>saleApplyController.do?method=toInterestAdjust&salemxId="+salemxId;
					 diag.Width = 400;
					 diag.Height = 200;
					 diag.CancelEvent = function(){ //关闭事件
					 pageForm.action = "<%=basePath%>saleApplyController.do?method=billManage";
					 pageForm.submit();
					diag.close();
			 		};
				 diag.show(); 
				}
  			}
  		});
   	 }
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>saleApplyController.do?method=batch";
		dataCollectForm.submit();
	}
	//转卖申请提交
	function apply(){
		 var checkNum = getCheckNum("salemxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请至少选择一条记录进行转卖申请提交");
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
					dynamicHiddenElement('dataCollectForm', 'ids', ids);
					dynamicHiddenElement('dataCollectForm', 'saleId', "${batch.saleId}");
					dynamicHiddenElement('dataCollectForm', 'billClass', "${batch.billClass}");
					modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
					dataCollectForm.action = "<%=basePath%>saleApplyController.do?method=submitApply";
					dataCollectForm.submit();
				}
  			}
  		});
	}
	//添加
	function add(){
		var saleId = "${batch.saleId}";
		var billType="${batch.billType}";
		var billClass="${batch.billClass}";
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="添加";
		 diag.URL = "<%=basePath%>saleApplyController.do?method=toAddBill&saleId="+saleId+"&billType="+billType+"&billClass="+billClass;
		 diag.Width = 1200;
		 diag.Height =470;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.action = "<%=basePath%>saleApplyController.do?method=billManage";
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	function del(){
		var checkNum = getCheckNum("salemxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请选择记录");
	   		return;
	   	 }
		var ids = getCheckStr("salemxId");
		bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					url:"<%=basePath%>saleApplyController.do?method=delBill",
					type:"POST",
					data:{"ids":ids},
					dataType:"json",
					cache: false,
					success:function(data){
						if (data.success){  //处理成功
							bootbox.alert(data.msg); 
						} else {
							top.hangge();
							bootbox.alert(data.msg); 
						}
						pageForm.action = "<%=basePath%>saleApplyController.do?method=billManage";
						pageForm.submit();
					}
				});
		  	}
	   	});
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
					doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1060301&type=SaleListPrint&baid="+batch_id+"&handleType=申 请"); 	 
				}
  			}
  		});	
	}
</script>
</body>
</html>
