<%-- 
 * 文件名称: accountFlow_info_search.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 账务流水查询清单页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wzc
 * 开发时间: 2016-12-14
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
				<form action=""	method="post" name="batchForm" id="batchForm" class="form-search">
					<div class="row-fluid">
						<label for="foreFlowNo" class="pull-left control-label">前台流水号</label>
						<input class="input-medium" type="text" name="foreFlowNo" value="${batch.foreFlowNo}" readonly />
						<label for="transNo">交易编码</label>
						<input class="input-medium" type="text" name="transNo"  value="${batch.transNo}" readonly/>
						<label for="transDt">交易日期</label>
						<input class="input-medium" class="input-medium" type="text" name="transDt"  value="${batch.transDt}" readonly/>
					</div>
					<div class="row-fluid">
						<label for="transTm" class="pull-left control-label">交易时间</label>
						<input class="input-medium" type="text" name="transTm"  value="${batch.transTm}" readonly/>
						<label for="acctType">交易类型</label>
						<input class="input-medium" type="text" name="acctType" readonly value="${fns:getDictLabel('K_ACCT_TYPE',batch.acctType)}" readonly/>
						<label for="acctStatus"class="col-md-2 control-label">交易状态</label>
						<input class="input-medium" type="text"  name="acctStatus" readonly value="${fns:getDictLabel('K_ACCT_STATUS',batch.acctStatus)}" readonly/>
					</div>
					<div class="row-fluid" >
						<label for="prodNo" class="pull-left control-label">产品编码</label>
						<input class="input-medium" type="text"  name="prodNo"  value="${fns:getDictLabel('K_PROD_NO',batch.prodNo)}" readonly/>
                        <label for="transBranchNo">交易机构号</label>
						<input class="input-medium" type="text"  name="transBranchNo"  value="${batch.transBranchNo}" readonly/>
						<label for="transBranchName">交易机构名称</label>
						<input class="input-medium" type="text" name="transBranchName" readonly value="${batch.transBranchName}" readonly/>
					</div>
					<div class="row-fluid" >
						<label for="transUserNo" class="pull-left control-label">交易机构柜员</label>
						<input class="input-medium" type="text" name="transUserNo" readonly value="${batch.transUserNo}" readonly/>
						<label for="acctBranchNo">账务所属机构号</label>
						<input class="input-medium" type="text" name="acctBranchNo" readonly value="${batch.acctBranchNo}" readonly/>
						<label for="totalAmount">票面总额</label>
						<input class="input-medium" type="text" name="totalAmount" readonly value="${fns:formateMoney(batch.totalAmount)}" readonly/>
					</div>
					<div class="row-fluid" >				
						<label for="settlementMoney" class="pull-left control-label">实收（付）金额</label>
						<input class="input-medium" type="text" name="settlementMoney" readonly value="${fns:formateMoney(batch.settlementMoney)}" readonly/>
						<label for="settlementInterest">应收（付）利息</label>
						<input class="input-medium" type="text" name="settlementInterest" readonly value="${fns:formateMoney(batch.settlementInterest)}" readonly/>
					</div>			
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action=""  method="post" id="btnForm" name="btnForm" style="padding:0px;" class="form-search">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
					   </div>
					   <div class="span6" id="btn-right">
					        <a class="btn-mini pull-right" onclick="goHistory();">返回</a>				
					   </div>
				  </div>
			   </form>
		   </div>
		   <div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;width:1600px;max-width:1600px;">
					<thead>
						<tr>
							<th class="center">票号</th>
							<th class="center">交易编码</th>
							<th class="center">交易日期</th>
							<th class="center">交易时间</th>
							<th class="center">产品编码</th>
							<th class="center">票面金额</th>
							<th class="center">实收（付）金额</th>
							<th class="center">应收（付）金额</th>
							<th class="center">交易机构</th>
							<th class="center">账务机构</th>
							<th class="center">待摊销利息</th>
							<th class="center">当前未摊销金额</th>
							<th class="center">利息损益</th>
							<th class="center">摘要</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="acct" varStatus="vs">
									<tr>
										<td class="center">${acct.billNo}</td>
										<td class="center">${acct.transNo}</td>
										<td class="center">${acct.transDt}</td>
										<td class="center">${acct.transTm}</td>
										<td class="center">${fns:getDictLabel('K_PROD_NO',acct.prodNo)}</td>
										<td class="center">${fns:formateMoney(acct.billAmonut)}</td>
										<td class="center">${fns:formateMoney(acct.settlementAmt)}</td>
										<td class="center">${fns:formateMoney(acct.settlementIntrst)}</td>
										<td class="center">${acct.transBranchNo}</td>
										<td class="center">${acct.acctBranchNo}</td>
										<td class="center">${acct.remaIntrst}</td>
										<td class="center">${acct.curRemaIntrst}</td>
										<td class="center">${acct.discrepancyInterest}</td>
										<td class="center">${acct.descption}</td>
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
		<form  action="#" name="dataCollectForm" method="post"></form>
		<%-- /列表操作区 --%>
		<div id="page" class="pagination">
			<form action="<%=basePath%>acptAccountController.do?method=billManage"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
			</form>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>accountFlowSearchController.do?method=accountFlowSearch";
		dataCollectForm.submit();
	}
</script>
</body>
</html>