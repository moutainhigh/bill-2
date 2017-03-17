<%-- 
 * 文件名称: dict_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-8 上午06:28:22
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
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>/dictController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="key_no">分组编号</label>
						<input class="input-medium" type="text" name="key_no" value="${mapField.key_no}" placeholder="请输入分组编号"/>
						<label for="item_code">分组key</label>
						<input class="input-medium" type="text" name="item_code" value="${mapField.item_code}" placeholder="请输入分组key"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
					<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
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
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center sort-column key_no'>分组编号</th>
							<th class='center sort-column key_name'>分组名称</th>
							<th class='center sort-column item_code'>分组key</th>
							<th class='center sort-column item_value'>分组value</th>
							<th class='center sort-column kernal_flag'>核心接口标志</th>
							<th class='center sort-column belong_type'>归属标志</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="dict" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${dict.keyNo},${dict.itemCode}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${dict.keyNo}</td>
									<td class="center">${dict.keyName}</td>
									<td class="center">${dict.itemCode}</td>
									<td class="center">${dict.itemValue}</td>
									<td class="center">${dict.kernalFlag}</td>
									<td class="center">${dict.belongType}</td>
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
				<form action="<%=basePath%>/dictController.do?method=list" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="key_no" value="${mapField.key_no}" />
					<input type="hidden" name="item_code" value="${mapField.item_code}" />
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
		var checkNum = getCheckNum("ids");
	   	 if (checkNum >1){
	   		bootbox.alert("最多选择一条记录");
	   		return;
	   	 }
	   	 var str = getCheckStr("ids");
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>dictController.do?method=toAdd&str="+str;
		 diag.Width = 450;
		 diag.Height = 320;
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
	   	 var str = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>dictController.do?method=toEdit&str='+str;
		 diag.Width = 450;
		 diag.Height = 310;
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
	   	var strs = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>dictController.do?method=del',
					data: {'strs': strs},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
						} else {
							top.hangge();
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