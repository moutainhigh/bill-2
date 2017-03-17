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
	<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.all.js"></script>
	<style type="text/css">
		.spmanager{position: absolute;margin-left: 0px;margin-top: 0px;width: 290px; border:1px #cfe2f0 solid; border-top:none;}
	</style>
</head>
<body style="font-family:'微软雅黑';background:#f4f8fb;">
<div class="clearfix">
	<div id="page-content" class="page-content" style="font-family:'微软雅黑';background:#f4f8fb;">
		<div id="up">
			<%-- 查询区  --%>
			<div class="row-fluid">
				<label class="no-padding-right" for="curStation" style="text-align:right;margin-top:3px;"><span class="star">*</span>当前岗位</label>
				<t:select id="station" className="select-medium" other="" name="station" list="stationList" listKey="asName" listValue="id" defaultVal="${auditStation.id}" onchange="changeRole();"></t:select>
				<a class="btn-mini" id="returnBtn" onclick="top.Dialog.close();">返回</a>
		</div>
	</div>
		<div id="footerd">
			<div style="width:45%;height:inherit;float:left;">
				<table class="table table-striped table-bordered table-hover" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('allcheck', 'ids')">
								<input type="checkbox" id="allcheck" /><span class="lbl"></span>
							</th>
							<th class="center">角色名称</th>
							<th class="center">备注</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty allRoleList}">
								<c:forEach items="${allRoleList}" var="role" varStatus="vs">
								<tr>
									<td class="center"><input type="checkbox" name="ids" value="${role.roleCode}"/></td>
									<td class="center">${role.roleName}</td>
									<td class="center">${role.remark}</td>
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
			<div style="height:inherit;float:left;padding:100px 18px;line-height:20px;">
				<div id="fixed">
		    		<a class="btn-mini" onclick="assignRoles();">分配角色</a>
		    	</div>
		    	<div class="space" style="height:20px;"></div>
		    	<div id="fixed">
		    		<a class="btn-mini" onclick="cancelAssign();">取消分配</a>
		    	</div>
			</div>
			<div style="width:45%;height:inherit;float:left;">
				<table class="table table-striped table-bordered table-hover" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('allcheck2', 'roleIds')">
								<label><input type="checkbox" id="allcheck2" /><span class="lbl"></span></label>
							</th>
							<th class="center">角色名称</th>
							<th class="center">备注</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty roleDtoList}">
								<c:forEach items="${roleDtoList}" var="roleDto" varStatus="vs">
								<tr>
									<td class="center"><input type="checkbox" name="roleIds" value="${roleDto.id}"/></td>
									<td class="center">${roleDto.roleName}</td>
									<td class="center">${roleDto.remark}</td>
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
		</div>
		<form  action="#" name="dataCollectForm" method="post"></form>
		<input type="hidden" id="arId" name="arId" value="${arId}">
</div>
</div>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	$(function (){
		var footerHeight;
		footerHeight=$("#page-content").innerHeight()-$("#up").innerHeight()-$("#center").innerHeight()-$("#page").innerHeight();
		$("#footer").height(footerHeight);
	});	
	function assignRoles(){
		var checkNum = getCheckNum("ids");
		if (checkNum < 1){
		bootbox.alert("请选择要分配的角色");
		return;
		}
	   	var ids = getCheckStr("ids");
	   	var asId = $("#station").val();
	   	dynamicHiddenElement('dataCollectForm','roleIds',ids);
	   	dynamicHiddenElement('dataCollectForm','asId',asId);
		dataCollectForm.action = "<%=basePath%>auditRouteController.do?method=assignRoles";
		modal("#layer_loading,#image");
		dataCollectForm.submit();
	}
	function cancelAssign(){
		var checkNum = getCheckNum("roleIds");
		if (checkNum < 1){
		bootbox.alert("请选择要取消分配的角色");
		return;
		}
	   	var ids = getCheckStr("roleIds");
	   	var asId = $("#station").val();
	   	dynamicHiddenElement('dataCollectForm','roleIds',ids);
	   	dynamicHiddenElement('dataCollectForm','asId',asId);
		dataCollectForm.action = "<%=basePath%>auditRouteController.do?method=cancelAssign";
		modal("#layer_loading,#image");
		dataCollectForm.submit();
	}
	function changeRole(){
		var asId = $("#station").val();
	   	dynamicHiddenElement('dataCollectForm','asId',asId);
		dynamicHiddenElement('dataCollectForm','anId',"${auditStation.anId}");
		dynamicHiddenElement('dataCollectForm','arId',"${auditStation.arId}");
		dataCollectForm.action = "<%=basePath%>auditRouteController.do?method=toAssignStation";
		dataCollectForm.submit(); 
	}
</script>
</body>
</html>