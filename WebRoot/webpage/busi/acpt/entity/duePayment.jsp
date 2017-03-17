<%-- 
 * 文件名称: duePayment.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 到期付款
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-9-13 上午06:28:22
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
			<form action="<%=basePath%>acptAccountController.do?method=duePayment" method="post" name="userForm" id="userForm" class="form-search">
				<div id="center">
						<div class="row-fluid">
							<div class="span6" id="btn-left">
								<a class="btn-mini" onclick="javascript:paymentRegister();">付款登记</a>
								<a class="btn-mini" onclick="javascript:noPayment();">拒付登记</a>
							</div>
							<div class="span6" id="btn-right"></div>
						</div>
					</div>
				</form>
			<%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" nowrap="nowrap" ><input type="checkbox" name="ids" value="" disabled="disabled" /></th>	
							<th class='center sort-column bill_no' nowrap="nowrap">票号</th>
							<th class='center sort-column bill_status' nowrap="nowrap">票据状态</th>
							<th class='center sort-column colltn_status' nowrap="nowrap">委托收款状态</th>
							<th class='center sort-column sspd_status' nowrap="nowrap">挂失状态</th>
							<th class='center sort-column protocal_no' nowrap="nowrap">银承协议编号</th>
			                <th class='center sort-column fac_no' nowrap="nowrap">授信额度编号</th>
							<th class='center sort-column bill_type'nowrap="nowrap">票据类型</th>
							<th class='center sort-column bill_money'nowrap="nowrap">票面金额</th>
							<th class='center sort-column payee'nowrap="nowrap">收款人全称</th>			
							<th class='center sort-column remitter'nowrap="nowrap">出票人全称</th>
							<th class='center sort-column remitter_acct'nowrap="nowrap">出票人账号</th>
							<th class='center sort-column issue_dt'nowrap="nowrap">出票日</th>
						    <th class='center sort-column due_dt'nowrap="nowrap">到期日</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="acpt" varStatus="vs">
								<tr>
									<td class="center">
									     <input type='checkbox' onclick="radioStyle(this,'ids')" name='ids' value="${acpt.acptmxId}" /><span class="lbl"></span>
									</td>
									<td class="center"nowrap="nowrap">${acpt.billNo}</td>
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_STATUS',acpt.billStatus)}</td>
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_WTSKZT',acpt.colltnStatus)}</td>
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_HSCZLX',acpt.sspdStatus)}</td>
									<td class="center"nowrap="nowrap">${acpt.protocalNo}</td>
			                        <td class="center"nowrap="nowrap">${acpt.facNo}</td>
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_TYPE',acpt.billType)}</td>
									<td class="center"nowrap="nowrap">${fns:formateMoney(acpt.billMoney)}</td>	
									<td class="center"nowrap="nowrap">${acpt.payee}</td>
									<td class="center"nowrap="nowrap">${acpt.remitter}</td>
									<td class="center"nowrap="nowrap">${acpt.remitterAcct}</td>
									<td class="center"nowrap="nowrap">${acpt.issueDt}</td>
									<td class="center"nowrap="nowrap">${acpt.dueDt}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="100" class="center">没有相关数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- /列表操作区 --%>
			<div>
			 	<%-- /列表操作区 --%>
				<div id="page" class="pagination">
					<form action="<%=basePath%>acptAccountController.do?method=duePayment"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					</form>
				</div>
			</div>
		  </div>
		</div>	
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
	//检索
	function search(){
		$("#userForm").submit();
	}
     //承兑付款登记
   	function paymentRegister(){
   		var checkNum = getCheckNum("ids");
	   	if (checkNum != 1){
	   		bootbox.alert("请先选择要付款的记录");
	   		return;
	   	}
	     var acptmx_ids = getCheckStr("ids");
   	   	 var diag = new top.Dialog();
   		 diag.Drag = true;
   		 diag.Title ="承兑付款登记";
   		 diag.URL = '<%=basePath%>acptAccountController.do?method=paymentRegister&acptmx_ids='+acptmx_ids;
   		 diag.Width = 500;
   		 diag.Height = 450;
   		 diag.CancelEvent = function(){ //关闭事件
   			diag.close();
   			 pageForm.action = "<%=basePath%>acptAccountController.do?method=duePayment";
			 pageForm.submit();
   		 };
   		 diag.show();
   	}	
   	 //承兑拒付登记
        function noPayment(){	
        var checkNum = getCheckNum("ids");
	   	if (checkNum != 1){
	   		bootbox.alert("请先选择要拒付的记录");
	   		return;
	   	}
	     var acptmx_ids = getCheckStr("ids");
   	   	 var diag = new top.Dialog();
   		 diag.Drag = true;
   		 diag.Title ="承兑拒付登记";
   		 diag.URL = '<%=basePath%>acptAccountController.do?method=noPayment&acptmx_ids='+acptmx_ids;
    	 diag.Width = 500;
         diag.Height = 450;
   		 diag.CancelEvent = function(){ //关闭事件
   			 diag.close();
   			 pageForm.action = "<%=basePath%>acptAccountController.do?method=duePayment";
		     pageForm.submit();
   		 };
   		 diag.show();
   	}
</script>
</body>
</html>
