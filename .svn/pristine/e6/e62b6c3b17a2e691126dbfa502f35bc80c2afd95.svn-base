<%-- 
 * 文件名称: bbspProduct_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-12 下午04:28:22
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
				<form action="<%=basePath%>bbspProductController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="prodType">产品类型</label>
						<t:dictSelect className="select-medium" name="prodType" other="" dictGroup="K_PJCPLX" defaultVal="${mapField.prodType}" haveHead="true">
						</t:dictSelect>
						<label for="prodName">产品名称</label>
						<input class="input-medium" type="text" name="prodName" value="${mapField.prodName}" placeholder="请输入产品名称"/>
						<label for=prodStatus>启用标志</label>
						<t:dictSelect className="select-medium" name="prodStatus" other="" dictGroup="K_QYBZ" defaultVal="${mapField.prodStatus}" haveHead="true">
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
							<th class="center">产品名称</th>
							<th class='center sort-column prod_no'>产品编号</th>
							<th class="center">产品类型</th>
							<th class="center">启用标志</th>
							<th class='center sort-column create_date'>开立时间</th>
							<th class="center">创建柜员名称</th>
							<th class="center">创建柜员号</th>
							<th class="center">产品编码</th>
							<th class="center">开通标志</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="bbspProduct" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${bbspProduct.prodNo}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${bbspProduct.prodName}</td>
									<td class="center">${bbspProduct.prodNo}</td>
									<td class="center">${fns:getDictLabel('K_PJCPLX',bbspProduct.prodType)}</td>
									<td class="center">${fns:getDictLabel('K_QYBZ',bbspProduct.prodStatus)}</td>
									<td class="center">${bbspProduct.createDate}</td>
									<td class="center">${bbspProduct.createUserName}</td>
									<td class="center">${bbspProduct.createUserNo}</td>
									<td class="center">${bbspProduct.prodCode}</td>
									<td class="center">${fns:getDictLabel('K_KTBZ',bbspProduct.branchsPower)}</td>
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
	   	<div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>bbspProductController.do?method=list" method="post" name="pageForm">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="prodType" value="${mapField.prodType}" />
					<input type="hidden" name="prodName" value="${mapField.prodName}" />
					<input type="hidden" name="prodStatus" value="${mapField.prodStatus}" />
				</form>
			</div>
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
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>bbspProductController.do?method=toAdd";
		 diag.Width = 700;
		 diag.Height = 300;
		 diag.CancelEvent = function(){ //关闭事件
			 if (diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				pageForm.submit();
			}
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
	   	 var prodNo = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>bbspProductController.do?method=toEdit&prodNo='+prodNo;
		 diag.Width = 700;
		 diag.Height = 300;
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
	   	var bbspProduct_ids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>bbspProductController.do?method=del',
			    	data: {'bbspProduct_ids': bbspProduct_ids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
						} else {
							bootbox.alert('删除失败!'); 
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