<%-- 
 * 文件名称: bill_manage.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wzc
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
				<form action=""	method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="remitter">出票人客户名称</label>
						<input type="text" class="input-medium" name="remitter" value="${batch.remitter}" readonly />
						<label for="remitterCustNo">出票人客户号</label>
						<input type="text" class="input-medium" name="remitterCustNo" value="${batch.remitterCustNo}" readonly/>
						<label for="remitterAcct">出票人客户账号</label>
						<input type="text" class="input-medium" name="remitterAcct" value="${batch.remitterAcct}" readonly/>
					</div>
					<div class="row-fluid">
						<label for="protocalNo">银承协议编号</label>
						<input type="text" class="input-medium" name="protocalNo" readonly value="${batch.protocalNo}" readonly/>
						<label for="issueDt">出票日</label>
						<input type="text" class="input-medium"  name="issueDt" readonly value="${batch.issueDt}" readonly/>
						<label for="dueDt">到期日</label>
						<input type="text" class="input-medium"  name="dueDt"  value="${batch.dueDt}" readonly/>
					</div>
					<div class="row-fluid" >
						<label for="totalAmt">汇总金额</label>
						<input type="text" class="input-medium" name="totalAmt" readonly value="${fns:formateMoney(batch.totalAmt)}" readonly/>
						<label for="totalCount">总笔数</label>
						<input type="text" class="input-medium" name="totalCount" readonly value="${batch.totalCount}" readonly/>
						<label for="billType">票据种类</label>
						<input type="text" class="input-medium" name="billType" readonly value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}" readonly/>
					</div>		
				</form>
			</div>
			<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>acptAccountController.do?method=elecReviewList"  method="post" id="btnForm" name="btnForm" style="padding:0px;" class="form-search">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
					   </div>
					   <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="doAccount();">提交</a>
					        <a class="btn-mini pull-right" onclick="goHistory();">返回</a>				
					   </div>
				  </div>
			   </form>
		   </div>
		   <div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center">
							<input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/>
							</th>	
							<th class="center">票号</th>
							<th class="center">票据状态</th>
							<th class="center">收款人名称</th>
							<th class="center">出票人客户账号</th>
							<th class="center">付款日</th>
							<th class="center">票据金额</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="acpt" varStatus="vs">
									<tr>
										<td class="center">
											<input type="checkbox" onclick="radioStyle(this,'ids')" name="ids" value="${batchList.acptId}" />
										</td>
										<td class="center">${acpt.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_STATUS',acpt.billStatus)}</td>
										<td class="center">${acpt.payee}</td>
										<td class="center">${acpt.remitterAcct}</td>
										<td class="center">${acpt.paymentDt}</td>
										<td class="center">${fns:formateMoney(acpt.billMoney)}</td>
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
				<input type="hidden" name="acptId" value="${batch.acptId}"/>
			</form>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>acptAccountController.do?method=batchManage";
		dataCollectForm.submit();
	}
	function doAccount(){
		bootbox.confirm("确定要提交选中的记录吗？", function(result) {
	   		if(result) {
		dynamicHiddenElement('dataCollectForm','acptId',"${batch.acptId}");
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>acptAccountController.do?method=doElecAccount";
		dataCollectForm.submit();
		   		}
	   	});
	}
</script>
</body>
</html>