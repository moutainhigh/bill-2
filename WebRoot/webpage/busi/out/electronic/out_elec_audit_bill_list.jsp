<%-- 
 * 文件名称: audit_elec_bill_list.jsp.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
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
				<form action=""	method="post" name="userForm" id="userForm" class="form-search detail-form">
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="batchType">批次类型</label>
						<input type="text" class="input-medium" name="batchType" value="${fns:getDictLabel('K_BILL_TYPE',batch.batchType)}" readonly />
						<label for="custManagerName">批次种类</label>
						<input type="text" class="input-medium" name="batchClass" value="${fns:getDictLabel('K_BILL_CLASS',batch.batchClass)}" readonly />
						<label for="prodName">产品名称</label>
						<input type="text" class="input-medium" name="prodName" value="${prodName}" readonly />
				    </div>
				    <div class="row-fluid">
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" name="custName" value="${batch.custName}" readonly id="custName"/>
						<label for="custNo">客户号</label>
						<input type="text" class="input-medium" name="custNo" readonly value="${batch.custNo}" />
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" readonly value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
					</div>
					<div class="row-fluid">
						<label for="custManager">客户经理编号</label>
						<input type="text" class="input-medium" name="custManager" value="${batch.custManager}" readonly />
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" readonly />
						<label for="deptName">所属部门</label>
						<input type="text" class="input-medium" readonly name="deptName" value="${batch.deptName}" />
						<%--<label for="isTc" class="pull-left control-label">是否贴查同步</label>
						<div class="pull-left">
							<input type="text" class="input-medium" name="isTc" value="${fns:getDictLabel('K_IS_TC',batch.isTc)}" readonly />
						</div>--%>
				    </div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
					   <div class="span6" id="btn-left">
					   </div>
					   <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" href="javascript:audit();">审核</a>
							<a class="btn-mini pull-right" onclick="goHistory('${query.custNo}');">返回</a>
					   </div>
				  </div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
						 	<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">承兑人</th>
							<th class="center">出票人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="out" varStatus="vs">
								<tr>
									<td class="center"><input type="checkbox" name="ids" value="${out.outmxId}"/></td>
									<td class="center">${out.billNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',out.billType)}</td>
									<td class="center">${out.issueDt}</td>
									<td class="center">${out.dueDt}</td>
									<td class="center">${fns:formateMoney(out.billMoney)}</td>
									<td class="center">${out.acceptor}</td>
									<td class="center">${out.remitter}</td>
									<td class="center"><a href="javascript:goBillDetail('${out.rgctId}')">查看</a></td>
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
		<form action="#" name="dataCollectForm" method="post"></form>
		<%-- /列表操作区 --%>
		<div>
			<div id="page" class="pagination">
					<form action="<%=basePath%>outAuditController.do?method=elecAuditBillList"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="outId" value="${batch.outId}"/>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
    //返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
        dataCollectForm.action="<%=basePath%>outAuditController.do?method=elecAuditBatchList";
        dataCollectForm.submit();
	}
	function audit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要审核的记录!");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		var outId = '${batch.outId}';
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="审核";
		 diag.URL = "<%=basePath%>outAuditController.do?method=toElecAudit&ids="+ids+"&outId="+outId;
		 diag.Width = 430;
		 diag.Height =270;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
	        dataCollectForm.action = "<%=basePath%>outAuditController.do?method=elecAuditBatchList"
	        dataCollectForm.submit();
		 };
		 diag.show();
	}
	//详情页面
	function goBillDetail(rgctId){
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