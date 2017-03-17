<%-- 
 * 文件名称: operation_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-21 上午09:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>operationController.do?method=list" method="post" name="operationForm" id="operationForm" class="form-search">
					<div class="row-fluid">
						<label for="operationName">功能名称</label>
						<input class="input-medium" type="text" name="operationName" value="${mapField.operationName}" placeholder="请输入功能名称"/>
						<label for="operationCode">功能编号</label>
						<input class="input-medium" type="text" name="operationCode" value="${mapField.operationCode}" placeholder="请输入功能编号"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
					<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
				</form>
			</div>
			<%-- /查询区  --%>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
					    	<a class="btn-mini"  onclick="add();">新增</a>
							<a class="btn-mini" onclick="edit();">修改</a>
							<a class="btn-mini"  onclick="del();">删除</a>
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
							<th class="center" onclick="selectAll('zcheckbox', 'operationCodes')">
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center sort-column operation_code'>功能编号</th>
							<th class='center sort-column operation_name'>功能名称</th>
							<th class='center sort-column operation_type'>操作类型</th>
							<th class='center sort-column menu_code'>菜单编号</th>
							<th class='center sort-column status'>是否生效</th>
							<th class='center sort-column auth_flag'>是否授权</th>
							<th class='center sort-column logon_flag'>是否需要登录校验</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="operation" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='operationCodes' value="${operation.operationCode}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${operation.operationCode}</td>
									<td class="center">${operation.operationName}</td>
									<td class="center">${operation.operationType}</td>
									<td class="center">${operation.menuCode}</td>
									<td class="center">${operation.status}</td>
									<td class="center">${operation.authFlag}</td>
									<td class="center">${operation.logonFlag}</td>
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
	   	<%-- /列表操作区 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>operationController.do?method=list" method="post" name="pageForm">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="operationName" value="${mapField.operationName}" />
					<input type="hidden" name="operationCode" value="${mapField.operationCode}" />
				</form>
			</div>
	  	</div>
	</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#operationForm").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>operationController.do?method=toAdd";
		 diag.Width = 450;
		 diag.Height = 210;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(){
		 var checkNum = getCheckNum("operationCodes");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var operationCode = getCheckStr("operationCodes");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>operationController.do?method=toEdit&operationCode='+operationCode;
		 diag.Width = 450;
		 diag.Height = 210;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(){
		var checkNum = getCheckNum("operationCodes");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	}
	   	var operationCodes = getCheckStr("operationCodes");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>operationController.do?method=del',
			    	data: {'operationCodes': operationCodes},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							operationForm.submit();
						} else {
							bootbox.alert("删除失败!"); 
						}
					}
				});
		 	 }
	   	});
	}
   	function page(){
		$("#operationForm").submit();
		return false;
	}
</script>
</body>
</html>