<%-- 
 * 文件名称: rebuy_acct_confirm_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 电票转入确认记账清单列表面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-10-28
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
				<form action=""	method="post" name="batchForm" id="batchForm" class="form-search detail-form">
					<input type="hidden" name="rebuyId" value="${batch.rebuyId}"/>
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch" name="batchNo" id="batchNo">${batch.batchNo}</div>
						<label for="rebuyDt">产品类型</label>
						<input type="text" class="input-medium" name="prodName" value="${prodName}" readonly />
						<input type="hidden" name="prodNo" value="${batch.prodNo}"/>
						<label for="rebuyDt">转入日</label>
						<input type="text" class="input-medium" name="rebuyDt" value="${batch.rebuyDt}" readonly />
					</div>
					<div class="row-fluid">
						<label for="custBankNo">交易对手行号</label>
						<input type="text" class="input-medium" name="custBankNo" value="${batch.custBankNo}" readonly />
						<label for="custBankName">交易对手名称</label>
						<input type="text" class="input-medium" name="custBankName" value="${batch.custBankName}" readonly />
						<label for="rate">利率</label>
						<input type="text" class="input-medium" name="rate" readonly value="${batch.rate}"/>
						<label for="cbRate">成本利率</label>
						<input type="text" class="input-medium" name="cbRate" value="${batch.cbRate}" readonly/>
					</div>
					<div class="row-fluid">
						<label for="">入账账号类型</label>
						<input type="text" class="input-medium" name="" value="结算账号" readonly />
						<label for="tradeAcct">入账账号</label>
						<input type="text" class="input-medium" name="tradeAcct" id="tradeAcct" value="${batch.tradeAcct}" readonly/>
						<label for="tradeAcctName">账户名称</label>
						<input type="text" class="input-medium" name="tradeAcctName" id="tradeAcctName" readonly value="${batch.tradeAcctName}" />
						<c:if test="${batch.discType=='1'}">
							<label for="resaleStaDt">赎回开放日</label>
							<input type="text" class="input-medium" name="resaleStaDt" id="resaleStaDt" value="${batch.resaleStaDt}" readonly />
						</c:if>
					</div>
					<div class="row-fluid">
						<label for="custManage">客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" id="custManage" value="${batch.custManage}" readonly/>
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" id="custManagerName" readonly value="${batch.custManagerName}"/>
						<label for="deptName">部门名称</label>
						<input type="text" class="input-medium" name="deptName" id="deptName" value="${batch.deptName}" readonly />
						<c:if test="${batch.discType=='1'}">
							<label for="resaleDueDt" >赎回截止日</label>
							<input type="text" class="input-medium" name="resaleDueDt" id="resaleDueDt" value="${batch.resaleDueDt}" readonly />
						</c:if>
					</div>
					<div class="row-fluid">
						<label for="totalMoney">票面汇总金额</label>
						<input type="text" class="input-medium" name="totalMoney" id="totalMoney" value="${fns:formateMoney(batch.totalMoney)}" readonly/>
						<label for="actualAmount">汇总实付金额</label>
						<input type="text" class="input-medium" name="actualAmount" id="actualAmount" readonly value="${fns:formateMoney(batch.actualAmount)}"/>
						<label for="totalNum">汇总张数</label>
						<input type="text" class="input-medium" name="totalNum" id="totalNum" readonly value="${batch.totalNum}"/>
						<label for="totalInterest">汇总利息</label>
						<input type="text" class="input-medium" name="totalInterest" id="totalInterest" value="${fns:formateMoney(batch.totalInterest)}" readonly />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="printPZ();">打印凭证</a>					
						<a class="btn-mini" onclick="printList();">打印清单</a>
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" href="javascript:finalAccount();">确认记账</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
						</div>
				   </div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:2000px;width:2000px;" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('allcheck', 'ids')">
								<input type="checkbox" id="allcheck" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">同城异地</th>
							<th class="center">计息到期日</th>
							<th class="center">票面金额</th>
							<th class="center">实付金额</th>
							<th class="center">试算利息</th>
							<th class="center">试算实付金额</th>
							<th class="center">清算方式</th>
							<th class="center">承兑人</th>
							<th class="center">承兑人开户行名称</th>
							<th class="center">承兑人行号</th>
							<th class="center">详细</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="billList" varStatus="vs">
									<tr>
										<td class="center"><input type="checkbox" name="ids" value="${billList.rebuymxId}" onclick="getall('ids')" price="${billList.billMoney}"/></td>
										<td class="center">${billList.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',billList.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',billList.billClass)}</td>
										<td class="center">${billList.issueDt}</td>
										<td class="center">${billList.dueDt}</td>
										<td class="center">${fns:getDictLabel('K_ISSAMECITY',billList.isSameCity)}</td>
										<td class="center">${billList.galeDate}</td>
										<td class="center">${fns:formateMoney(billList.billMoney)}</td>
										<td class="center">${fns:formateMoney(billList.payMoney)}</td>
										<td class="center">${fns:formateMoney(billList.checkInterest)}</td>
										<td class="center">${fns:formateMoney(billList.checkPayMoney)}</td>
										<td class="center">${fns:getDictLabel('K_ISONLINE',billList.isOnline)}</td>
										<td class="center">${billList.acceptor}</td>
										<td class="center">${billList.acceptorBankName}</td>
										<td class="center">${billList.acceptorBankNo}</td>
										<td class="center"><a onclick="goDetail('${billList.rgctId}');" style="cursor:pointer">详细</a></td>
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
			<div id="page" class="pagination">
				<form action="<%=basePath%>rebuyAccountController.do?method=elecAccountBillList"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="rebuyId" value="${batch.rebuyId}"/>
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//确认记账
	function finalAccount(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要记账确认的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
	   	dynamicHiddenElement('batchForm','ids',ids);
       	modal("#layer_loading,#image");
       	batchForm.action = "<%=basePath%>rebuyAccountController.do?method=accountConfirmSubmitElec";
       	batchForm.submit();
	}
	function goHistory(){
		modal("#layer_loading,#image");
		dataCollectForm.action = "<%=basePath%>rebuyAccountController.do?method=listAccountConfirmElec";
		dataCollectForm.submit();
	}
	 //打印清单
    //operStatusString 票据状态
	function printList(){
		var batch_id="${batch.rebuyId}";
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		$.ajax({
			url:"rebuyApplyController.do?method=isRate",
			type:"POST",
			data:{"ids":ids,"rebuyId":batch_id},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
				 	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1050101&type=RebuyListPrint&baid="+batch_id+"&handleType=确 认 记 账"); 	 
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常");
			}
		}); 	
	}
	//打印凭证
	function printPZ(){
		var payType=$("#payType").val();//付息方式 1买 2卖 3协议
		var batch_id="${batch.rebuyId}";
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		$.ajax({
			url:"rebuyApplyController.do?method=isRate",
			type:"POST",
			data:{"ids":ids,"rebuyId":batch_id},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
				 	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1050301&type=RebuyPZPrint&baid="+batch_id); 	 
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常");
			}
		}); 
	}
</script>
</body>
</html>