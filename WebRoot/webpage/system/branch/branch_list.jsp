<%-- 
 * 文件名称: branch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-12 上午06:28:22
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
		<link rel="stylesheet" href="<%=basePath%>plugins/zTree/3.5/zTreeStyle.css" type="text/css">
		<link href="<%=basePath%>plugins/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
	</head>
<body style="font-family:'微软雅黑';">
	<div id="page-content" class="page-content">
		<div style="padding-left:10px;height:100%;float:left;width:30%;" id="zhongxin">
			<ul id="leftTree" class="ztree"></ul>
		</div>
		<div style="float:right;width:68%;height:100%;"  >
			<iframe id="treeNode" frameborder="no" width="100%" height="100%" overflow="no" src="<%=basePath%>branchController.do?method=treeNodeList" ></iframe> 
		</div>
	</div>	
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript" src="<%=basePath%>plugins/zTree/3.5/jquery.ztree.all.js"></script>
<script src="<%=basePath%>plugins/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
<script type="text/javascript">	
	var dataLength = 0;
	$(document).ready(function() {
		createZtree();
	});
	//创建左侧的组织机构树
	function createZtree(){
		var setting = {
				view: {
					selectedMulti: false
				},
				/*check:{
					enable: true,
					chkboxType: { "Y": "ps", "N": "ps" }
				},*/
				data: {
					simpleData: {
						enable: true
					}
				},
				callback : {
					onClick : function(event, treeId, treeNode) {
						var id = treeNode.id;
						createListTree(id);
					}
				}
			};
			var zNodes = "";
			$.ajax({
				url:"<%=basePath%>branchController.do?method=treeData",
				//data:{id:id},
				async:false,
				type:"POST",
				dataType:"json",
				success:function(data){
					zNodes = data;
				}
			});
			$.fn.zTree.init($("#leftTree"), setting,zNodes);
			var treeObj = $.fn.zTree.getZTreeObj("leftTree");
			treeObj.expandAll(true); //展开树的所有节点			
	}
	//创建右侧的列表树	
	function createListTree(id){
		$("#treeNode").attr("src","<%=basePath%>branchController.do?method=treeNodeList&id="+id);
		$("#treeNode").reload();
	}
</script>		
</body>
</html>