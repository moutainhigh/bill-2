<%-- 
 * 文件名称: department_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-7-7 上午06:28:22
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
				<form action="<%=basePath%>deptController.do?method=listDept" method="post" name="deptForm" id="deptForm" class="form-search">
					<div class="row-fluid">
						<label for="dep_name">部门名称</label>
						<input class="input-medium" type="text" name="dep_name" value="${mapField.dep_name}" id="dep_name" placeholder="请输入部门名称"/>
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
					    	<a class="btn-mini" onclick="add();">新增</a>
							<a class="btn-mini" onclick="edit();">修改</a>
							<a class="btn-mini" onclick="del();">删除</a>
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
							<th class='center sort-column dep_code'>部门编号</th>
							<th class='center sort-column dep_name'>部门名称</th>
							<th class='center sort-column short_name'>部门简称</th>
							<th class='center sort-column remark'>备注</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="deptparam" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${deptparam.depCode}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${deptparam.depCode}</td>
									<td class="center">${deptparam.depName}</td>
									<td class="center">${deptparam.shortName}</td>
									<td class="center">${deptparam.remark}</td>
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
			<form  action="#" name="dataCollectForm" method="post"></form>
			<div id="page" class="pagination">
				<form action="<%=basePath%>deptController.do?method=listDept"  name="pageForm" method="post" >
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="dep_name" value="${mapField.dep_name}" />
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
		$("#deptForm").submit();
	}	
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>deptController.do?method=toAdd";
		 diag.Width = 530;
		 diag.Height = 260;
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
	   		bootbox.alert('请选择一条记录编辑');
	   		return;
	   	 }
	   	 var dept_id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>deptController.do?method=toEdit&depCode='+dept_id;
		 diag.Width = 530;
		 diag.Height = 260;
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
	   		bootbox.alert('请选择删除记录');
	   		return;
	   	}
	   	var dept_ids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result){
				$.ajax({
					type: "POST",
					url: '<%=basePath%>deptController.do?method=del',
			    	data: {'dept_ids': dept_ids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							bootbox.alert(data.msg);
							deptForm.submit();
						} else {
							top.hangge();
							bootbox.alert('删除失败!');
						}
					}
				});
			}
	    });
	}
	
	function page(){
		$("#deptForm").submit();
		return false;
    }
</script>
</body>
</html>