<%-- 
 * 文件名称:_apply_detail_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-9-13
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
	<title>发生查询</title>
	<style>
		#s2id_OperationName{width:100%;}
	</style>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action="<%=basePath%>happenSearchController.do?method=happenSearch"  method="post" name="Form" id="Form" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid" >
						<label for="industry_investment">票据类型</label>
						<t:dictSelect className="select-medium" name="billType" other="" dictGroup="K_BILL_TYPE" defaultVal="${query.billType}" haveHead="true"  >
						</t:dictSelect>
						<label for="industry_investment">票据品种</label>
						<t:dictSelect className="select-medium"  name="billClass" other="" dictGroup="K_BILL_CLASS" defaultVal="${query.billClass}" haveHead="true"  >
						</t:dictSelect>
						<label for="totalNum">交易类型</label>
						<select  class="select2" name="serviceType" defaultVal="${serviceType}" valid="required" id="serviceType">
						 	<option value="0">请选择</option>
							<option value="1" <c:if test="${serviceType==1}">selected</c:if>>贴现</option>
							<option value="2" <c:if test="${serviceType==2}">selected</c:if>>转入</option>
							<option value="3" <c:if test="${serviceType==3}">selected</c:if>>转出</option>
							<option value="4" <c:if test="${serviceType==4}">selected</c:if>>质押</option>
							<option value="5" <c:if test="${serviceType==5}">selected</c:if>>委托收款</option>
					    </select>
						<a class="btn-mini" id="search" href="javascript:searchd();">查询</a>
					</div>
				</form>
			</div>
	<%-- 按钮操作区 --%>
	<div id="center">
		<form id="btnForm" name="btnForm">
			<div class="row-fluid" style="margin-bottom:0px;">
				<div class="span6" id="btn-left">
					<a class="btn-mini pull-left" href="javascript:getExportInfo();">导出</a>
				</div>
		  	</div>
		</form>
	</div>	
			<div id="footer" class="footer">
				<c:if test="${serviceType=='1'}">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:4000px;width:4000px;">
						<thead>
							<tr>
								<th class="center">序号</th>
								<th class="center">批次号</th>
								<th class="center">贴现日</th>
								<th class="center">记账日</th>
								<th class="center">客户名称</th>
								<th class="center">买入贴现类型</th>
								<th class="center">贴现帐号</th>
								<th class="center">票号</th>
								<th class="center">当前状态</th>
								<th class="center">票据类型</th>
								<th class="center">票据品种</th>
								<th class="center">票面金额</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">计息天数</th>
								<th class="center">计息到期日</th>
								<th class="center">贴现利率</th>
								<th class="center">贴现利息</th>
								<th class="center">实付金额</th>
								<th class="center">利息支出</th>
								<th class="center">成本利率</th>
								<th class="center">付息方式</th>
								<th class="center">出票人</th>
								<th class="center">出票人开户行</th>
								<th class="center">收款人</th>
								<th class="center">收款人开户行名称</th>
								<th class="center">承兑人开户行名称</th>
								<th class="center">承兑人开户行行号</th>
								<th class="center">客户经理名称</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="list" varStatus="vs">
										<tr>
											<td class="center">${vs.index+1}</td>
											<td class="center">${list.apply.batchNo}</td>
											<td class="center">${list.apply.discDt}</td>
											<td class="center">${list.bill.accountDate}</td>
											<td class="center">${list.apply.custName}</td>
											<td class="center">一般贴现</td>
											<td class="center">${list.apply.custAccount}</td>
											<td class="center">${list.bill.billNo}</td>
											<td class="center">${fns:getDictLabel("K_CURSTATUS",list.bill.curStatus)}</td>
											<td class="center">${fns:getDictLabel("K_BILL_TYPE",list.apply.billType)}</td>
											<td class="center">${fns:getDictLabel("K_BILL_CLASS",list.apply.billClass)}</td>
											<td class="center">${fns:formateMoney(list.bill.billMoney)}</td>
											<td class="center">${list.bill.issueDt}</td>
											<td class="center">${list.bill.dueDt}</td>
											<td class="center">${list.bill.interestDays}</td>
											<td class="center">${list.bill.galeDate}</td>
											<td class="center">${list.apply.rate}</td>
											<td class="center">${fns:formateMoney(list.bill.salerInterest)}</td>
											<td class="center">${fns:formateMoney(list.bill.payMoney)}</td>
											<td class="center">${fns:formateMoney(list.bill.totalIntrstPayment)}</td>
											<td class="center">${list.apply.cbRate}</td>
											<td class="center">${fns:getDictLabel("K_PAY_TYPE",list.apply.payType)}</td>
											<td class="center">${list.bill.remitter}</td>
											<td class="center">${list.bill.remitterBankName}</td>
											<td class="center">${list.bill.payee}</td>
											<td class="center">${list.bill.payeeBankName}</td>
											<td class="center">${list.bill.acceptorBankName}</td>
											<td class="center">${list.bill.acceptorBankNo}</td>
											<td class="center">${list.apply.custManagerName}</td>
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
				</c:if>
				<c:if test="${serviceType=='2'}">
					<table class="table table-striped table-bordered table-hover rebuy" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:4000px;width:4000px;">
						<thead>
							<tr>
								<th class="center">序号</th>
								<th class="center">批次号</th>
								<th class="center">转入日</th>
								<th class="center">记账日</th>
								<th class="center">转入类型</th>
								<th class="center">票号</th>
								<th class="center">当前状态</th>
								<th class="center">票据种类</th>
								<th class="center">票据品种</th>
								<th class="center">票面金额</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">赎回开放日</th>
								<th class="center">赎回截止日</th>
								<th class="center">计息天数</th>
								<th class="center">计息到期日</th>
								<th class="center">转贴现利率</th>
								<th class="center">转贴现利息</th>
								<th class="center">实付金额</th>
								<th class="center">成本利率</th>
								<th class="center">出票人</th>
								<th class="center">出票人开户行</th>
								<th class="center">承兑人开户行名称</th>
								<th class="center">承兑人开户行行号</th>
								<th class="center">客户经理名称</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="rebuyresultList" varStatus="vs">
										<tr>
											<td class="center">${vs.index+1}</td>
											<td class="center">${rebuyresultList.apply.batchNo}</td>
											<td class="center">${rebuyresultList.bill.rebuyDt}</td>
											<td class="center">${rebuyresultList.bill.accountDate}</td>
											<td class="center">${fns:getDictLabel("K_ISREGRESS",rebuyresultList.apply.discType)}</td>
											<td class="center">${rebuyresultList.bill.billNo}</td>
											<td class="center">${fns:getDictLabel("K_CURSTATUS",rebuyresultList.bill.curStatus)}</td>
											<td class="center">${fns:getDictLabel("K_BILL_TYPE",rebuyresultList.bill.billType)}</td>
											<td class="center">${fns:getDictLabel("K_BILL_CLASS",rebuyresultList.bill.billClass)}</td>
											<td class="center">${fns:formateMoney(rebuyresultList.bill.billMoney)}</td>
											<td class="center">${rebuyresultList.bill.issueDt}</td>
											<td class="center">${rebuyresultList.bill.dueDt}</td>
											<td class="center">${rebuyresultList.bill.resaleStaDt}</td>
											<td class="center">${rebuyresultList.bill.resaleDueDt}</td>
											<td class="center">${rebuyresultList.bill.interestDays}</td>
											<td class="center">${rebuyresultList.bill.galeDate}</td>
											<td class="center">${rebuyresultList.apply.rate}</td>
											<td class="center">${rebuyresultList.bill.checkInterest}</td>
											<td class="center">${fns:formateMoney(rebuyresultList.bill.payMoney)}</td>
											<td class="center">${rebuyresultList.apply.cbRate}</td>
											<td class="center">${rebuyresultList.bill.remitter}</td>
											<td class="center">${rebuyresultList.bill.remitterBankName}</td>
											<%--<td class="center">${rebuyresultList.apply.billStorageOrgName}</td>--%>
											<td class="center">${rebuyresultList.bill.acceptorBankName}</td>
											<td class="center">${rebuyresultList.bill.acceptorBankNo}</td>
											<td class="center">${rebuyresultList.apply.custManagerName}</td>
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
				</c:if>
				<c:if test="${serviceType=='3'}">
					<table class="table table-striped table-bordered table-hover sale" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:4000px;width:4000px;">
						<thead>
							<tr>
								<th class="center">序号</th>
								<th class="center">批次号</th>
								<th class="center">转出贴现客户</th>
								<th class="center">转卖日</th>
								<th class="center">记账日</th>
								<th class="center">转卖类型</th>
								<th class="center">票号</th>
								<th class="center">当前状态</th>
								<th class="center">票据品种</th>
								<th class="center">票据类型</th>
								<th class="center">票面金额</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">卖出赎回开放日</th>
								<th class="center">卖出赎回截止日</th>
								<th class="center">计息天数</th>
								<th class="center">计息到期日</th>
								<th class="center">转出利率</th>
								<th class="center">转出利息</th>
								<th class="center">实收金额</th>
								<th class="center">出票人</th>
								<th class="center">出票人开户行</th>
								<th class="center">承兑人开户行名称</th>
								<th class="center">承兑人开户行行号</th>
								<th class="center">档案编号</th>
								<th class="center">卖出客户经理名称</th>
								<th class="center">交易对手</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="saleresultList" varStatus="vs">
									<tr>
										<td class="center">${vs.index+1}</td>
										<td class="center">${saleresultList.apply.batchNo}</td>
										<td class="center">${saleresultList.apply.custName}</td>
										<td class="center">${saleresultList.apply.saleDt}</td>
										<td class="center">${saleresultList.bill.accountDate}</td>
										<td class="center">${fns:getDictLabel("K_BUY_TYPE",saleresultList.apply.saleType)}</td>
										<td class="center">${saleresultList.bill.billNo}</td>
										<td class="center">${fns:getDictLabel("K_CURSTATUS",saleresultList.bill.curStatus)}</td>
										<td class="center">${fns:getDictLabel("K_BILL_CLASS",saleresultList.apply.billClass)}</td>
										<td class="center">${fns:getDictLabel("K_BILL_TYPE",saleresultList.apply.billType)}</td>
										<td class="center">${fns:formateMoney(saleresultList.bill.billMoney)}</td>
										<td class="center">${saleresultList.bill.issueDt}</td>
										<td class="center">${saleresultList.bill.dueDt}</td>
										<td class="center">${saleresultList.apply.buybackOpenDt}</td>
										<td class="center">${saleresultList.apply.rebuyDueDt}</td>
										<td class="center">${saleresultList.bill.interestDays}</td>
										<td class="center">${saleresultList.bill.galeDate}</td>
										<td class="center">${saleresultList.apply.rate}</td>
										<td class="center">${fns:formateMoney(saleresultList.bill.interest)}</td>
										<td class="center">${fns:formateMoney(saleresultList.bill.receiveMoney)}</td>
										<td class="center">${saleresultList.bill.remitter}</td>
										<td class="center">${saleresultList.bill.remitterBankName}</td>
										<td class="center">${saleresultList.bill.acceptorBankName}</td>
										<td class="center">${saleresultList.bill.acceptorBankNo}</td>
										<td class="center">${saleresultList.bill.oldFileNo}</td>
										<td class="center">${saleresultList.apply.custManagerName}</td>
										<td class="center">${saleresultList.apply.mockAimName}</td>
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
			</c:if>
			<c:if test="${serviceType=='4'}">
				<table class="table table-striped table-bordered table-hover savelist" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2500px;width:2500px;">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th class="center">批次号</th>
							<th class="center">质押日期</th>
							<th class="center">客户名称</th>
							<th class="center">票号</th>
							<th class="center">当前状态</th>
							<th class="center">票据品种</th>
							<th class="center">票据类型</th>
							<th class="center">票面金额</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">出票人</th>
							<th class="center">出票人开户行</th>
							<th class="center">承兑人开户行名称</th>
							<th class="center">承兑人开户行行号</th>
							<th class="center">客户经理名称</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="saveresultList" varStatus="vs">
									<tr>
										<td class="center">${vs.index+1}</td>
										<td class="center">${saveresultList.apply.batchNo}</td>
										<td class="center">${saveresultList.bill.accountDate}</td>
										<td class="center">${saveresultList.apply.custName}</td>
										<td class="center">${saveresultList.bill.billNo}</td>
										<td class="center">${saveresultList.bill.gathType}</td>
										<td class="center">${fns:getDictLabel("K_BILL_CLASS",saveresultList.bill.billClass)}</td>
										<td class="center">${fns:getDictLabel("K_BILL_TYPE",saveresultList.bill.billType)}</td>
										<td class="center">${fns:formateMoney(saveresultList.bill.billMoney)}</td>
										<td class="center">${saveresultList.bill.issueDt}</td>
										<td class="center">${saveresultList.bill.dueDt}</td>
										<td class="center">${saveresultList.bill.remitter}</td>
										<td class="center">${saveresultList.bill.remitterBankName}</td>
										<td class="center">${saveresultList.bill.acceptorBankName}</td>
										<td class="center">${saveresultList.bill.acceptorBankNo}</td>
										<td class="center">${saveresultList.apply.custManagerName}</td>
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
			</c:if>
			<c:if test="${serviceType=='5'}">
				<table class="table table-striped table-bordered table-hover subcoll" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:4000px;width:4000px;">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th class="center">批次号</th>
							<th class="center">发托日期</th>
							<th class="center">记账日</th>
							<th class="center">买入贴现类型</th>
							<th class="center">票号</th>
							<th class="center">当前状态</th>
							<th class="center">票据来源</th>
							<th class="center">票据品种</th>
							<th class="center">票据类型</th>
							<th class="center">票面金额</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">计息到期日</th>
							<th class="center">客户名称</th>
							<th class="center">出票人</th>
							<th class="center">出票人开户行</th>
							<th class="center">承兑人</th>
							<th class="center">承兑人开户行名称</th>
							<th class="center">承兑人开户行行号</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="subcollresultList" varStatus="vs">
									<tr>
										<td class="center">${vs.index+1}</td>
										<td class="center">${subcollresultList.apply.batchId}</td>
										<td class="center">${subcollresultList.bill.collDate}</td>
										<td class="center">${subcollresultList.bill.gathDate}</td>
										<td class="center">${fns:getDictLabel("K_BUY_TYPE",subcollresultList.bill.buyType)}</td>
										<td class="center">${subcollresultList.bill.billNo}</td>
										<td class="center">${fns:getDictLabel("K_CURSTATUS",subcollresultList.bill.curStatus)}</td>
										<td class="center">${subcollresultList.bill.billSource}</td>
										<td class="center">${fns:getDictLabel("K_BILL_CLASS",subcollresultList.apply.billClass)}</td>
										<td class="center">${fns:getDictLabel("K_BILL_TYPE",subcollresultList.apply.billType)}</td>
										<td class="center">${fns:formateMoney(subcollresultList.bill.billMoney)}</td>
										<td class="center">${subcollresultList.bill.issueDt}</td>
										<td class="center">${subcollresultList.bill.dueDt}</td>
										<td class="center">${subcollresultList.bill.galeDate}</td>
										<td class="center">${subcollresultList.bill.custName}</td>
										<td class="center">${subcollresultList.bill.remitter}</td>
										<td class="center">${subcollresultList.bill.remitterBankName}</td>
										<td class="center">${subcollresultList.bill.acceptor}</td>
										<td class="center">${subcollresultList.bill.acceptorBankName}</td>
										<td class="center">${subcollresultList.bill.acceptorBankNo}</td>
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
			</c:if>
		</div>
		
		<%-- /列表操作区 --%>
		<div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>happenSearchController.do?method=happenSearch" method="post" name="pageForm">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="billType" value="${query.billType}"/>
					<input type="hidden" name="billClass" value="${query.billClass}"/>
					<input type="hidden" name="serviceType" value="${serviceType}"/>
				</form>
			</div>
			<form  action="#" name="dataCollectForm" method="post"></form>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function searchd(){
		//if($("#serviceType").valid()){
		if($("#serviceType").val()!=null&&$("#serviceType").val()!="0"){
			modal("#layer_loading,#image");
			$("#Form").submit();
		}else{
			bootbox.alert("请选择业务类型！");
		}
	}
	//导出个性化页面
	function getExportInfo(){
		var serviceType = $("#serviceType").val();
		if( serviceType !=null && serviceType != "-1"){
			var tableName = "";
			if("1"==serviceType){//贴现余额
				tableName = "VW_DISC_HAPPEN";
			}else if("2"==serviceType){//转贴现转入余额
				tableName = "VW_REBUY_HAPPEN";
			}else if("3"==serviceType){//质押票据
				tableName = "VW_SALE_HAPPEN";
			}else if("4"==serviceType){//质押票据
				tableName = "VW_SAVE_HAPPEN";
			}else if("5"==serviceType){//托收在途
				tableName = "VW_SUBCOLL_HAPPEN";
			}
			var frameId = window.frameElement && window.frameElement.id || '';
			var diag = new top.Dialog();
		 	diag.Drag = true;
		 	diag.Title ="详情";
			diag.URL = "<%=basePath%>exportController.do?method=getExportInfo&tableName="+tableName+"&action=exportController.do?method=commonExport&formName=Form&frameId="+frameId;
			diag.Width = 800;
		 	diag.Height = 575;
		 	diag.CancelEvent = function(){ //关闭事件
				diag.close();
		 	};
		 	diag.show();
		}else{
			bootbox.alert("请选择业务类型！");
		}
	}
</script>
</body>
</html>