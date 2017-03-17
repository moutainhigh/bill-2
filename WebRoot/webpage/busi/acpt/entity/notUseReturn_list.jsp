<%-- 
 * 文件名称: notUseReturn_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 电票接收
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-9-10 上午11:27:22
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
			<form action="<%=basePath%>acptAccountController.do?method=notUseReturnList" method="post" name="userForm" id="userForm" class="form-search">	
			</form>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="javascript:notUseReturnButton();">退回</a>
						</div>
						<div class="span6" id="btn-right"></div>
		  			</div>
				</form>
			</div>
			<%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center">
								<input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/>
							</th>
							<th class='center sort-column bill_no' nowrap="nowrap">票号</th>
							<th class='center sort-column bill_class'  nowrap="nowrap">票据种类</th>
							<th class='center sort-column bill_type'nowrap="nowrap">票据类型</th>
							<th class='center sort-column issue_dt'nowrap="nowrap">出票日</th>
							<th class='center sort-column due_dt'nowrap="nowrap">票面到期日</th>
							<th class='center sort-column bill_money'nowrap="nowrap">票面金额</th>			
							<th class='center sort-column remitter'nowrap="nowrap">出票人名称</th>
							<th class='center sort-column payee'nowrap="nowrap">收款人</th>
						    <th class='center sort-column status'nowrap="nowrap">状态</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="acpt" varStatus="vs">
								<tr>
									<td class="center">
										<input type="checkbox" name="ids" id="ids" value="${acpt.acptmxId}"/>
									</td>
									<td class="center"nowrap="nowrap">${acpt.billNo}</td>
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_CLASS',acpt.billClass)}</td>
									<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_TYPE',acpt.billType)}</td>
									<td class="center"nowrap="nowrap">${acpt.issueDt}</td>
									<td class="center"nowrap="nowrap">${acpt.dueDt}</td>
									<td class="center"nowrap="nowrap">${fns:formateMoney(acpt.billMoney)}</td>					
									<td class="center"nowrap="nowrap">${acpt.remitter}</td>
									<td class="center"nowrap="nowrap">${acpt.payee}</td>
									<td class="center"nowrap="nowrap">${acpt.status}</td>
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
		   	<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>acptAccountController.do?method=notUseReturnList"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					</form>
				</div>	
		  	</div>
	  	</div>
	</div>	
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
   //退回
        function  notUseReturnButton(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要退回的记录");
	   		return;
	   	}
	   	var ids=getCheckStr("ids");
	   	bootbox.confirm("确定要退回选中的记录吗？", function(result) {
	   		if(result) {
		   		dynamicHiddenElement('dataCollectForm','ids',ids);
		   		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		   		dataCollectForm.action = "<%=basePath%>acptAccountController.do?method=notUseReturn";
		   		dataCollectForm.submit();
	   		}
	   	});
	}
</script>
</body>
</html>