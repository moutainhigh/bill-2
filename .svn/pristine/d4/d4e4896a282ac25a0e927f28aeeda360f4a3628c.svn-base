<%-- 
 * 文件名称: reportOfLossAndSp.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 挂失支付
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
			<div id="header">
				<form action="<%=basePath%>acptAccountController.do?method=reportOfLossAndSp" method="post" name="userForm" id="userForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="billNo">票号</label>
						<input class="input-medium" type="text" name="billNo" value="${searchBean.billNo}" placeholder="请输入票号" maxLength='16'/>
						<label for="remitter_acct">出票人账号</label>
						<input class="input-medium" type="text" name="remitterAcct" value="${searchBean.remitterAcct}" placeholder="出票人账号"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
					<%-- /查询区  --%>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
							<div class="span6" id="btn-left">
								<a class="btn-mini" onclick="javascript:reportOfLoss();">挂失</a>
								<a class="btn-mini" onclick="javascript:remove1();">解挂</a>
							</div>
							<div class="span6" id="btn-right">
							</div>
					  </div>
				</form>
			</div>
			<%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center"><input type="checkbox" name="ids" value="" disabled="disabled" /></th>
							<th class='center sort-column bill_no' nowrap="nowrap">票号</th>
							<th class='center sort-column sspd_status' nowrap="nowrap">挂失状态</th>
							<th class='center sort-column status' nowrap="nowrap">状态</th>
							<th class='center sort-column bill_status' nowrap="nowrap">票据状态</th>
							<th class='center sort-column bill_type'nowrap="nowrap">票据类型</th>
							<th class='center sort-column bill_money'nowrap="nowrap">票面金额</th>
							<th class='center sort-column payee'nowrap="nowrap">收款人</th>			
							<th class='center sort-column remitter'nowrap="nowrap">出票人名称</th>
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
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_HSCZLX',acpt.sspdStatus)}</td>
									<td class="center"nowrap="nowrap">${acpt.status}</td>
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_STATUS',acpt.billStatus)}</td>
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
					<form action="<%=basePath%>acptAccountController.do?method=reportOfLossAndSp"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					</form>
				</div>	
			</div>
		 </div>
	</div>	
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	//检索
	function searchd(){
		$("#userForm").submit();
	}
     //挂失
   	function reportOfLoss(){
   		var checkNum = getCheckNum("ids");
	   	if (checkNum != 1){
	   		bootbox.alert("一次只能对一个记录进行挂失操作");
	   		return;
	   	}
	     var acptmx_ids = getCheckStr("ids");
   	   	 var diag = new top.Dialog();
   		 diag.Drag = true;
   		 diag.Title ="记录挂失";
   		 diag.URL = '<%=basePath%>acptAccountController.do?method=reportOfLoss&acptmx_ids='+acptmx_ids;
   		 diag.Width = 750;
   		 diag.Height = 210;
   		 diag.CancelEvent = function(){ //关闭事件
   			 diag.close();
   			 pageForm.action = "<%=basePath%>acptAccountController.do?method=reportOfLossAndSp";
			 pageForm.submit();
   		 };
   		 diag.show();
   	}	
  	 //解挂按钮
    function remove1(){	
        var checkNum = getCheckNum("ids");
	   	if (checkNum != 1){
	   		bootbox.alert("一次只能对一个记录进行解挂操作");
	   		return;
	   	}
     	 var acptmx_ids = getCheckStr("ids");
  	   	 var diag = new top.Dialog();
  		 diag.Drag = true;
  		 diag.Title ="记录解挂";
  		 diag.URL = '<%=basePath%>acptAccountController.do?method=remove1&acptmx_ids='+acptmx_ids;
  		 diag.Width = 750;
  		 diag.Height = 210;
  		 diag.CancelEvent = function(){ //关闭事件
  			 diag.close();
  			 pageForm.action = "<%=basePath%>acptAccountController.do?method=reportOfLossAndSp";
	     pageForm.submit();
  		 };
  		 diag.show();
  	}
</script>
</body>
</html>