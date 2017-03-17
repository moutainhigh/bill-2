<%-- 
 * 文件名称: custManager_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-12 上午09:28:22
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
				<form action="<%=basePath%>custManagerController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="custManagerName">经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${mapField.custManagerName}" placeholder="请输入经理名称"/>
						<label for="custManagerNo">经理编号</label>
						<input type="text" class="input-medium" name="custManagerNo" value="${mapField.custManagerNo}" placeholder="请输入经理编号"/>
					</div>
					<div class="row-fluid">
						<label for="deptNo" class="control-label">归属部门</label>
			   			<sys:treeselect className="input-medium" id="deptNo" name="deptNo" value="${mapField.deptNo}" module="0" labelName="parent.menuName" labelValue="${mapField.deptNo}"
								title="部门" url="tagController.do?method=treeData" extId="${menu.menuCode}"  subnode="dep_code"  pnode = "parent_code" nodename="dep_name" sourcename="tdept"/>
						<label for="status">是否启用</label>
						<t:dictSelect className="select-medium"  name="status"  other="" dictGroup="K_YORN" defaultVal="${mapField.status}" haveHead="true">
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
			  		 	<div class="span6" id="btn-right">
			   			</div>
		  			</div>
		  		</form>
		  	</div>
		    <%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'custManagerNos')">
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center sort-column cust_manager_no'>经理编号</th>
							<th class='center sort-column cust_manager_name'>经理名称</th>
							<th class='center sort-column dept_no'>归属部门编号</th>
							<th class='center sort-column status'>是否启用</th>
							<th class='center sort-column create_oper_name'>维护柜员</th>
							<th class='center sort-column create_dt'>维护日期</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="custmanage" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='custManagerNos' value="${custmanage.custManagerNo}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${custmanage.custManagerNo}</td>
									<td class="center">${custmanage.custManagerName}</td>
									<td class="center">${custmanage.deptNo}</td>
									<td class="center">${fns:getDictLabel('K_YORN',custmanage.status)}</td>
									<td class="center">${custmanage.createOperName}</td>
									<td class="center">${custmanage.createDt}</td>
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
				<form action="<%=basePath%>custManagerController.do?method=list" name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="custManagerName" value="${mapField.custManagerName}" />
					<input type="hidden" name="custManagerNo" value="${mapField.custManagerNo}" />
					<input type="hidden" name="deptNo" value="${mapField.deptNo}" />
					<input type="hidden" name="status" value="${mapField.status}" />
				</form>
			</div>
	  	</div>
	</div>	
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
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
		 diag.URL = "<%=basePath%>custManagerController.do?method=toAdd";
		 diag.Width = 500;
		 diag.Height = 300;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(){
		var checkNum = getCheckNum("custManagerNos");
		if (checkNum !=1){
			bootbox.alert("请选择一条记录编辑");
			return;
		}
		var custManagerNo = getCheckStr("custManagerNos");
		var diag = new top.Dialog();
		diag.Drag = true;
		diag.Title ="修改";
		diag.URL = '<%=basePath%>custManagerController.do?method=toEdit&custManagerNo='+custManagerNo;
		diag.Width = 500;
		diag.Height = 300;
		diag.CancelEvent = function(){ //关闭事件
			diag.close();
			pageForm.submit();
		};
		diag.show();
	}
	//删除
	function del(){
		var checkNum = getCheckNum("custManagerNos");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	}
	   	var custManagerNos = getCheckStr("custManagerNos");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>custManagerController.do?method=del',
			    	data: {'custManagerNos': custManagerNos},
					dataType:'json',
					//beforeSend: validateData,
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