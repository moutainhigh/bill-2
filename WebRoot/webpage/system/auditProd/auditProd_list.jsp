<%-- 
 * 文件名称: auditProd_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-9-28 上午09:28:22
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
			<div id="header">
				<form action="<%=basePath%>auditProdController.do?method=list"	method="post" name="Form" id="Form" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="industry_investment">机构号</label>
						<input type="text" class="input-medium" name="branchNo" id="branchNo" value="${searchBean.branchNo}"  placeholder="请输入机构号" />
						<label for="industry_investment">是否审批</label>
						<t:dictSelect className="select-medium" name="auditFlag" other="" dictGroup="K_YORN" defaultVal="${searchBean.auditFlag}" haveHead="true">
						</t:dictSelect>
						<a class="btn-mini" id="search" onclick="searchList();">查询</a>
					</div>
				</form>
			</div>
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
						<th class="center" onclick="selectAll('zcheckbox', 'noticeNos')">
							<input type="checkbox" id="zcheckbox" />
						</th>
						<th class="center">序号</th>
						<th class='center sort-column branch_no'>机构号</th>
						<th class='center '>机构名称</th>
						<th class='center '>产品名称</th>
						<th class='center '>是否需要审批</th>
					</tr>
					</thead>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="reArProd" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='id' value="${reArProd.id}" />
										</td>
										<td class="center">${vs.index+1}</td>
										<td class="center">${reArProd.branchNo}</td>
										<td class="center">${reArProd.branch.branchName}</td>
										<td class="center">${reArProd.product.prodName}</td>
										<td class="center">${fns:getDictLabel("K_YORN",reArProd.auditFlag)}</td>
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
					<form action="<%=basePath%>auditProdController.do?method=list" name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="id" value="${mapField.id}" />
						<input type="hidden" name="branchNo" value="${searchBean.branchNo}" />
						<input type="hidden" name="auditFlag" value="${searchBean.auditFlag}" />
					</form>
				</div>
		  	</div>
		</div>	
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	//检索
	function search(){
		$("#userForm").submit();
	}
	//新增
	function add(){
		var diag = new top.Dialog();
		diag.Drag = true;
		diag.Title ="新增";
		diag.URL = "<%=basePath%>auditProdController.do?method=toAddAuditProd";
		diag.Width = 500;
		diag.Height = 200;
		diag.CancelEvent = function(){ //关闭事件
		pageForm.submit();
		diag.close();
		};
		diag.show(); 
	}
	//修改
	function edit(){
		 var checkNum = getCheckNum("id");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var id = getCheckStr("id");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>auditProdController.do?method=toEditAuditProd&id='+id;
		 diag.Width = 500;
		 diag.Height = 200;
		 diag.CancelEvent = function(){ //关闭事件
				diag.close();
				pageForm.submit();
		 };
		 diag.show();
	}
	//删除
	function del(){
		var checkNum = getCheckNum("id");
	   	if (checkNum !=1){
	   		bootbox.alert("请选择一条记录删除");
	   		return;
	   	}
	   	var id = getCheckStr("id");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>auditProdController.do?method=delAuditProd',
			    	data: {'id': id},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							Form.submit();
						}else{
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
	function searchList(){
		modal("#layer_loading,#image");
		Form.submit();
	}
</script>
</body>
</html>