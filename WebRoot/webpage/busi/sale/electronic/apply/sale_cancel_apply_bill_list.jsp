<%-- 
 * 文件名称: sale_cancel_apply_bill_list.jsp
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
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="infoForm" id="infoForm" class="form-search detail-form">
					<%-- 查询区  --%>
					<div class="row-fluid firstLine">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="billType">票据类型</label>
						<input type="text" class="input-medium" name="billType" id="billType" value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}" readonly/>
						<label for="inAcctNo">票据品种</label>
						<input type="text" class="input-medium" name="inAcctNo" readonly value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<span class="outer">
							<label for="custType">客户类型</label>
							<input type="text" class="input-medium" name="custType" readonly value="${fns:getDictLabel('K_KHLX',batch.custType)}" />
						</span>
					</div>
					<div class="row-fluid outer">
						<label for="inAcctName">客户行号</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.aimBranchNo}" readonly />
						<label for="inAcctName">客户名称</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.custName}" readonly />
						<label for="innerAccount">客户账号</label>
						<input type="text" class="input-medium" name="innerAccount" value="${batch.innerAccount}" readonly />
					</div>
					<div class="row-fluid inner secondLine">
						<label for="inAcctName">客户机构号</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.branchNo}" readonly />
						<label for="inAcctName">客户机构名称</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.custName}" readonly />
					</div>
					<div class="row-fluid outer">
						<label for="inAcctName">账户名称</label>
						<input type="text" class="input-medium" name="inAcctName" value="${batch.inAcctName}" readonly />
						<label for="inAcctType">账户类型</label>
						<input type="text" class="input-medium" name="inAcctType" id="inAcctType" value="${batch.inAcctType}" readonly/>
						<label for="inAcctNo">入账账号</label>
						<input type="text" class="input-medium" name="inAcctNo" readonly value="${batch.inAcctNo}" />
						<label for="billType">清算方式</label>
						<input type="text" class="input-medium" name="billType" readonly value="${fns:getDictLabel('K_ISONLINE',batch.isOnline)}" />
					</div>
					<div class="row-fluid threeLine">
						<label for="saleDt">转卖日</label>
						<input type="text" class="input-medium" name="saleDt" readonly value="${batch.saleDt}"/>
						<label for="saleType">转卖类型</label>
						<input type="text" class="input-medium" name="saleType" readonly id="saleType" value="${batch.saleType}" />
						<label for="rate">利率</label>
						<input type="text" class="input-medium" name="rate" readonly value="${fns:formateRate(batch.rate)}"/>
					</div>
					<div class="row-fluid fourLine">
						<label for="custManage">客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" value="${batch.custManage}" readonly />
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" readonly />
						<label for="deptName">部门</label>
						<input type="text" class="input-medium" readonly name="deptName" value="${batch.deptName}" />
						<label for="deptName">是否禁止背书</label>
						<input type="text" class="input-medium" readonly name="forbidFlag" value="${fns:getDictLabel('K_YORN',batch.forbidFlag)}" />
					</div>
					<div class="row-fluid fiveLine">
						<label for="totalSize">合计张数</label>
						<input type="text" class="input-medium" name="totalSize" readonly value="${batch.totalSize}" />
						<label for="sumMoney">合计金额</label>
						<input type="text" class="input-medium" name="sumMoney" readonly value="${fns:formateMoney(batch.sumMoney)}" />
						<label for="totalSize" class="rebuyDt">赎回开放日</label>
						<input type="text" class="input-medium rebuyDt" name="buybackOpenDt" readonly value="${batch.buybackOpenDt}" />
						<label for="sumMoney" class="rebuyDt">赎回截止日</label>
						<input type="text" class="input-medium rebuyDt" name="rebuyDueDt" readonly value="${batch.rebuyDueDt}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left"></div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="cancelApply();">确认</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a> 
						</div>
					</div>
				</form>
			</div>
			<%-- 列表操作区 --%>
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
											<input type='checkbox' name='salemxId' value="${saleBillInfo.salemxId}" onclick="getall('salemxId')" price="${saleBillInfo.billMoney}"/><span class="lbl"></span>
										</td>
										<td class="center">${saleBillInfo.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleBillInfo.billType)}</td>
										<td class="center">${saleBillInfo.issueDt}</td>
										<td class="center">${saleBillInfo.dueDt}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.billMoney)}</td>
										<td class="center">${saleBillInfo.galeDate}</td>
										<td class="center">${saleBillInfo.interestDays}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.interest)}</td>
										<td class="center">${fns:formateMoney(saleBillInfo.receiveMoney)}</td>
										<td class="center">${saleBillInfo.acceptor}</td>
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
			<%-- 用于页面跳转 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- 分页 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>saleApplyController.do?method=cancelApplyBillManage" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="saleId" id="saleId" value="${batch.saleId}" />
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script src="weblib/bizjs/busiSaleEle.js"></script>
<script>
$(document).ready(function(){
		var prodNo='${batch.prodNo}';
		var batchInAcctype='${batch.inAcctType}';
		checkType(prodNo,batchInAcctype);
	})
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>saleApplyController.do?method=cancelElecBatch";
		dataCollectForm.submit();
	}
	//转卖申请撤销
	function cancelApply(){
		 var checkNum = getCheckNum("salemxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请至少选择一条要进行撤销转卖申请的记录");
	   		return;
	   	 }
		var ids = getCheckStr("salemxId");
		dynamicHiddenElement('dataCollectForm', 'ids', ids);
		dynamicHiddenElement('dataCollectForm', 'saleId', "${batch.saleId}");
		dynamicHiddenElement('dataCollectForm', 'billClass', "${batch.billClass}");
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>saleApplyController.do?method=cancelApply";
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
