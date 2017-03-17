<%-- 
 * 文件名称: balance_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-09-05
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
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-family:'微软雅黑';background:#f4f8fb;">
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid" style="margin-bottom:0px;">
						<div class="span6" id="btn-left">
						    <a class="btn-mini" href="javascript:goBack();">返回</a>
					   </div>
					   <div class="span6" id="btn-right">
					   </div>
				  </div>
				</form>
			</div>
			<%-- 列表操作区 --%>
			<table class="table table-bordered" id="tab" cellpadding="0" cellspacing="0" style="margin-bottom:0px;">
				<c:if test="${serviceType=='1'}">
					<tbody>
						<tr>
							<td>票号</td>
							<td>${obj.bill.billNo}</td>
							<td>当前状态</td>
							<td>${fns:getDictLabel('K_CURSTATUS',obj.bill.curStatus)}</td>
							<td>买入贴现类型</td>
							<td>一般贴现</td>
							<%-- 
							<td>${obj.bill.discType}</td>
							 --%>
						</tr>
						<tr>
							<td>票据类型</td>
							<td>${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
							<td>票据种类</td>
							<td>${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
							<td>批次号</d>
							<td>${obj.apply.batchNo }</d>
						</tr>
						<tr>
							<td>票面金额</td>
							<td>${fns:formateMoney(obj.bill.billMoney)}</td>
							<td>贴现日</td>
							<td>${obj.apply.discDt}</td>
							<td>记账日</td>
							<td>${obj.bill.accountDate}</td>
						</tr>
						<tr>
							<td>出票日</td>
							<td>${obj.bill.issueDt}</td>
							<td>票面到期日</td>
							<td>${obj.bill.dueDt}</td>
							<td>计息天数</td>
							<td>${obj.bill.interestDays}</td>
						</tr>
						<tr>
							<td>计息到期日</td>
							<td>${obj.bill.galeDate}</td>
							<td>贴现利率</td>
							<td>${fns:formateRate(obj.apply.rate)}%</td>
							<td>贴现利息</td>
							<td>${fns:formateMoney(obj.bill.totalIntrstPayment)}</td>
						</tr>
						<tr>
							<td>实付金额</td>
							<td>${fns:formateMoney(obj.bill.payMoney)}</td>
							<td>成本利率</td>
							<td>${fns:formateRate(obj.apply.cbRate)}%</td>
							<td>付息方式</td>
							<td>${fns:getDictLabel('K_PAY_TYPE',obj.apply.payType)}</td>
						</tr>
						<tr>
							<td>付息比例</td>
							<td>${obj.apply.buyPayRate}</td>
							<td>付息人名称</td>
							<td>${obj.apply.payCustName}</td>
							<td>付息人行号</td>
							<td>${obj.apply.payBankNo}</td>
						</tr>
						<tr>
							<td>付息人账号</td>
							<td>${obj.apply.payAccount}</td>
							<td>客户名称</td>
							<td>${obj.apply.custName}</td>
						</tr>
						<tr>
							<td>出票人</td>
							<td>${obj.bill.remitter}</td>
							<td>出票人开户行行号</td>
							<td>${obj.bill.remitterBankNo}</td>
							<td>出票人开户行名称</td>
							<td>${obj.bill.remitterBankName }</td>
						</tr>
						<tr>
							<td>出票人账号</td>
							<td>${obj.bill.remitterAcct}</td>
							<td>承兑人</td>
							<td>${obj.bill.acceptor}</td>
							<td>承兑人开户行行号</td>
							<td>${obj.bill.acceptorBankNo}</td>
						</tr>
						<tr>
							<td>承兑人开户行名称</td>
							<td>${obj.bill.acceptorBankName}</td>
							<td>承兑人账号</td>
							<td>${obj.bill.acceptorAcct}</td>
							<td>收款人</td>
							<td>${obj.bill.payee}</td>
						</tr>
						<tr>
							<td>收款人开户行行号</td>
							<td>${obj.bill.payeeBankNo}</td>
							<td>收款人开户行名称</td>
							<td>${obj.bill.payeeBankName}</td>
							<td>收款人账号</td>
							<td>${obj.bill.payeeAcct}</td>
						</tr>
						<tr>
							<td>经营归属机构</td>
							<td>${obj.apply.profOwner}</td>
							<td>所属部门</td>
							<td>${obj.apply.deptName}</td>
							<td>客户经理名称</td>
							<td>${obj.apply.custManagerName}</td>
						</tr>
						<tr>
							<td>档案编号</td>
							<td>${obj.bill.fileNo}</td>
						</tr>
					</tbody>
				</c:if>
				<c:if test="${serviceType=='2'}">
					<tbody>
						<tr>
							<td>票号</td>
							<td>${obj.bill.billNo}</td>
							<td>当前状态</td>
							<td>${fns:getDictLabel('K_CURSTATUS',obj.bill.curStatus)}</td>
							<td>买入贴现类型</td>
							<td>${obj.apply.discType }</td>
						</tr>
						<tr>
							<td>票据类型</td>
							<td>${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
							<td>票据种类</td>
							<td>${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
							<td>批次号</d>
							<td>${obj.apply.batchNo }</d>
						</tr>
						<tr>
							
							<td>票面金额</td>
							<td>${fns:formateMoney(obj.bill.billMoney)}</td>
							<td>转入日</td>
							<td>${obj.apply.rebuyDt}</td>
							<td>记账日</td>
							<td>${obj.bill.accountDate}</td>
						</tr>
						<tr>
							<td>出票日</td>
							<td>${obj.bill.issueDt}</td>
							<td>票面到期日</td>
							<td>${obj.bill.dueDt}</td>
							<td>计息天数</td>
							<td>${obj.bill.interestDays}</td>
						</tr>
						<tr>
							<td>计息到期日</td>
							<td>${obj.bill.galeDate}</td>
							<td>转贴现利率</td>
							<td>${fns:formateRate(obj.apply.rate)}%</td>
							<td>转贴现利息</td>
							<td>${fns:formateMoney(obj.bill.interest)}</td>
						</tr>
						<tr>
							<td>实付金额</td>
							<td>${fns:formateMoney(obj.bill.payMoney)}</td>
							<td>成本利率</td>
							<td>${fns:formateRate(obj.apply.cbRate)}%</td>
							<td>客户名称</td>
							<td>${obj.apply.custName}</td>
						</tr>
						<tr>
							<td>出票人</td>
							<td>${obj.bill.remitter}</td>
							<td>出票人开户行行号</td>
							<td>${obj.bill.remitterBankNo}</td>
							<td>出票人开户行名称</td>
							<td>${obj.bill.remitterBankName }</td>
						</tr>
						<tr>
							<td>出票人账号</td>
							<td>${obj.bill.remitterAcct}</td>
							<td>承兑人</td>
							<td>${obj.bill.acceptor}</td>
							<td>承兑人开户行行号</td>
							<td>${obj.bill.acceptorBankNo}</td>
						</tr>
						<tr>
							<td>承兑人开户行名称</td>
							<td>${obj.bill.acceptorBankName}</td>
							<td>收款人</td>
							<td>${obj.bill.payee}</td>
							<td>收款人开户行行号</td>
							<td>${obj.bill.payeeBankNo}</td>
						</tr>
						<tr>
							<td>收款人开户行名称</td>
							<td>${obj.bill.payeeBankName}</td>
							<td>收款人账号</td>
							<td>${obj.bill.payeeAcct}</td>
							<td>经营归属机构</td>
							<td>${obj.apply.profOwner}</td>
						</tr>
						<tr>
							<td>所属部门</td>
							<td>${obj.apply.deptName}</td>
							<td>客户经理名称</td>
							<td>${obj.apply.custManagerName}</td>
							<td>档案编号</td>
							<td>${obj.apply.fileNo}</td>
						</tr>
					</tbody>
				</c:if>
				<c:if test="${serviceType=='5'}">
					<tbody>
						<tr>
							<td>批次号</d>
							<td>${obj.apply.batchNo }</d>
							<td>客户名称</td>
							<td>${obj.apply.custName}</td>
							<td>质押日期</td>
							<td>${obj.bill.accountDate}</td>
						</tr>
						<tr>
							<td>票号</td>
							<td>${obj.bill.billNo}</td>
							<td>当前状态</td>
							<td></td>
							<td>票据类型</td>
							<td>${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
						</tr>
						<tr>
							<td>票据种类</td>
							<td>${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
							<td>票面金额</td>
							<td>${fns:formateMoney(obj.bill.billMoney)}</td>
							<td>出票日</td>
							<td>${obj.bill.issueDt}</td>
						</tr>
						<tr>
							<td>票面到期日</td>
							<td>${obj.bill.dueDt}</td>
							<td>出票人</td>
							<td>${obj.bill.remitter}</td>
							<td>出票人开户行行号</td>
							<td>${obj.bill.remitterBankNo}</td>
						</tr>
						<tr>
							<td>出票人开户行名称</td>
							<td>${obj.bill.remitterBankName }</td>
							<td>出票人账号</td>
							<td>${obj.bill.remitterAcct}</td>
							<td>承兑人</td>
							<td>${obj.bill.acceptor}</td>
						</tr>
						<tr>
							<td>承兑人开户行行号</td>
							<td>${obj.bill.acceptorBankNo}</td>
							<td>承兑人开户行名称</td>
							<td>${obj.bill.acceptorBankName}</td>
							<td>承兑人协议号</td>
							<td>${obj.bill.acceptProtocolNo}</td>
						</tr>
						<tr>
							<td>收款人</td>
							<td>${obj.bill.payee}</td>
							<td>收款人开户行行号</td>
							<td></td>
							<td>收款人开户行名称</td>
							<td>${obj.bill.payeeBankName}</td>
						</tr>
						<tr>
							<td>收款人账号</td>
							<td>${obj.bill.payeeAcct}</td>
							<td>所属部门</td>
							<td>${obj.apply.deptName}</td>
							<td>客户经理名称</td>
							<td>${obj.apply.custManagerName}</td>
						</tr>
					</tbody>
				</c:if>
				<c:if test="${serviceType=='6'}">
					<tbody>
						<tr>
							<td>票号</td>
							<td>${obj.bill.billNo}</td>
							<td>当前状态</td>
							<td>${fns:getDictLabel('K_CURSTATUS',obj.bill.curStatus)}</td>
							<td>买入贴现类型</td>
							<td>${fns:getDictLabel('K_BUY_TYPE',obj.bill.buyType)}</td>
						</tr>
						<tr>
							<td>票据类型</td>
							<td>${fns:getDictLabel('K_BILL_TYPE',obj.bill.billType)}</td>
							<td>票据种类</td>
							<td>${fns:getDictLabel('K_BILL_CLASS',obj.bill.billClass)}</td>
							<td>批次号</d>
							<td>${obj.apply.batchId }</d>
						</tr>
						<tr>
							<td>票面金额</td>
							<td>${fns:formateMoney(obj.bill.billMoney)}</td>
							<td>发托日期</td>
							<td>${obj.bill.collDate}</td>
							<td>贴现日</td>
							<td>${obj.bill.discDt}</td>
							
						</tr>
						<tr>
							<td>记账日</td>
							<td>${obj.bill.gathDate}</td>
							<td>出票日</td>
							<td>${obj.bill.issueDt}</td>
							<td>票面到期日</td>
							<td>${obj.bill.dueDt}</td>
						</tr>
						<tr>
							<td>计息天数</td>
							<td>${obj.bill.interestDays}</td>
							<td>计息到期日</td>
							<td>${obj.bill.galeDate}</td>
							<td>贴现利率</td>
							<td>${fns:formateRate(obj.bill.rate)}%</td>
						</tr>
						<tr>
							<td>贴现利息</td>
							<td>${fns:formateMoney(obj.bill.interest)}</td>
							<td>实付金额</td>
							<td>${fns:formateMoney(obj.bill.payMoney)}</td>
							<td>客户名称</td>
							<td>${obj.bill.custName}</td>
						</tr>
						<tr>
							<td>出票人</td>
							<td>${obj.bill.remitter}</td>
							<td>出票人开户行行号</td>
							<td>${obj.bill.remitterBankNo}</td>
							<td>出票人开户行名称</td>
							<td>${obj.bill.remitterBankName }</td>
						</tr>
						<tr>
							<td>出票人账号</td>
							<td>${obj.bill.remitterAcct}</td>
							<td>承兑人</td>
							<td>${obj.bill.acceptor}</td>
							<td>承兑人开户行行号</td>
							<td>${obj.bill.acceptorBankNo}</td>
						</tr>
						<tr>
							<td>承兑人开户行名称</td>
							<td>${obj.bill.acceptorBankName}</td>
							<td>承兑人账号</td>
							<td>${obj.bill.acceptorAcct}</td>
							<td>收款人</td>
							<td>${obj.bill.payee}</td>
						</tr>
						<tr>
							<td>收款人开户行行号</td>
							<td>${obj.bill.payeeBankNo}</td>
							<td>收款人开户行名称</td>
							<td>${obj.bill.payeeBankName}</td>
							<td>收款人账号</td>
							<td>${obj.bill.payeeAcct}</td>
						</tr>
					</tbody>
				</c:if>
			</table>
			<%-- 用于页面跳转 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	$(document).ready(function(){
		$("#tab td").css("padding","7px");
	})
	function goBack(){
		dynamicHiddenElement('dataCollectForm', 'serviceType', "${serviceType}");
		dynamicHiddenElement('dataCollectForm', 'billNo', "${billNo}");
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>balanceSearchController.do?method=searchBalance";
		dataCollectForm.submit();
	}
</script>
</body>
</html>