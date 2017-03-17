<%-- 
 * 文件名称: cust_Limit_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
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
<body style="font-family:'微软雅黑';">
	<div class="clearfix">
		<div id="page-content" class="page-content">
		<%-- 查询区  --%>
		<div id="header">
			<form action="<%=basePath%>custLimitController.do?method=cbList" method="post" name="Form" id="Form" class="form-search">
				<div class="row-fluid">
					<label for="cust_name" class="control-label">客户名称</label>
					<input class="input-medium" type="text" name="cust_name" id="cust_name" value="${mapField.cust_name}" placeholder="请输入客户名称"/>
					<label for="cust_type" class="control-label">客户类型</label>
					<t:dictSelect className="select-medium" name="cust_type" other="" dictGroup="K_CUSTTYPE" defaultVal="${mapField.cust_type}" haveHead="true"  valid="required">
					</t:dictSelect>
					<label for="cust_no" class="control-label">客户号</label>
					<input class="input-medium" type="text" name="cust_no" id="cust_no" value="${mapField.cust_no}" placeholder="请输入客户号"/>
					<a class="btn-mini" id="search" onclick="searchd();">查询</a>
				</div>
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
			   		</div>
			   		<div class="span6" id="btn-right">
			   		</div>
		  		</div>
			</form>
		</div>
	    <%-- /按钮操作区 --%>
		<%-- 列表操作区 --%>
		<div id="footer">
		<table id="table_report" class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1400px;width:1400px;">
			<thead>
			<tr>
				<th class="center" onclick="selectAll('zcheckbox', 'ids')">
					<input type="checkbox" id="zcheckbox" />
				</th>
				<th class="center"  style="width:50px;">序号</th>
				<th class="center">客户名称</th>
				<th class="center">客户类型</th>
				<th class="center">客户号</th>
				<th class="center">额度品种</th>
				<th class="center">总金额</th>
				<th class="center">可用金额</th>
				<th class="center">已用金额</th>
				<th class="center">有效日期</th>
				<th class="center">是否可循环利用</th>
				<th class="center">状态</th>
			</tr>
			</thead>
			<c:choose>
				<c:when test="${not empty rsList}">
					<c:forEach items="${rsList}" var="cb" varStatus="vs">
					<tr>
						<td class="center">
							<input type='checkbox' name='ids' value="${cb.custNo}" />
						</td>
						<td class="center">${vs.index+1}</td>
						<td class="center">${cb.custName}</td>
						<td class="center">${fns:getDictLabel('K_KHYUE',cb.custType)}</td>
						<td class="center">${cb.custNo}</td>
						<td class="center">${fns:getDictLabel('K_EDPZ',cb.balanceClass)}</td>
						<td class="center">${fns:formateMoney(cb.totalBalance)}</td>
						<td class="center">${fns:formateMoney(cb.ableBalance)}</td>
						<td class="center">${fns:formateMoney(cb.usedBalance)}</td>
						<td class="center">${cb.effectiveDate}</td>
						<td class="center">${fns:getDictLabel('K_YORN',cb.isCircle)}</td>
						<td class="center">${fns:getDictLabel('K_YORN',cb.status)}</td>
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
		<div>
	<%-- /列表操作区 --%>
	<div id="page" class="pagination">
			<form action="<%=basePath%>custLimitController.do?method=cbList" name="pageForm" method="post" >
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="cust_name" value="${mapField.cust_name}" />
				<input type="hidden" name="cust_type" value="${mapField.cust_type}" />
				<input type="hidden" name="cust_no" value="${mapField.cust_no}" />
			</form>
		</div>
  	</div>
</div>	
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#Form").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>custLimitController.do?method=toAdd";
		 diag.Width = 680;
		 diag.Height = 280;
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
	   		bootbox.alert('请选择一条记录编辑');
	   		return;
	   	 }
	   	 var custNo = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="编辑";
		 diag.URL = '<%=basePath%>custLimitController.do?method=toEdit&custNo='+custNo;
		 diag.Width = 680;
		 diag.Height = 280;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
    function page(){
		$("#custInfoForm").submit();
		return false;
    }
</script>
</body>
</html>