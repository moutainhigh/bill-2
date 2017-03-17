<%-- 
 * 文件名称: rgctecdsstatus_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-21
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
		<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>rgctEcdsStatusController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="log();">人工登录</a>
					   	 	<a class="btn-mini"  onclick="quit();">人工退出</a>
							
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
								<th class="center">序号</th>
								<th class='center sort-column pname_cn'>系统状态</th>
								<th class='center sort-column pvalue_cn'>系统运营阶段</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="rgctecdsstatus" varStatus="vs">
									<tr>
										<td class="center">${vs.index+1}</td>
										<td class="center">${rgctecdsstatus.pnameCn}</td>
										<td class="center">${rgctecdsstatus.pvalueCn}</td>
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
					<form action="<%=basePath%>rgctEcdsStatusController.do?method=list"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					</form>
				</div>
		  	</div>
		</div>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//人工登录
	function log(){
		bootbox.confirm("是否确定人工登录?", function(result) {
			if(result){
				$.ajax({
					url:"manpowerLoginController.do?method=login",
					type:"post",
					data:{"type":"TM00"},
					dataType:"json",
					success:function(data){
						bootbox.alert(data.msg);
					},
					error:function(data){
					}
				});
			}
		});
	}
	//人工退出
	function quit(){
		bootbox.confirm("是否确定人工退出?", function(result) {
			if(result){
				$.ajax({
					url:"manpowerLogonController.do?method=logon",
					type:"post",
					data:{"type":"TM01"},
					dataType:"json",
					success:function(data){
						bootbox.alert(data.msg);
					},
					error:function(data){
					}
				});
			}
	   	});
	}
	//启动监听
	function start(){
		bootbox.confirm("是否确定启动监听?", function(result) {
	   	});
	}
	//停止监听
	function stop(){
		bootbox.confirm("是否确定停止监听?", function(result) {
	   	});
	}
	function page(){
		$("#userForm").submit();
		return false;
    }
</script>
</body>
</html>