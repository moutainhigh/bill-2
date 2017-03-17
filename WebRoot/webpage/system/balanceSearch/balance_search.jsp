<%-- 
 * 文件名称: balance_search.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 余额查询首页面
 * 系统版本: @version2.0.0.1
 * 开发人员: 
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
	<%-- jsp文件头和头部 --%>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
		<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>balanceSearchController.do?method=searchBalance"  method="post" name="Form" id="Form" class="form-search">
					<div class="row-fluid" style="margin-bottom:0px;">
						<label class="no-padding-right" for="serviceType"><span class="star">*</span>业务种类</label>
						<select class="select2 select-medium" name="serviceType" id="serviceType" valid="required" defaultValue="serviceType">
							<option value="-1">请选择</option>
							<option value="1" <c:if test="${serviceType==1}">selected</c:if>>贴现余额</option>
							<option value="2" <c:if test="${serviceType==2}">selected</c:if>>转贴现转入余额</option>
							<option value="5" <c:if test="${serviceType==5}">selected</c:if>>质押票据</option>
							<option value="6" <c:if test="${serviceType==6}">selected</c:if>>托收在途</option>
						</select>
						<label class="no-padding-right" for="billNo">票号</label>
						<input class="input-medium" type="text" name="billNo" value="${billNo}" placeholder="请输入票号"/>
						<c:if test="${branchNo=='0001'}"> 
							<label for="branch_no" class="no-padding-right">所属机构</label>
							<sys:treeselect className="input-medium" id="branchNo" name="branchNo"
								value="${branch.branchNo}" module="2"
								labelName="branch_name" labelValue="${branch.branchName}"
								title="机构" url="tagController.do?method=treeData"
								extId="${branchNo}" />
						</c:if>
						<a class="btn-mini" id="search" href="javascript:searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form id="btnForm" name="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini pull-left" href="javascript:getExportInfo();">导出</a>
						</div>
				  	</div>
				</form>
			</div>
	   		<%-- 列表操作区 --%>
		    <div id="footer">
				<c:if test="${serviceType=='1'}">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2800px;width:2800px;">
						<thead>
							<tr>
								<th class="center">票号</th>
								<th class="center">当前状态</th>
								<th class="center">买入贴现类型</th>
								<th class="center">票据品种</th>
								<th class="center">票据类型</th>
								<th class="center">批次号</th>
								<th class="center">票面金额</th>
								<th class="center">贴现日</th>
								<th class="center">记账日</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">计息天数</th>
								<th class="center">计息到期日</th>
								<th class="center">贴现利率</th>
								<th class="center">贴现利息</th>
								<th class="center">实付金额</th>
								<th class="center">成本利率</th>
								<th class="center">付息方式</th>
								<th class="center">客户名称</th>
								<th class="center">出票人</th>
								<th class="center">出票人开户行</th>
								<th class="center">承兑人开户行名称</th>
								<th class="center">承兑人开户行行号</th>
								<th class="center">经营归属机构</th>
								<th class="center">所属部门</th>
								<th class="center">客户经理名称</th>
								<th class="center">档案编号</th>
								<th class="center">详情</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="obj" varStatus="vs">
										<tr>
											<td class="center">${obj.bill.billNo}</td>
											<td class="center">${fns:getDictLabel('K_CURSTATUS',obj.bill.curStatus)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
											<td class="center">${obj.apply.batchNo}</td>
											<td class="center">${fns:formateMoney(obj.bill.billMoney)}</td>
											<td class="center">${obj.apply.discDt}</td>
											<td class="center">${obj.bill.accountDate}</td>
											<td class="center">${obj.bill.issueDt}</td>
											<td class="center">${obj.bill.dueDt}</td>
											<td class="center">${obj.bill.interestDays}</td>
											<td class="center">${obj.bill.galeDate}</td>
											<td class="center">${fns:formateRate(obj.apply.rate)}</td>
											<td class="center">${fns:formateMoney(obj.bill.totalIntrstPayment)}</td>
											<td class="center">${fns:formateMoney(obj.bill.payMoney)}</td>
											<td class="center">${fns:formateRate(obj.apply.cbRate)}</td>
											<td class="center">${fns:getDictLabel('K_PAY_TYPE',obj.apply.payType)}</td>
											<td class="center">${obj.apply.custName}</td>
											<td class="center">${obj.bill.remitter}</td>
											<td class="center">${obj.bill.remitterBankName }</td>
											<td class="center">${obj.bill.acceptorBankName}</td>
											<td class="center">${obj.bill.acceptorBankNo}</td>
											<td class="center">${obj.apply.profOwner}</td>
											<td class="center">${obj.apply.deptName}</td>
											<td class="center">${obj.apply.custManagerName}</td>
											<td class="center">${obj.bill.fileNo}</td>
											<td class="center">
												<a href="javascript:goBalanceDetail('${obj.bill.discmxId}')">查看</a>
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
				</c:if>
				<c:if test="${serviceType=='2' }">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2800px;width:2800px;">
						<thead>
							<tr>
								<th class="center">票号</th>
								<th class="center">当前状态</th>
								<th class="center">买入贴现类型</th>
								<th class="center">票据品种</th>
								<th class="center">票据类型</th>
								<th class="center">批次号</th>
								<th class="center">票面金额</th>
								<th class="center">转入日</th>
								<th class="center">记账日</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">计息天数</th>
								<th class="center">计息到期日</th>
								<th class="center">转贴现利率</th>
								<th class="center">转贴现利息</th>
								<th class="center">实付金额</th>
								<th class="center">成本利率</th>
								<th class="center">客户名称</th>
								<th class="center">出票人</th>
								<th class="center">出票人开户行</th>
								<th class="center">纸票保管机构</th>
								<th class="center">承兑人开户行名称</th>
								<th class="center">承兑人开户行行号</th>
								<th class="center">经营归属机构</th>
								<th class="center">所属部门</th>
								<th class="center">客户经理名称</th>
								<th class="center">档案编号</th>
								<th class="center">详情</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="obj" varStatus="vs">
										<tr>
											<td class="center">${obj.bill.billNo}</td>
											<td class="center">${fns:getDictLabel('K_CURSTATUS',obj.bill.curStatus)}</td>
											<td class="center">${fns:getDictLabel('K_PROD_NO',obj.apply.prodNo)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
											<td class="center">${obj.apply.batchNo}</td>
											<td class="center">${fns:formateMoney(obj.bill.billMoney)}</td>
											<td class="center">${obj.apply.rebuyDt}</td>
											<td class="center">${obj.bill.accountDate}</td>
											<td class="center">${obj.bill.issueDt}</td>
											<td class="center">${obj.bill.dueDt}</td>
											<td class="center">${obj.bill.interestDays}</td>
											<td class="center">${obj.bill.galeDate}</td>
											<td class="center">${fns:formateRate(obj.apply.rate)}</td>
											<td class="center">${fns:formateMoney(obj.bill.interest)}</td>
											<td class="center">${fns:formateMoney(obj.bill.payMoney)}</td>
											<td class="center">${fns:formateRate(obj.apply.cbRate)}</td>
											<td class="center">${obj.apply.custName}</td>
											<td class="center">${obj.bill.remitter}</td>
											<td class="center">${obj.bill.remitterBankName }</td>
											<td class="center">${obj.bill.billStorageOrg}</td>
											<td class="center">${obj.bill.acceptorBankName}</td>
											<td class="center">${obj.bill.acceptorBankNo}</td>
											<td class="center">${obj.apply.profOwner}</td>
											<td class="center">${obj.apply.deptName}</td>
											<td class="center">${obj.apply.custManagerName}</td>
											<td class="center">${obj.apply.fileNo }</td>
											<td class="center">
												<a href="javascript:goBalanceDetail('${obj.bill.rebuymxId}')">查看</a>
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
				</c:if>
				<c:if test="${serviceType=='5'}">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1700px;width:1700px;">
						<thead>
							<tr>
								<th class="center">票号</th>
								<th class="center">当前状态</th>
								<th class="center">票据品种</th>
								<th class="center">票据类型</th>
								<th class="center">批次号</th>
								<th class="center">票面金额</th>
								<th class="center">质押日期</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">客户名称</th>
								<th class="center">出票人</th>
								<th class="center">出票人开户行</th>
								<th class="center">承兑人开户行名称</th>
								<th class="center">承兑人开户行行号</th>
								<th class="center">所属部门</th>
								<th class="center">客户经理名称</th>
								<th class="center">详情</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="obj" varStatus="vs">
										<tr>
											<td class="center">${obj.bill.billNo}</td>
											<td class="center"></td>
											<td class="center">${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
											<td class="center">${obj.apply.batchNo}</td>
											<td class="center">${fns:formateMoney(obj.bill.billMoney)}</td>
											<td class="center">${obj.bill.accountDate}</td>
											<td class="center">${obj.bill.issueDt}</td>
											<td class="center">${obj.bill.dueDt}</td>
											<td class="center">${obj.apply.custName}</td>
											<td class="center">${obj.bill.remitter}</td>
											<td class="center">${obj.bill.remitterBankName }</td>
											<td class="center">${obj.bill.acceptorBankName}</td>
											<td class="center">${obj.bill.acceptorBankNo}</td>
											<td class="center">${obj.apply.deptName}</td>
											<td class="center">${obj.apply.custManagerName}</td>
											<td class="center">
												<a href="javascript:goBalanceDetail('${obj.bill.savemxId}')">查看</a>
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
				</c:if>
				<c:if test="${serviceType=='6'}">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2300px;width:2300px;">
						<thead>
							<tr>
								<th class="center">票号</th>
								<th class="center">当前状态</th>
								<th class="center">买入贴现类型</th>
								<th class="center">票据来源</th>
								<th class="center">票据品种</th>
								<th class="center">票据类型</th>
								<th class="center">批次号</th>
								<th class="center">票面金额</th>
								<th class="center">发托日期</th>
								<th class="center">贴现日</th>
								<th class="center">记账日</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">计息天数</th>
								<th class="center">计息到期日</th>
								<th class="center">贴现利率</th>
								<th class="center">贴现利息</th>
								<th class="center">实付金额</th>
								<th class="center">客户名称</th>
								<th class="center">出票人</th>
								<th class="center">出票人开户行</th>
								<th class="center">承兑人</th>
								<th class="center">承兑人开户行名称</th>
								<th class="center">承兑人开户行行号</th>
								<th class="center">详情</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="obj" varStatus="vs">
										<tr>
											<td class="center">${obj.bill.billNo}</td>
											<td class="center">${fns:getDictLabel('K_CURSTATUS',obj.bill.curStatus)}</td>
											<td class="center">${fns:getDictLabel('K_BUY_TYPE',obj.bill.buyType) }</td>
											<td class="center">${fns:getDictLabel('K_BILL_SOURCE',obj.bill.billSource) }</td>
											<td class="center">${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
											<td class="center">${obj.apply.batchId}</td>
											<td class="center">${fns:formateMoney(obj.bill.billMoney)}</td>
											<td class="center">${obj.bill.collDate}</td>
											<td class="center">${obj.bill.discDt}</td>
											<td class="center">${obj.bill.gathDate }</td>
											<td class="center">${obj.bill.issueDt}</td>
											<td class="center">${obj.bill.dueDt}</td>
											<td class="center">${obj.bill.interestDays}</td>
											<td class="center">${obj.bill.galeDate}</td>
											<td class="center">${fns:formateRate(obj.bill.rate)}</td>
											<td class="center">${fns:formateMoney(obj.bill.interest)}</td>
											<td class="center">${fns:formateMoney(obj.bill.payMoney)}</td>
											<td class="center">${obj.bill.custName}</td>
											<td class="center">${obj.bill.remitter}</td>
											<td class="center">${obj.bill.remitterBankName }</td>
											<td class="center">${obj.bill.acceptor}</td>
											<td class="center">${obj.bill.acceptorBankName}</td>
											<td class="center">${obj.bill.acceptorBankNo}</td>
											<td class="center">
												<a href="javascript:goBalanceDetail('${obj.bill.subcollmxId}')">查看</a>
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
				</c:if>
			</div>
			<%-- 分页 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>balanceSearchController.do?method=searchBalance"
					name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="serviceType" value="${serviceType}"/>
					<input type="hidden" name="billNo" value="${billNo}"/>
					<c:if test="${branchNo=='0001'}"> 
						<input type="hidden" name="branchNo" value="${branchNo}"/>
					</c:if>
				</form>
			</div>
			<form  action="#" name="dataCollectForm" method="post"></form>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	$("#tab th,td").addClass("center");
	function searchd(){
		if($("#serviceType").val()!=null&&$("#serviceType").val()!="-1"){
			modal("#layer_loading,#image");
			$("#Form").submit();
		}else{
			showTips("serviceType","请选择业务类型！");
		}
	}
	function goBalanceDetail(xxxmxId){
		dynamicHiddenElement('pageForm', 'xxxmxId', xxxmxId);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		pageForm.action = "<%=basePath%>balanceSearchController.do?method=goBalanceDetail";
		pageForm.submit();
	}
	//导出个性化页面
	function getExportInfo(){
		var serviceType = $("#serviceType").val();
		if( serviceType !=null && serviceType != "-1"){
			var tableName = "";
			if("1"==serviceType){//贴现余额
				tableName = "VW_DISC_BALANCE";
			}else if("2"==serviceType){//转贴现转入余额
				tableName = "VW_REBUY_BALANCE";
			}else if("5"==serviceType){//质押票据
				tableName = "VW_SAVE_BALANCE";
			}else if("6"==serviceType){//托收在途
				tableName = "VW_SUBCOLL_BALANCE";
			}
			var frameId = window.frameElement && window.frameElement.id || '';
			var diag = new top.Dialog();
		 	diag.Drag = true;
		 	diag.Title ="详情";
			diag.URL = "<%=basePath%>exportController.do?method=getExportInfo&tableName="+tableName+"&action=balanceSearchController.do?method=doExport&formName=Form&frameId="+frameId;
			diag.Width = 800;
		 	diag.Height = 575;
		 	diag.CancelEvent = function(){ //关闭事件
				diag.close();
		 	};
		 	diag.show();
		}else{
			showTips("serviceType","请选择业务类型！");
		}
	}
</script>
</body>
</html>