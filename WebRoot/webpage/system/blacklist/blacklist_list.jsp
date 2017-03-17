<%-- 
 * 文件名称: blacklist_list.jsp
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
				<form action="<%=basePath%>blackController.do?method=listBlack" method="post" name="blackForm" id="blackForm" class="form-search">
					<div class="row-fluid">
						<label for="enterprise_name">企业名称</label>
						<input class="input-medium" type="text" name=enterprise_name id="enterprise_namee" value="${mapField.enterprise_name}" placeholder="请输入企业名称"/>
						<label for="union_bankno" class="control-label">关联行号</label>
						<input class="input-medium" type="text" name=union_bankno id="union_banknoe" value="${mapField.union_bankno}" placeholder="请输入关联行号"/>
					</div>
					<div class="row-fluid">
						<label for="oper_brch_no">操作柜员机构</label>
						<input class="input-medium" type="text" name=oper_brch_no id="oper_brch_noe" value="${mapField.oper_brch_no}" placeholder="请输入操作柜员机构"/>
						<label for="description" class="control-label">理由</label>
						<input class="input-medium" type="text" name=description id="descriptione" value="${mapField.description}" placeholder="请输入理由"/>
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
							<th class='center sort-column enterprise_name'>企业名称</th>
							<th class='center sort-column union_bankno'>关联行号</th>
							<th class='center sort-column create_dt'>创建日期</th>
							<th class='center sort-column create_time'>创建时间</th>
							<th class='center sort-column oper_brch_no'>操作柜员机构</th>
							<th class='center sort-column description'>理由</th>
							<th class='center sort-column oper_name'>操作柜员名称</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="blackparam" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${blackparam.unionBankno}" />
									</td>
									<td class='center'>${vs.index+1}</td>
									<td class='center'>${blackparam.enterpriseName}</td>
									<td class='center'>${blackparam.unionBankno}</td>
									<td class='center'>${blackparam.createDt}</td>
									<td class='center'>${blackparam.createTime}</td>
									<td class='center'>${blackparam.operBranchNo}</td>
									<td class='center'>${blackparam.description}</td>
									<td class='center'>${blackparam.operName}</td>
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
				<form action="<%=basePath%>blackController.do?method=listBlack"  name="pageForm" method="post" >
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="enterprise_name" value="${mapField.enterprise_name}" />
						<input type="hidden" name="union_bankno" value="${mapField.union_bankno}" />
						<input type="hidden" name="oper_brch_no" value="${mapField.oper_brch_no}" />
						<input type="hidden" name="description" value="${mapField.description}" />
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
		$("#blackForm").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>blackController.do?method=toAdd";
		 diag.Width = 450;
		 diag.Height = 290;
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
	   	 var black_id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>blackController.do?method=toEdit&blackid='+black_id;
		 diag.Width =500;
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
	   	var black_ids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
		if(result){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>blackController.do?method=del',
		    	data: {'black_ids': black_ids},
				dataType:'json',
				cache: false,
				success: function(data){	
					if (data.success){  //处理成功
						blackForm.submit();
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
		$("#blackForm").submit();
		return false;
    }
</script>
</body>
</html>