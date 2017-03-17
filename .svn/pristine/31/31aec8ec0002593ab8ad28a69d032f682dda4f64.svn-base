<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<script src="weblib/assets/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.all.js"></script>
<body>
	


		
<div class="container-fluid" id="main-container">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td>
					<div style="padding-left:20px;" id="zhongxin">
						<ul id="leftTree" class="ztree"></ul>
					</div>
				</td>
			</tr>
			<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
			<tr>
				<td style="text-align: center;" colspan="10" id="fixed">
					<a class="btn btn-minier btn-grey"  onclick="save();">保存</a>
					<a class="btn btn-minier btn-grey" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
</div>
<SCRIPT type="text/javascript">
		function save(){
			var zTree = $.fn.zTree.getZTreeObj("leftTree");
			var nodes = zTree.getCheckedNodes();
			var menus = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				menus += nodes[i].id+",";
			}
			menus = menus.substring(0,menus.length-1);
			var roleCode = "<%=request.getParameter("roleCode")%>";
			$.ajax({
				url:"<%=basePath%>roleMenuController.do?method=saveRoleMenu",
				data:{roleCode:roleCode,menus:menus},
				async:false,
				type:"POST",
				success:function(data){
					alert(data);
					top.Dialog.close();
				}
			});
		}

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

		//var zNodes =[{id:"systemManage",pId:"root",name:"系统管理",open:true},{id:"aaaManage",pId:"menuManage",name:"aaa",open:true},{id:"menuManage",pId:"systemManage",name:"菜单管理",open:true},{id:"roleManage",pId:"systemManage",name:"角色管理",open:true},{id:"aaa1Manage",pId:"menuManage",name:"aaa1",open:true},{id:"userManage",pId:"systemManage",name:"用户管理",open:true},{id:"aaa2Manage",pId:"menuManage",name:"aaa2",open:true},{id:"sysParamManage",pId:"systemManage",name:"参数管理",open:true}];

		function showIconForTree(treeId, treeNode) {
			return !treeNode.isParent;
		};
		var zNodes = "";
		$(document).ready(function(){
			var roleCode = "<%=request.getParameter("roleCode")%>";
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
		});	
	</SCRIPT>
	</body>
</html>

