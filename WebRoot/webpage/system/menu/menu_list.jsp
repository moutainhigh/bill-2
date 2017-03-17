<%-- 
 * 文件名称: menu_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:		菜单列表
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-7-17 上午08:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<link href="<%=basePath%>plugins/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>plugins/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
	</head>
<body >
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>menuController.do?method=list" method="post" name="listForm" id="listForm" class="form-search">
					<div class="row-fluid">
						<label class="">菜单名称</label>
						<input class="input-medium" type="text" name=menuName value="${menu.menuName}" placeholder="请输入菜单名称"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- 查询区  --%>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
				    		<a class="btn-mini"  onclick="add();">新增</a>
			   			</div>
			   			<div class="span6" id="btn-right"></div>
		 			</div>
		 		</form>
		 	</div>
		    <%-- 按钮操作区 --%>
		   	<%-- 列表操作区 --%>
		   	<div id="footer">
				<table id="treeTable" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" style="text-align:left;padding-left:20px;">菜单名称</th>
							<th class="center">菜单URL</th>
							<th class="center">菜单序号</th>
							<th class="center">是否展开</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList}" var="menu">
							<tr id="${menu.menuCode}" pId="${menu.menuLevel ne level?menu.parentMenuCode:'0'}">
								<td nowrap style="text-align:left;padding-left:20px;"><i class="icon-${not empty menu.iconDisplay?menu.iconDisplay:' hide'}"></i>${menu.menuName}</td>
								<td title="${menu.menuUrl}">${menu.menuUrl}</td>
								<td style="text-align:center;">
									<input type="hidden" name="ids" value="${menu.menuCode}"/>
								 	${menu.orderNo}
								</td>
								<td style="text-align:center;">${menu.openFlag eq '0'?'显示':'隐藏'}</td>
								<td nowrap style="text-align:center;">
									<a href="javascript:edit('${menu.menuCode}')">修改</a>
									<a href="javascript:add('${menu.menuCode}')">添加下级菜单</a> 
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	  	</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<script type="text/javascript" src="weblib/commonjs/common.js"></script>
<script type="text/javascript">
	if("ontouchend" in document) document.write("<script src='weblib/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 2}).show();
		});
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#listForm").submit();
	}
	//新增
	function add(menuCode){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>menuController.do?method=toAdd&parentMenuCode="+menuCode;
		 diag.Width = 690;
		 diag.Height = 300;
		 diag.CancelEvent = function(){ //关闭事件
			listForm.submit();
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(menu_code){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>menuController.do?method=toEdit&menuCode='+menu_code;
		 diag.Width = 690;
		 diag.Height = 300;
		 diag.CancelEvent = function(){ //关闭事件
			listForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(menuCodes){
		bootbox.confirm('是否要删除该菜单项吗？',function(result){
			if(result){
				$.ajax({
					type: "POST",
					url: '<%=basePath%>menuController.do?method=del',
				    data: {'menuCodes': menuCodes},
					dataType:'json',
					success: function(data){	
						if (data.success){  //处理成功
							listForm.submit();
						} else {
							bootbox.alert(data.msg); 
						}
					}
				});
			}
		})
	}
</script>
</body>
</html>