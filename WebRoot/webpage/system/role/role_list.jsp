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
	<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" type="text/css">
</head>

<body>
<div class="clearfix">
	<div id="page-content" class="page-content">
		<%-- 查询区  --%>
		<div id="header">
			<form action="<%=basePath%>roleController.do?method=list" method="post" name="roleForm" id="roleForm" class="form-search">
				<div class="row-fluid">
					<label class="text-right text">角色编码</label>
					<input class="input-medium" type="text" name="roleCode" value="${role.roleCode}" placeholder="请输入角色编码"/>
					<label class="text-right text">角色名称</label>
					<input class="input-medium" type="text" name="roleName" value="${role.roleName}" placeholder="请输入角色名称"/>
					<a class="btn-mini" id="search" onclick="searchd();">查询</a>
				</div>
			</form>
		</div>
		<%-- 查询区  --%>
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
		<div id="footer" style="width:60%;float:left;">
			<table class="table table-striped table-bordered table-hover" id="lef-table">
				<thead>
					<tr>
						<th class="center" onclick="selectAll('zcheckbox', 'ids')">
							<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
						</th>
						<th class="center">序号</th>
						<th class="center">角色编码</th>
						<th class="center">角色名称</th>
						<th class="center">角色状态</th>
						<th class="center">操作</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty resultList}">
						<c:forEach items="${resultList}" var="roles" varStatus="vs">
						<tr>
							<td class="center">
								<input type='checkbox' name='ids' value="${roles.roleCode}" /><span class="lbl"></span>
							</td>
							<td class="center">${vs.index+1}</td>
							<td class="center">${roles.roleCode }</td>
							<td class="center">${roles.roleName }</td>
							<td class="center">${fns:getDictLabel('K_ROLE_STATUS',roles.roleStatus)}</td>
							<td class="center"><a href="javascript:functionManager('${roles.roleCode}')">功能授权</a></td>
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
		<div id="rig-table">
				<div id="zhongxind">
					<ul id="leftTree" class="ztree"></ul>
				</div>
				<div style="text-align: center;line-height:20px;" colspan="10" id="fixed">
					<a class="btn-mini" id="fillBtn"  onclick="save();">保存</a>
				</div>
		</div>
	   	<%-- /列表操作区 --%>
	   	<div id="page" class="pagination">
			<form action="<%=basePath%>roleController.do?method=list" name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="roleCode" value="${role.roleCode}" />
				<input type="hidden" name="roleName" value="${role.roleName}" />
			</form>
		</div>
  	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%> 
<script src="weblib/assets/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.all.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#rig-table").height($("#footer").height());
	})
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#roleForm").submit();
	}
	var role = "";
	//功能授权
	function functionManager(roleCode){
		role = roleCode;
		var setting = {
			view: {
				selectedMulti: false
			},
			check:{
				enable: true,
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		var zNodes = "";
		$.ajax({
			url:"<%=basePath%>roleMenuController.do?method=jsonList",
			data:{roleCode:roleCode},
			async:false,
			type:"POST",
			dataType:"json",
			success:function(data){
				zNodes = eval(data);
			}
		});
		$.fn.zTree.init($("#leftTree"), setting,zNodes);
	}
	function save(){
			var zTree = $.fn.zTree.getZTreeObj("leftTree");
			var nodes = zTree.getCheckedNodes();
			var menus = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				menus += nodes[i].id+",";
			}
			menus = menus.substring(0,menus.length-1);
			$.ajax({
				url:"<%=basePath%>roleMenuController.do?method=saveRoleMenu",
				data:{roleCode:role,menus:menus},
				async:false,
				type:"POST",
				success:function(data){
					bootbox.alert(data);
					top.Dialog.close();
				}
			});
		}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>roleController.do?method=toAdd";
		 diag.Width = 380;
		 diag.Height = 220;
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
	   	 var role_code = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>roleController.do?method=toEdit&roleCode='+role_code;
		 diag.Width = 380;
		 diag.Height = 220;
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
	   	var roleCodes = getCheckStr("ids");	
		bootbox.confirm("是否确定要删除选择的记录?",function(result){
			if(result){
				$.ajax({
					type: "POST",
					url: '<%=basePath%>roleController.do?method=del',
			    	data: {'roleCodes': roleCodes},
					dataType:'json',
					cache: false,
					success: function(data){	
						//bootbox.alert(data);
						if (data.success){  //处理成功
							roleForm.submit();
						} else {
							//top.hangge();
							bootbox.alert("删除失败!"); 
						}
					}
				});
			}
		})
	}
</script>
</body>
</html>