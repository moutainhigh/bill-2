<%-- 
 * 文件名称: tradeBank_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-1 下午04:28:22
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
				<form action="<%=basePath%>tradeBankController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="parentBankname" class="control-label">总行行名</label>
						<input class="input-medium" type="text" name="parentBankname" value="${mapField.parentBankname}" placeholder="请输入总行行名"/>
						<label for="childBankname" class="control-label">分支行行名</label>
						<input class="input-medium" type="text" name="childBankname" value="${mapField.childBankname}" placeholder="请输入分支行行名"/>
					</div>
					<div class="row-fluid">
						<label for="parentBankno" class="control-label">总行行号 </label>
						<input class="input-medium" type="text" name="parentBankno" value="${mapField.parentBankno}" placeholder="请输入总行行号 "/>
						<label for="childBankno" class="control-label">分支行行号 </label>
						<input class="input-medium" type="text" name="childBankno" value="${mapField.childBankno}" placeholder="请输入分支行行号 "/>
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
							<th class='center sort-column child_bankno'>分支行行号</th>
							<th class="center">分支行行名</th>
							<th class='center sort-column parent_bankno'>总行行号</th>
							<th class='center'>总行行名</th>
							<th class='center'>创建日期</th>
							<th class='center'>创建时间</th>
							<th class='center'>组织机构代码</th>
							<th class='center'>参与者类型</th>
						</tr>
					</thead>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="tradeBank" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='ids' value="${tradeBank.childBankno}" />
										</td>
										<td class='center'>${vs.index+1}</td>
										<td class='center'>${tradeBank.childBankno}</td>
										<td class='center'>${tradeBank.childBankname}</td>
										<td class='center'>${tradeBank.parentBankno}</td>
										<td class='center'>${tradeBank.parentBankname}</td>
										<td class='center'>${tradeBank.createDate}</td>
										<td class='center'>${tradeBank.createTime}</td>
										<td class='center'>${tradeBank.orgCode}</td>
										<td class='center'>${fns:getDictLabel('K_BUSSINESS_ROLE',tradeBank.partnerType)}</td>
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
					<form action="<%=basePath%>tradeBankController.do?method=list" name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="parentBankname" value="${mapField.parentBankname}" />
						<input type="hidden" name="childBankname" value="${mapField.childBankname}" />
						<input type="hidden" name="parentBankno" value="${mapField.parentBankno}" />
						<input type="hidden" name="childBankno" value="${mapField.childBankno}" />
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
		 diag.URL = "<%=basePath%>tradeBankController.do?method=toAdd";
		 diag.Width = 420;
		 diag.Height = 350;
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
	   	 var childBankno = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>tradeBankController.do?method=toEdit&childBankno='+childBankno;
		 diag.Width = 420;
		 diag.Height = 350;
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
	   	var tradeBank_ids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>tradeBankController.do?method=del',
			    	data: {'tradeBank_ids': tradeBank_ids},
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