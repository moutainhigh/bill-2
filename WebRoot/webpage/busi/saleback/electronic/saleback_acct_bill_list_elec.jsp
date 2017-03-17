<%--
 * 文件名称: saleback_acct_bill_list_elec.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 票据列表页面
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%--jsp文件头和头部--%>
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
						</div>-->
						<label for="inAcctName">交易对手机构号</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.branchNo}" readonly />
						</div>
						<label for="rate">利率</label>
						<input type="text" class="input-medium" id ="rate" name="rate"  value="${fns:formateRate(batch.rate)}" onblur="checkRate();" readonly/>
					</div>
					<div class="row-fluid outer">
						<label for="inAcctName">账户名称</label>
						<input type="text" class="input-medium" id="inAcctName" name="inAcctName" value="${batch.inAcctName}" readonly />
						</div>
						<%--<label for="inAcctType">账<span style="margin:0 6px;"></span>号<span style="margin:0 6px;"></span>类<span style="margin:0 6px;"></span>型</label>
						<input type="text" class="input-medium" name="inAcctType" id="inAcctType" value="${batch.inAcctType}" readonly/>
						</div>--%>
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
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="submitacct();">确认记账</a>
							<a class="btn-mini pull-right" onclick="goBack();">返回</a>
						</div>
					</div>
				</form>
		  </div>
	      <%--/按钮操作区--%>
	      <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1400px;width:1400px;">
					<thead>
						<tr>
							<th class="center">
								<input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<%--<th class="center">计息到期日</th>--%>
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
										<td class="center"><input type="checkbox" name="ids"
											value="${saleBillInfo.salebackmxId}" onclick="getall('ids')" price="${saleBillInfo.billMoney}"/>
										</td>
										<td class="center">${saleBillInfo.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleBillInfo.billType)}</td>
										<td class="center">${saleBillInfo.issueDt}</td>
										<td class="center">${saleBillInfo.dueDt}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.billMoney)}</td>
										<%-- <td class="center">${saleBillInfo.galeDate}</td> --%>
										<td class="center">${saleBillInfo.interestDays}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.interest)}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.salebackMoney)}</td>
										<td class="center">${saleBillInfo.remitter}</td>
										<td class="center">${saleBillInfo.remitterBankNo}</td>
										<td class="center"><a href="javascript:goDetail('${saleBillInfo.rgctId}')">查看</a>
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
			<form  action="#" name="dataCollectForm" method="post"></form>
			 <%--/列表操作区--%>
				<div id="page" class="pagination">
					<form action="<%=basePath%>salebackAccountController.do?method=searchSaleBackAccountBillList"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						<input type="hidden" name="salebackId" id="salebackId" value="${query.salebackId}" />
					</form>
				</div>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function submitacct(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum ==0){
	   		bootbox.alert("请选择要进行确认记账操作的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		var salebackId = "${batch.salebackId}";
		bootbox.confirm("确定要确认记账选中的记录吗?", function(result) {
			if(result){
					top.jzts();
					$.ajax({
						type: "POST",
						url: '<%=basePath%>salebackAccountController.do?method=doAccountSubmit',
				    	data: {'ids': ids,'salebackId': salebackId},
						dataType:'json',
						//beforeSend: validateData,
						cache: false,
						success: function(data){	
							if (data.success){  //处理成功
			                    modal("#layer_loading,#image");
			                    dataCollectForm.action = "<%=basePath%>salebackAccountController.do?method=searchAccountableSalebackBatch";
			                    dataCollectForm.submit();
							} else {
								bootbox.alert(data.msg);
							}
						}
					});
			 	}
		   	});
	}
	function goBack(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>salebackAccountController.do?method=searchAccountableSalebackBatch";
		dataCollectForm.submit();
	}	
    //详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
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