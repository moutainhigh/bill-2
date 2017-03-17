<%-- 
 * 文件名称: branch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-8-1 上午06:28:22
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
		<link rel="<%=basePath%>stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" type="text/css">
		<link href="<%=basePath%>plugins/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
	</head>

<body style="font-family:'微软雅黑';">
	<div class="clearfix">
		<div id="page-content" class="page-content">
		<%-- 列表操作区 --%>
		<div id="footer">
			<form action="<%=basePath%>branchController.do?method=treeNodeList" method="post" name="treeForm" id="treeForm">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class='center branch_name'>机构名称</th>
							<th class='center branch_no'>机构编号</th>
							<th class='center pay_bank_no'>联行行号</th>
							<th class='center elec_auth'>票据权限</th>
							<th class='center if_effective'>是否生效</th>
							<th class='center '>操作</th>
						</tr>
					</thead>
					<tbody id="treeTableList">
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="branch" varStatus="vs">
									<tr id="${branch.branchNo}" pId="${branch.branchLevel ne level ? branch.parentBrchCode : '0'}">
										<td nowrap >${branch.branchName}</td>
										<td class="center" >${branch.branchNo}</td>
										<td class="center" >${branch.payBankNo}</td>
										<td class="center" >${fns:getDictLabel('K_YORN',branch.elecAuth)}</td>
										<td class="center" >${fns:getDictLabel('K_YORN',branch.ifEffective)}</td>
										<td class='center nowrap'>
										  	<a href="javascript:edit('${branch.branchNo}')">修改</a>
											<%--<a href="javascript:del('${branch.branchNo}')">删除</a>--%>
											<a href="javascript:add('${branch.branchNo}')">添加下级机构</a> 
										 </td>
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
			</form>
		</div>
	  	<%-- /列表操作区 --%>
	   	<div>
			<div class="pagination">${page.pageStr}</div>
		</div>
  	</div>
</div>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript" src="<%=basePath%>plugins/zTree/3.5/jquery.ztree.all.js"></script>
<script src="<%=basePath%>plugins/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var dataLength = 0;
	$(document).ready(function() {
		$("#table_report").treeTable({expandLevel : 2}).show();
	});	
	//检索
	function search(){
		$("#treeForm").submit();
	}	
	//新增
	function add(branchNo){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>branchController.do?method=toAdd&branchNo="+branchNo;
		 diag.Width = 750;
		 diag.Height = 390;
		 diag.CancelEvent = function(){ //关闭事件
			 parent.location.reload();
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(branchNo){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>branchController.do?method=toEdit&branch_no='+branchNo;
		 diag.Width = 750;
		 diag.Height = 390;
		 diag.CancelEvent = function(){ //关闭事件
			parent.location.reload();
			diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(branchNo){
	   	bootbox.confirm("是否确定要删除该机构吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>branchController.do?method=del',
					data: {branchNo: branchNo},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							parent.location.reload();
						} else {
							bootbox.alert("删除失败!请先删除子机构！"); 
						}
					}
				});
		  	}
	   	});
	}
	function page(){
		$("#treeForm").submit();
		return false;
    }
</script>
</body>
</html>