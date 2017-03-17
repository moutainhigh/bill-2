<%-- 
 * 文件名称: rgctdraftlog_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
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
				<form action="<%=basePath%>/rgctDraftLogController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<input type="hidden" name="logId" value="${rgctdraftlog.logId}"/>
						<div class="row-fluid">
				   			<label for="rgct_id">登记中心ID</label>
							<input class="input-medium" type="text" name="rgct_id" value="${mapField.rgct_id}" placeholder="请输入登记中心ID"/>
							<label for="bill_no">票号</label>
							<input class="input-medium" type="text" name="bill_no" value="${mapField.bill_no}" placeholder="请输入票号"/>
							<label for="req_sid">请求报文编号</label>
							<input class="input-medium" type="text" name="draft_no_req" value="${mapField.draft_no_req}" placeholder="请输入请求报文编号"/>
						</div>
						<div class="row-fluid">
							<label for="status" text-right">处理状态</label>
							<t:dictSelect className="select-medium" name="status" other="" dictGroup="K_CLBZ" defaultVal="${mapField.process_flag}" haveHead="true"  title="状态">
							</t:dictSelect>
							<label for="resp_sid">应答报文编号</label>
							<input class="input-medium" type="text" name="draft_no_resp" value="${mapField.draft_no_resp}"" placeholder="请输入应答报文编号"/>
							<a class="btn-mini" id="search" onclick="searchd();">查询</a>
						</div>
							<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>	
					</form>
				</div>
				<%-- /查询区  --%>
				<%-- 列表操作区 --%>
				<div id="footer">
					<table id="table_report" class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1600px;width:1600px;">
						<thead>
							<tr>
								<th class="center" onclick="selectAll('zcheckbox', 'ids')">
									<input type="checkbox" id="zcheckbox" />
								</th>
								<th class="center">序号</th>
								<th class='center sort-column rgct_id'>登记中心ID</th>
								<th class='center sort-column bill_no'>票号</th>
								<th class='center sort-column req_sid'>请求报文ID</th>
								<th class='center sort-column draft_no_req'>请求报文编号</th>
								<th class='center sort-column req_dt'>请求日期</th>
								<th class='center sort-column resp_sid'>应答报文ID</th>
								<th class='center sort-column draft_no_resp'>应答报文编号</th>
								<th class='center sort-column resp_dt'>应答日期</th>
								<th class='center sort-column process_flag'>处理状态</th>
								<th class='center sort-column process_flag'>详情</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="rgctdraftlog" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='ids' value="${rgctdraftlog.rgctId}" />
										</td>
										<td class="center">${vs.index+1}</td>
										<td class="center">${rgctdraftlog.rgctId}</td>
										<td class="center">${rgctdraftlog.billNo}</td>
										<td class="center">${rgctdraftlog.reqSid}</td>
										<td class="center">${rgctdraftlog.draftNoReq}</td>
										<td class="center">${rgctdraftlog.reqDt}</td>
										<td class="center">${rgctdraftlog.respSid}</td>
										<td class="center">${rgctdraftlog.draftNoResp}</td>
										<td class="center">${rgctdraftlog.respDt}</td>
										<c:if test="${rgctdraftlog.processFlag=='1'}">
											<td class="center">已处理</td>
										</c:if>
										<c:if test="${rgctdraftlog.processFlag=='0'}">
											<td class="center">未处理</td>
										</c:if>
										<c:if test="${rgctdraftlog.processFlag!='0'&&rgctdraftlog.processFlag!='1'}">
											<td class="center"></td>
										</c:if>
										<td class="center"><a href=javascript:goDetail('${rgctdraftlog.logId}')>查看</a></td>
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
					<form action="" name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="logId" value="${mapField.logId}" />
						<input type="hidden" name="rgct_id" value="${mapField.rgct_id}" />
						<input type="hidden" name="bill_no" value="${mapField.bill_no}" />
						<input type="hidden" name="draft_no_req" value="${mapField.draft_no_req}" />
						<input type="hidden" name="status" value="${mapField.process_flag}" />
						<input type="hidden" name="draft_no_resp" value="${mapField.draft_no_resp}" />
					</form>
				</div>
  			</div>
		</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("rgct_id").readOnly = true;
	}
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#userForm").submit();
	}
	function page(){
		$("#userForm").submit();
		return false;
    }
    //详情页面
	function goDetail(logId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="报文日志详情";
		 diag.URL = "<%=basePath%>rgctDraftLogController.do?method=detail&logId="+logId;
		 diag.Width = 950;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
</script>
</body>
</html>