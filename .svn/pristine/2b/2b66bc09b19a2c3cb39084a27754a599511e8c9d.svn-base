<%-- 
 * 文件名称: sysparam_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-7-7 上午06:28:22
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
	<div class="clearfix" id="allshow">
		<div id="page-content" class="page-content">
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>sysParamController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="form-search">
						<label for="param_id">参数标识</label>
						<input class="input-medium" type="text" name="param_id" value="${mapField.param_id}" placeholder="请输入参数标识"/>
						<label for="param_id">归属系统</label>
						<input class="input-medium" type="text" name="belong_type" value="${mapField.belong_type}" placeholder="请输归属系统"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
				</div>
				<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
			</form>
		</div>
		<%-- /查询区  --%>
		<%-- 按钮操作区 --%>
		<div id="center">
			<form  id="btnForm">
				<div class="form-search">
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
						<th class="center" onclick="selectAll('zcheckbox', 'ids')">
							<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
						</th>
						<th class="center">序号</th>
						<th class='center sort-column param_id'>参数标识</th>
						<th class='center sort-column param_name'>参数标识名称</th>
						<th class='center sort-column param_value'>参数标识值</th>
						<th class='center sort-column param_value'>归属系统</th>
						<th class='center sort-column value_name'>参数标识值说明</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty resultList}">
						<c:forEach items="${resultList}" var="sysparam" varStatus="vs">
							<tr>
								<td class="center">
									<input type='checkbox' name='ids' value="${sysparam.paramId}" /><span class="lbl"></span>
								</td>
								<td class="center">${fns:getDictLabel('sex',vs.index+1)}</td>
								<td class="center">${sysparam.paramId}</td>
								<td class="center">${sysparam.paramName}</td>
								<td class="center">${sysparam.paramValue}</td>
								<td class="center">${sysparam.belongType}</td>
								<td class="center">${sysparam.valueName}</td>
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
			<form action="<%=basePath%>sysParamController.do?method=list"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="param_id" value="${param_id}"/>
				<input type="hidden" name="belong_type" value="${belong_type}"/>
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
	$("#userForm").submit();
}
//新增
function add(){
	 var diag = new top.Dialog();
	 diag.Drag = true;
	 diag.Title ="新增";
	 diag.URL = "<%=basePath%>sysParamController.do?method=toAdd";
	 diag.Width = 500;
	 diag.Height = 390;
	 diag.CancelEvent = function(){ //关闭事件
		 pageForm.submit();
		 diag.close();
	 };
	 diag.show(); 
}
//修改
function edit(){
	 var checkNum = getCheckNum("ids");
   	 if (checkNum !=1){
   		bootbox.alert("请选择一条记录编辑");
   		return;
   	 }
   	 var param_id = getCheckStr("ids");
   	 var diag = new top.Dialog();
	 diag.Drag = true;
	 diag.Title ="修改";
	 diag.URL = '<%=basePath%>sysParamController.do?method=toEdit&param_id='+param_id;
	 diag.Width = 500;
	 diag.Height = 390;
	 diag.CancelEvent = function(){ //关闭事件
		pageForm.submit();
		diag.close();
	 };
	 diag.show();
}
//删除
function del(){
	var checkNum = getCheckNum("ids");
   	if (checkNum <= 0){
   		bootbox.alert("请选择删除记录");
   		return;
   	}
   	var param_ids = getCheckStr("ids");	
   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
		if(result) {
			$.ajax({
				type: "POST",
				url: '<%=basePath%>sysParamController.do?method=del',
		    	data: {'param_ids': param_ids},
				dataType:'json',
				cache: false,
				success: function(data){	
					if (data.success){  //处理成功
						userForm.submit();
					} else {
						bootbox.alert("删除失败!"); 
					}
				}
			});
	  	}
   	});
}
function page(){
	$("#userForm").submit();
	return false;
}
</script>
</body>
</html>