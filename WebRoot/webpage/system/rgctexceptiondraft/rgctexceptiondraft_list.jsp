<%-- 
 * 文件名称: rgctexceptiondraft_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
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
			<form action="<%=basePath%>/rgctExceptionDraftController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
				<input type="hidden" name="logId" value="${rgctexceptiondraft.logId}"/>
				<div class="row-fluid">
			   		<label for="bill_no">票号</label>
					<input class="input-medium" type="text" name="bill_no" value="${mapField.bill_no}" placeholder="请输入票号"/>
					<label for="status" class="text-right">处理状态</label>
					<t:dictSelect className="select-medium" name="status" other="" dictGroup="K_CLBZ" defaultVal="${mapField.status}" haveHead="true"  title="状态">
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
						<a class="btn-mini" onclick="reh();">重新处理</a>
						<a class="btn-mini"  onclick="del();">删除</a>
			   		</div>
			   		<div class="span6" id="btn-right"></div>
		  		</div>
			</form>
		</div>
	    <%-- /按钮操作区 --%>
		<%-- 列表操作区 --%>
		<div id="footer">
			<table id="table_report" class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1400px;width:1400px;">
				<thead>
					<tr>
						<th class="center" onclick="selectAll('zcheckbox', 'ids')">
							<input type="checkbox" id="zcheckbox" />
						</th>
						<th class="center">序号</th>
						<th class='center sort-column msg_sid'>报文ID</th>
						<th class='center sort-column draft_dt'>报文日期</th>
						<th class='center sort-column draft_no'>报文编号</th>
						<th class='center sort-column orgnl_msg_sid'>原报文ID</th>
						<th class='center sort-column orgnl_draft_dt'>原报文日期</th>
						<th class='center sort-column orgnl_draft_no'>原报文编号</th>
						<th class='center sort-column bill_no'>票号</th>
						<th class='center sort-column in_out'>收发状态</th>
						<th class='center sort-column status'>处理状态</th>
					</tr>
				</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="rgctexceptiondraft" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${rgctexceptiondraft.msgSid}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${rgctexceptiondraft.msgSid}</td>
									<td class="center">${rgctexceptiondraft.draftDt}</td>
									<td class="center">${rgctexceptiondraft.draftNo}</td>
									<td class="center">${rgctexceptiondraft.orgnlMsgSid}</td>
									<td class="center">${rgctexceptiondraft.orgnlDraftDt}</td>
									<td class="center">${rgctexceptiondraft.orgnlDraftNo}</td>
									<td class="center">${rgctexceptiondraft.billNo}</td>
									<c:if test="${rgctexceptiondraft.inOut=='1'}">
										<td class="center">收</td>
									</c:if>
									<c:if test="${rgctexceptiondraft.inOut=='0'}">
										<td class="center">发</td>
									</c:if>
									<c:if test="${rgctexceptiondraft.status=='1'}">
										<td class="center">已处理</td>
									</c:if>
									<c:if test="${rgctexceptiondraft.status=='0'}">
										<td class="center">未处理</td>
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
			<form action="<%=basePath%>rgctExceptionDraftController.do?method=list"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="bill_no" value="${mapField.bill_no}" />
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
		document.getElementById("msg_sid").readOnly = true;
	}
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#userForm").submit();
	}
	//重新处理
	function reh(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择重新处理记录");
	   		return;
	   	}
	   	var msg_sids = getCheckStr("ids");
	   	bootbox.confirm("是否确定要重新处理记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>rgctExceptionDraftController.do?method=reh',
			    	data: {'msg_sids': msg_sids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
						} else {
							bootbox.alert("重新处理失败!"); 
						}
					}
				});
		 	 }
	   	});
	}		
	//删除
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	}
	   	var msg_sids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>rgctExceptionDraftController.do?method=del',
			    	data: {'msg_sids': msg_sids},
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