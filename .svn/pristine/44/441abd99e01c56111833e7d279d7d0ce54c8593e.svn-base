<%-- 
 * 文件名称: rebuy_riskbill.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-9-10
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
		<div id="page-content" class="page-content" style="background:#f4f8fb;">
			<div id="">
				<div class="row-fluid" style="margin-bottom:4px;">
					<h3 style="margin-top:0px;">${msg}</h3>
				</div>
			</div>
			<div id="center" style="margin-top: 20px;">
				<form  id="btnForm">
					<div class="row-fluid">
						   <div style="font-size: 16px; float:left;" class="span6">是否继续？</div>
						   <div class="span6" id="btn-left">
						   		<a class="btn-mini pull-right" onclick="top.Dialog.close();">取消</a>
								<a class="btn-mini pull-right" style="margin-right:10px;" onclick="next();">继续</a>
							</div>
					  </div>
				</form>
			</div>
			<div id="rebuyFooter" >
				<fieldset>
					<h5>风险票列表</h5>
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">承兑人</th>
							<th class="center">出票人</th>
							<th class="center">出票人开户行</th>
							<th class="center">票面金额</th>
							<th class="center">出票日</th>
							<th class="center">到期日</th>
							<th class="center">风险描述</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="risk" varStatus="vs">
								<c:if test="${risk.isRiskBill=='1'}">
									<tr>
										<td class="center">${risk.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',risk.billType)}</td>
										<td class="center">${risk.acceptor}</td>
										<td class="center">${risk.remitter}</td>
										<td class="center">${risk.remitterBankNo}</td>
										<td class="center">${fns:formateMoney(risk.billMoney)}</td>
										<td class="center">${risk.issueDt}</td>
										<td class="center">${risk.dueDt}</td>
										<td class="center">${risk.checkResult}</td>
									</tr>
								</c:if>
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
			</fieldset>
			<div class="space"></div>
				<fieldset>
					<h5>黑名单列表</h5>
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th class="center">票号</th>
								<th class="center">票据类型</th>
								<th class="center">承兑人</th>
								<th class="center">出票人开户行</th>
								<th class="center">票面金额</th>
								<th class="center">出票日</th>
								<th class="center">到期日</th>
								<th class="center">黑名单企业</th>
								<th class="center">黑名单理由</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="risk" varStatus="vs">
										<c:if test="${risk.isBlackBill=='1'}">
											<tr>
												<td class="center">${risk.billNo}</td>
												<td class="center">${fns:getDictLabel('K_BILL_TYPE',risk.billType)}</td>
												<td class="center">${risk.acceptor}</td>
												<td class="center">${risk.remitterBankNo}</td>
												<td class="center">${fns:formateMoney(risk.billMoney)}</td>
												<td class="center">${risk.issueDt}</td>
												<td class="center">${risk.dueDt}</td>
												<td class="center">${risk.enterpriseName}</td>
												<td class="center">${risk.desc}</td>
											</tr>
										</c:if>
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
				</fieldset>
				<div class="space"></div>
			<fieldset>
			<h5>账务库存票列表</h5>
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class="center">票号</th>
						<th class="center">票据类型</th>
						<th class="center">承兑人</th>
						<th class="center">出票人开户行</th>
						<th class="center">票面金额</th>
						<th class="center">出票日</th>
						<th class="center">到期日</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="risk" varStatus="vs">
								<c:if test="${risk.isAcctBalanceBill=='1'}">
									<tr>
										<td class="center">${risk.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',risk.billType)}</td>
										<td class="center">${risk.acceptor}</td>
										<td class="center">${risk.remitterBankNo}</td>
										<td class="center">${fns:formateMoney(risk.billMoney)}</td>
										<td class="center">${risk.issueDt}</td>
										<td class="center">${risk.dueDt}</td>
									</tr>
								</c:if>
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
		</fieldset>
	</div>
</div>
</div>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function next(){
		top.Dialog.close();
	}
</script>
</body>
</html>