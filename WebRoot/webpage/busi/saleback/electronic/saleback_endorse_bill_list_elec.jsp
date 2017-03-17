<%--
 * 文件名称: account_bill_list.jsp
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
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="infoForm" id="infoForm" class="form-search">
					<%--查询区 --%>
					<div class="row-fluid firstLine">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="billType">票据类型</label>
						<input type="text" class="input-medium" name="billType" id="billType" value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}" readonly/>
						<label for="inAcctNo">票据品种</label>
						<input type="text" class="input-medium" name="inAcctNo" readonly value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
					</div>
					<div class="row-fluid outer">
						<label for="inAcctName">客户名称</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.custName}" readonly />
						<%--	<label for="inAcctName"><span style="margin:0 4px;"></span>交易对手名称</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.custName}" readonly />
						<label for="inAcctName">交易对手机构号</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.branchNo}" readonly />
						<label for="rate">利率</label>
						<input type="text" class="input-medium" id ="rate" name="rate"  value="${fns:formateRate(batch.rate)}" onblur="checkRate();" readonly/>
					</div>
					<div class="row-fluid outer">
						<label for="inAcctName">账户名称</label>
						<input type="text" class="input-medium" id="inAcctName" name="inAcctName" value="${batch.inAcctName}" readonly />
						<%--<label for="inAcctType">账<span style="margin:0 6px;"></span>号<span style="margin:0 6px;"></span>类<span style="margin:0 6px;"></span>型</label>
						<input type="text" class="input-medium" name="inAcctType" id="inAcctType" value="${batch.inAcctType}" readonly/>
						--%>
						<label for="inAcctNo">入账账号</label>
						<input type="text" class="input-medium" id="inAcctNo" name="inAcctNo"  value="${batch.inAcctNo}" onblur="fillAcctInfo();" readonly/>
						<label for="billType">清算方式</label>
						<c:if test="${batch.isOnline=='0'}">
							<input type="text" class="input-medium" readonly name="Online" id="Online" value="线下清算"/>
						</c:if>
						<c:if test="${batch.isOnline=='1'}">
							<input type="text" class="input-medium" readonly name="Online" id="Online" value="线上清算"/>
						</c:if>
						<input type="hidden" name="isOnline"  id="isOnline" value="${batch.isOnline}"/>
					</div>
					<div class="row-fluid threeLine">
						<label for="saleDt">返售开放日</label>
						<input type="text" class="input-medium input-date" name="saleDt" readonly value="${batch.salebackOpenDt}"/>
						<label for="saleType">返售截止日</label>
						<input type="text" class="input-medium input-date" name="saleType" readonly id="saleType" value="${batch.salebackDueDt}" />
						<label for="billType">返售日期</label>
						<input class="input-medium input-date layinput" name="createDt"  id="createDt" value="${batch.createDt}" type="text" placeholder="系统日期" title="请输入有效的日期 (YYYY-MM-DD)" valid="required dateISO" readonly/>
					</div>
					<div class="row-fluid fiveLine">
						<label for="totalSize">合计张数</label>
						<input type="text" class="input-medium" name="totalSize" readonly value="${batch.totalNum}" />
						<label for="sumMoney">合计金额</label>
						<input type="text" class="input-medium" name="sumMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
					</div>
				</form>
			</div>
			<%--按钮操作区--%>
			<div id="center">
				<form id="btnForm" name="btnForm" action="<%=basePath%>saleEndorseController.do?method=auditList" method="post">
					<div class="row-fluid">
						<div class="span6" id="btn-left"></div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="endorse();">提交</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a> 
						</div>
					</div>
				</form>
			</div>
			<%--列表操作区--%>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover"
					id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1400px;width:1400px">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'salemxId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">返售到期日</th>
							<th class="center">票面金额</th>
							<th class="center">出票日</th>
							<th class="center">计息天数</th>
							<th class="center">利息支出</th>
							<th class="center">实收金额</th>
							<th class="center">出票人</th>
							<th class="center">出票人开户行行号</th>
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
											<input type='checkbox' name='salemxId' value="${saleBillInfo.salebackmxId}" onclick="getall('salemxId')" price="${saleBillInfo.billMoney}"/><span class="lbl"></span>
											</td>
										<td class="center">${saleBillInfo.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleBillInfo.billType)}</td>
										<td class="center">${saleBillInfo.salebackDueDt}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.billMoney)}</td>
										<td class="center">${saleBillInfo.issueDt}</td>
										<td class="center">${saleBillInfo.interestDays}</td>
										<td class="center">${saleBillInfo.interest}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.salebackMoney)}</td>
										<td class="center">${saleBillInfo.remitter}</td>
										<td class="center">${saleBillInfo.remitterBankNo}</td>
										<td class="center">
											<a href="javascript:goDetail('${saleBillInfo.rgctId}')">查看</a>
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
			<div id="select-Info">
				<div id="selectInfo"><center>暂时没有相关数据</center></div>
			</div>
			<%--用于页面跳转--%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%--分页--%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>SalebackApplyController.do?method=salebackendorseapply"
					name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="saleId" value="${batch.salebackId}" />
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>SalebackApplyController.do?method=searchEndorsableSaleBackBatch";
		dataCollectForm.submit();
	}
	//详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="票据详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	//提交背书
	function endorse(){
		 var checkNum = getCheckNum("salemxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要进行背书提交的记录");
	   		return;
	   	 }
	   	 var salebackId = "${batch.salebackId}";
		var ids = getCheckStr("salemxId");
		dynamicHiddenElement("dataCollectForm", "salebackmxIds", ids);
		dynamicHiddenElement("dataCollectForm", "salebackId", salebackId);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>SalebackApplyController.do?method=submitSalebackEndorse";
		dataCollectForm.submit();
	}
</script>
</body>
</html>