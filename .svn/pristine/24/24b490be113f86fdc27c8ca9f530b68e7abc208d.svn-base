<%-- 
 * 文件名称: workingads_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-20
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
				<form action="<%=basePath%>/workingAdsController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid" id="same">
			   			<label for="working_ads_no">机构编号</label>
						<input class="input-medium" type="text" name="working_ads_no" value="${mapField.working_ads_no}" placeholder="请输入机构编号"/>
						<label for="working_ads_name">机构名称</label>
						<input class="input-medium" type="text" name="working_ads_name" value="${mapField.working_ads_name}" placeholder="请输入机构名称"/>
						<label for="status" class="control-label text-right">机构状态</label>
						<t:dictSelect className="select-medium" name="status" other="" dictGroup="K_QYBZ" defaultVal="${mapField.status}" haveHead="true">
						</t:dictSelect>
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
							<th class='center sort-column working_ads_no'>机构编号</th>
							<th class='center sort-column working_ads_name'>机构名称</th>
							<th class='center sort-column status'>机构状态</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="workingads" varStatus="vs">
							<tr>
								<td class="center">
									<input type='checkbox' name='ids' value="${workingads.workingAdsNo}" />
								</td>
								<td class="center">${vs.index+1}</td>
								<td class="center">${workingads.workingAdsNo}</td>
								<td class="center">${workingads.workingAdsName}</td>
								<c:if test="${workingads.status=='1'}">
									<td class="center">启用</td>
								</c:if>
								<c:if test="${workingads.status=='0'}">
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
			<form action="<%=basePath%>workingAdsController.do?method=list"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="working_ads_no" value="${mapField.working_ads_no}" />
				<input type="hidden" name="working_ads_name" value="${mapField.working_ads_name}" />
				<input type="hidden" name="status" value="${mapField.status}" />
				
			</form>
		</div>
  	</div>
</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("working_ads_no").readOnly = true;
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
		 diag.URL = "<%=basePath%>workingAdsController.do?method=toAdd";
		 diag.Width = 450;
		 diag.Height = 210;
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
	   	 var working_ads_no = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>workingAdsController.do?method=toEdit&working_ads_no='+working_ads_no;
		 diag.Width = 450;
		 diag.Height = 210;
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
	   	var working_ads_nos = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>workingAdsController.do?method=del',
			    	data: {'working_ads_nos': working_ads_nos},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
						} else {
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