<%-- 
 * 文件名称: busibranch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/poseui.tld"%>
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
				<form action="<%=basePath%>/busiBranchController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
		    			<label for="brch_no">机构编号</label>
						<input class="input-medium" type="text" name="brch_no" value="${mapField.brch_no}" placeholder="请输入机构编号"/>
						<label for="name" class="control-label">机构名称</label>
						<input class="input-medium" type="text" name="name" value="${mapField.name}" placeholder="请输入机构名称"/>
						<label for="status" class="control-label text-right">机构状态</label>
						<t:dictSelect className="select-medium" name="status" other="" dictGroup="K_QYBZ" defaultVal="${mapField.status}" haveHead="true"  title="状态">
						</t:dictSelect>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
					<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>	
				</form>
			</div>
			<%-- /查询区  --%>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm" >
					<div class="row-fluid">
						<div class="span6" id="btn-left">
				    		<a class="btn-mini"  onclick="add();">
				    		<i class="icon-plus"></i>新增</a>
							<a class="btn-mini" onclick="edit();">
							<i class="icon-edit"></i>修改</a>
							<a class="btn-mini"  onclick="del();">
							<i class="icon-trash"></i>删除</a>
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
							<th class='center sort-column brch_no'>机构编号</th>
							<th class='center sort-column name'>机构名称</th>
							<th class='center sort-column status'>机构状态</th>
						</tr>
					</thead>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="busibranch" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='ids' value="${busibranch.brchNo}" /><span class="lbl"></span>
										</td>
										<td class="center">${vs.index+1}</td>
										<td class="center">${busibranch.brchNo}</td>
										<td class="center">${busibranch.name}</td>
										<c:if test="${busibranch.status=='1'}">
											<td class="center">启用</td>
										</c:if>
										<c:if test="${busibranch.status=='0'}">
											<td class="center">未启用</td>
										</c:if>
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
				<form action="<%=basePath%>/busiBranchController.do?method=list" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				</form>
			</div>
	  	</div>
	</div>
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("brch_no").readOnly = true;
	}
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
		 diag.URL = "<%=basePath%>busiBranchController.do?method=toAdd";
		 diag.Width = 450;
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
	   		alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var brch_no = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>busiBranchController.do?method=toEdit&brch_no='+brch_no;
		 diag.Width = 450;
		 diag.Height = 280;
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
	   		alert("请选择删除记录");
	   		return;
	   	}
	   	var brch_nos = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>busiBranchController.do?method=del',
			    	data: {'brch_nos': brch_nos},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
						} else {
							top.hangge();
							alert("删除失败!"); 
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