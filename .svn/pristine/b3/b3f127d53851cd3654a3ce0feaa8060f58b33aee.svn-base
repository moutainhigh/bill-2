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
<body style="font-family:'微软雅黑';">
<div class="clearfix">
	<div id="page-content" class="page-content">
		<div id="footer">
			<div style="width:22%;height:93%;float:left;padding:15px;border:solid 1px #ccc">
				<div style="text-align:center;" id="fixed">
					<a class="btn-mini" onclick="upFn();">上移</a>
					<a class="btn-mini" onclick="downFn();">下移</a>
					<a class="btn-mini" onclick="goBackFn();">返回</a>
				</div>
				<div id="auditRouteInfoDiv" style="padding:20px;overflow-y:auto; overflow-x:hidden;">
					<ul id="leftTree" class="ztree"></ul>
				</div>
			</div>
			<div id="eventDiv" style="width:70%;height:93%;padding:15px;float:right;border:solid 1px #ccc">
				<iframe name="rightFrame" id="rightFrame" frameborder="0" src="" style="width:100%;height:100%" ></iframe>
			</div>
		</div>
	</div>
	<form  action="#" name="dataCollectForm" method="post" style="margin:0px;"></form>
</div>
<input type="hidden" id="arId" name="arId" value="${arId}">
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	$(function (){
		var footerHeight;
		footerHeight=$("#page-content").innerHeight()-$("#header").innerHeight()-$("#center").innerHeight()-$("#page").innerHeight();
		$("#footer").height(footerHeight);
		var arId=$("#arId").val();
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
					enable: true,
					idKey: "id",
					pIdKey: "parent",
					rootPId: "0"
				}
			},
			callback: {
				onClick: onClick 
			}
		};
		var treedata
		$.ajax({
			url:"<%=basePath%>auditRouteController.do?method=jsonList",
			data:{arId:arId},
			async:false,
			type:"POST",
			dataType:"json",
			success:function(data){
				treedata=eval(data);
			}
		});
		$.fn.zTree.init($("#leftTree"), setting,treedata);
		var treeObj = $.fn.zTree.getZTreeObj("leftTree");
		treeObj.expandAll(true); //展开树的所有节点
	});
	function onClick(event,treeId,treeNode){
		var id = treeNode.id;
		var name = treeNode.name;
		var parent = treeNode.parent;
		var treeNodeType = treeNode.treeNodeType;
		if(treeNodeType=="0"){
			$("#rightFrame").attr("src","auditRouteController.do?method=toRouteInfo&id="+id);
		}else if(treeNodeType=="1"){
			$("#rightFrame").attr("src","auditRouteController.do?method=toNodeInfo&id="+id);
		}else if(treeNodeType=="2"){
			$("#rightFrame").attr("src","auditRouteController.do?method=toStationInfo&id="+id);
		}
	}
	//上移按钮事件 0-上移
	function upFn() {
		move(0);
	}
	//下移按钮事件 1-下移
	function downFn() {
		move(1);
	}
	function move(upOrDown){
		var zTree = $.fn.zTree.getZTreeObj("leftTree");
		var nodes = zTree.getCheckedNodes();
		var flag = true;
		var count = 0;
		var stationNode;
		for (var i=0; i<nodes.length; i++) {
			if(nodes[i].treeNodeType=="2"){
				count += 1;
				stationNode = nodes[i];
			}
		}
		if(count>1){
			bootbox.alert("只能选择单个岗位节点");
			return;
		}
		$.ajax({
			url:"<%=basePath%>auditRouteController.do?method=moveStation",
			data:{"asId":stationNode.id,
					"upOrDown":upOrDown},
			async:false,
			type:"POST",
			dataType:"json",
			success:function(data){
				if(data.success){
					modal("#layer_loading,#image");
				 	location.href="<%=basePath%>auditRouteController.do?method=toDesignAuditRoute&arId="+"${arId}";
				}else{
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//返回按钮事件
	function goBackFn() {
		modal("#layer_loading,#image");
		dataCollectForm.action = "<%=basePath%>auditRouteController.do?method=searchAuditRoute";
		dataCollectForm.submit();
	}
</script>
</body>
</html>