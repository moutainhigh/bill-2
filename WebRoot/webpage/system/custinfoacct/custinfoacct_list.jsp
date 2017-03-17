<%-- 
 * 文件名称: custinfoacct_list.jsp
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
				<form action="<%=basePath%>custInfoAcctController.do?method=listCustInfoAcct" method="post" name="custInfoAcctForm" id="custInfoAcctForm" class="form-search">
					<div class="row-fluid">
						<label for="cust_no">客户号</label>
						<input class="input-medium" type="text" name="cust_no" id="cust_no" value="${mapField.cust_no}" placeholder="请输入客户号"/>
						<label for="acct_no">客户账号</label>
						<input class="input-medium" type="text" name="acct_no" id="acct_no" value="${mapField.acct_no}" placeholder="请输入客户账号"/>
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
							<th class='center sort-column cust_no'>客户号</th>
							<th class='center sort-column acct_no'>客户账号</th>
							<th class='center sort-column acct_type'>账户类型</th>
							<th class='center sort-column acct_branch_no'>账户开户机构</th>
							<th class='center sort-column acct_branch_name'>账户开户机构名</th>
							<th class='center sort-column open_date'>签约日期</th>
							<th class='center sort-column open_time'>签约时间</th>
							<th class='center sort-column acct_bank_no'>开户行行号</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="custinfoacctparam" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${custinfoacctparam.acctNo}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${custinfoacctparam.custNo}</td>
									<td class="center">${custinfoacctparam.acctNo}</td>
									<td class="center">${fns:getDictLabel('K_ZHLX',custinfoacctparam.acctType)}</td>
									<td class="center">${custinfoacctparam.acctBranchNo}</td>
									<td class="center">${custinfoacctparam.acctBranchName}</td>
									<td class="center">${custinfoacctparam.openDate}</td>
									<td class="center">${custinfoacctparam.openTime}</td>
									<td class="center">${custinfoacctparam.acctBankNo}</td>
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
				<form action="<%=basePath%>custInfoAcctController.do?method=listCustInfoAcct"  name="pageForm" method="post" >
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="cust_no" value="${mapField.cust_no}" />
					<input type="hidden" name="acct_no" value="${mapField.acct_no}" />
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
		$("#custInfoAcctForm").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>custInfoAcctController.do?method=toAdd";
		 diag.Width = 460;
		 diag.Height =290;
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
	   	 var custacct_id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>custInfoAcctController.do?method=toEdit&custacctid='+custacct_id;
		 diag.Width = 460;
		 diag.Height = 290;
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
	   	var custacct_ids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
		if(result){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>custInfoAcctController.do?method=del',
		    	data: {'custacct_ids': custacct_ids},
				dataType:'json',
				cache: false,
				success: function(data){	
					if (data.success){  //处理成功
						custInfoAcctForm.submit();
					} else {
						bootbox.alert('删除失败!');
					}
				}
			});
		  }
	   });
	}
	function page(){
		$("#custInfoAcctForm").submit();
		return false;
    }
</script>
</body>
</html>