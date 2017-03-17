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
				<form action="<%=basePath%>auditRouteController.do?method=searchAuditRoute"  method="post" name="searchForm" id="searchForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="arName">审批路线名称</label>
						<input type="text" id="arName" name="arName" class="input-medium" valid="required"  placeholder="请选择审批路线名称"/>
						<label class="no-padding-right" for="prodNo">指定使用产品</label>
						<sys:treeselect className="input-medium" id="prodNo" name="prodNo" placeholder="请选择指定使用产品" value="${bean.prodNo}" module="0" labelName="bean.prodNo" labelValue="${routeproName}"
						    title="产品" url="tagController.do?method=treeData" extId="${menu.menuCode}"  subnode="prod_no"  pnode = "parent_prod_no" nodename="prod_name" sourcename="tproduct"/>
						<a class="btn-mini"  id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
						    <a class="btn-mini" onclick="addRoute();">新增路线</a>
							<a class="btn-mini" href="javascript:editRoute();">编辑路线</a>
							<a class="btn-mini" href="javascript:delRoute();">删除路线</a>
							<a class="btn-mini" href="javascript:setRoute();">设置路线</a>
					   </div>
					   <div class="span6" id="btn-right"></div>
				  	</div>
				 </form>
			</div>
	    	<%-- /按钮操作区 --%>
    		<div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" >
								<input type="checkbox" id="zcheckbox" onclick="selectAll('zcheckbox', 'ids')"/>
							</th>
							<th class="center">审批路线名称</th>
							<th class="center">审批岗位执行模式</th>
							<th class="center">指定使用产品</th>
							<th class="center">指定使用机构</th>
							<th class="center">生效状态</th>
							<th class="center">审批起始机构级别</th>
							<th class="center">创建机构</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
								<c:when test="${not empty routeList}">
									<c:forEach items="${routeList}" var="route" varStatus="vs">
										<tr>
											<td class="center">
												<input type="checkbox" name='ids' value="${route.id}" />
											</td>
											<td class="center">${route.arName}</td>
											<td class="center">${fns:getDictLabel('K_EXEC_MODE',route.anExecMode)}</td>
											<td class="center">${fns:getTableLabel('tproduct','prod_no','prod_name',route.prodNo)}</td>
											<td class="center">${route.branch.branchName}</td>
											<td class="center">${fns:getDictLabel('K_YORN',route.effectFlag)}</td>
											<td class="center">${fns:getDictLabel('K_BRCH_LEVEL',route.auditStartBrchLvl)}</td>
											<td class="center">${fns:getTableLabel('tbranch','branch_no','branch_name',route.createBrchNo)}</td>
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
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>auditRouteController.do?method=searchAuditRoute"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="arName" value="${bean.arName }">
					<input type="hidden" name="prodNo" value="${bean.prodNo }">
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function searchd(){
		modal("#layer_loading,#image");
		searchForm.submit();
	}
	function addRoute(){
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="新增审批路线";
	 	diag.URL = "<%=basePath%>auditRouteController.do?method=toAddRoute";
	 	diag.Width = 500;
	 	diag.Height = 350;
	 	diag.CancelEvent = function(){ //关闭事件
		 	pageForm.submit();
		   	diag.close();
	 	};
	 	diag.show(); 
	}
	function editRoute(){
	     var checkNum = getCheckNum("ids");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	var id = getCheckStr("ids");
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="修改审批路线";
	 	diag.URL = "<%=basePath%>auditRouteController.do?method=toEditRoute&id="+id;
	 	diag.Width = 500;
	 	diag.Height = 350;
	 	diag.CancelEvent = function(){ //关闭事件
		 	pageForm.submit();
		   	diag.close();
	 	};
	 	diag.show(); 
	}
	function delRoute(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	}
	   	var ids = getCheckStr("ids");
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>auditRouteController.do?method=delRoute',
			    	data: {'ids': ids},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							searchForm.submit();
						} else {
							top.hangge();
							bootbox.alert("删除失败!"); 
							}
					}
				});
		  	}
	   	});
	}	
	function setRoute(){
		var checkNum = getCheckNum("ids");
	  	if (checkNum !=1){
	  		bootbox.alert("请选择一条记录");
	  		return;
	  	}
	  	var arId = getCheckStr("ids");
	  	modal("#layer_loading,#image");
	  	dynamicHiddenElement('dataCollectForm','arId',arId);
		dataCollectForm.action="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute";
		dataCollectForm.submit();
	}
</script>
</body>
</html>